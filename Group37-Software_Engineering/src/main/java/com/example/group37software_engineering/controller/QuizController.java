package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.*;
import com.example.group37software_engineering.repo.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

import static java.lang.Math.max;

/**
 * Controller class for handling quiz-related operations.
 */
@Controller
public class QuizController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Autowired
    private AchievementController achievementController;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new QuizValidator(quizRepository));
    }

    /**
     * Handles displaying the quiz for a specific course.
     *
     * @param courseId The ID of the course for which the quiz is requested.
     * @param model    The model to add attributes to.
     * @return The view name for displaying the quiz.
     */
    @GetMapping("/quiz")
    public String getQuiz(@RequestParam int courseId, Model model) {
        Course course = courseRepository.findCourseById(courseId);
        model.addAttribute("quiz", new Quiz());
        model.addAttribute("courseQuiz", course.getQuiz());
        model.addAttribute("name", course.getTitle());
        model.addAttribute("courseId", courseId);
        return "quiz";
    }

    /**
     * Handles completing and scoring a quiz.
     *
     * @param quiz      The submitted Quiz object containing user answers.
     * @param result    The BindingResult to check for validation errors.
     * @param courseId  The ID of the course for which the quiz is completed.
     * @param model     The model to add attributes to.
     * @param principal The currently logged-in user.
     * @return The view name for displaying the quiz results.
     */
    @PostMapping("/completeQuiz")
    public String completeQuiz(@Valid @ModelAttribute("quiz") Quiz quiz, BindingResult result, @RequestParam int courseId, Model model, Principal principal) {
        Course course = courseRepository.findCourseById(courseId);
        model.addAttribute("courseQuiz", course.getQuiz());
        model.addAttribute("name", course.getTitle());
        model.addAttribute("courseId", courseId);
        if (result.hasErrors()) {
            return "quiz";
        }
        MyUser user = userRepository.findByUsername(principal.getName());
        UserCourses userCourse = userCourseRepository.findByUserAndCourse(user, course);
        Quiz courseQuiz = quizRepository.findById(quiz.getId());
        List<MyUser> previousLeaderboard = userRepository.findAllByLeagueId(user.getLeague().getId()).stream().sorted(Comparator.comparingDouble(MyUser::getPoints).reversed()).toList();
        List<MyUser> previousGlobal = (List<MyUser>) userRepository.findAll();
        previousGlobal = previousGlobal.stream().sorted(Comparator.comparingDouble(MyUser::getPoints).reversed()).toList();
        boolean promoted = false;
        int correct = 0;
        for (int i = 0; i < courseQuiz.getQuestions().size(); i++) {
            if (Objects.equals(courseQuiz.getQuestions().get(i).getAnswer(), quiz.getQuestions().get(i).getAnswer())) {
                correct += 1;
            }
        }
        double score = correct * 100.0 / courseQuiz.getQuestions().size();
        if (score >= 80) {
            userCourse.setEndDate();
            userCourse.setEndTime();
            user.setPoints(user.getPoints() + (int) course.getDuration() * 100);
        }
        userCourse.setPercentage(max(score, userCourse.getPercentage()));
        achievementController.Fantastic4(user);
        achievementController.Daredevil(user);
        achievementController.TheDarkKnight(user);
        achievementController.CyberGuardian(user);
        achievementController.Cortana(user);
        achievementController.Noble6(user);
        achievementController.TheFlash(user);
        achievementController.DataSorcerer(user);
        achievementController.IronLegion(user);
        achievementController.Quicksilver(user);
        achievementController.MrFantastic(user);
        achievementController.HyperLethal(user);
        achievementController.TheChosenOne(user);
        List<League> leagues = (List<League>) leagueRepository.findAll();
        leagues.sort(Comparator.comparing(League::getThreshold).reversed());
        for(League league : leagues) {
            if(league.getThreshold() <= user.getPoints()) {
                if (user.getLeague() == league) {
                    break;
                }
                promoted = true;
                user.setLeague(league);
                Notification n = new Notification();
                n.setDescription("You reached the " + league.getTitle() + " league!");
                n.setPageLink("/leaderboard");
                n.setIconLink(league.getImageUrl());
                notificationRepository.save(n);
                user.getNotifications().add(n);
                break;
            }
        }
        List<MyUser> currentLeaderboard = userRepository.findAllByLeagueId(user.getLeague().getId()).stream().sorted(Comparator.comparingDouble(MyUser::getPoints).reversed()).toList();
        List<MyUser> currentGlobal = (List<MyUser>) userRepository.findAll();
        currentGlobal = currentGlobal.stream().sorted(Comparator.comparingDouble(MyUser::getPoints).reversed()).toList();
        sendLeaderboardNotifications(previousGlobal, currentGlobal, user, true, true);
        sendLeaderboardNotifications(previousLeaderboard, currentLeaderboard, user, promoted, false);
        userRepository.save(user);
        userCourseRepository.save(userCourse);
        model.addAttribute("score", score);
        return "quiz";
    }

    public void sendLeaderboardNotifications(List<MyUser> previousLeaderboard, List<MyUser> currentLeaderboard, MyUser user, boolean promoted, boolean isGlobal) {
        int previousRank = previousLeaderboard.indexOf(user);
        int currentRank = currentLeaderboard.indexOf(user);
        if ((!Objects.equals(user.getLeague().getTitle(), "Unranked") || isGlobal) && currentRank < 3 && (currentRank < previousRank || promoted)) {
            Notification n1 = new Notification();
            if (isGlobal) {
                n1.setDescription("You reach #" + (currentRank + 1) + " in the global leaderboard!");
            } else {
                n1.setDescription("You reach #" + (currentRank + 1) + " in the " + user.getLeague().getTitle() + " league!");
            }
            n1.setPageLink("/leaderboard");
            n1.setIconLink(user.getLeague().getImageUrl());
            notificationRepository.save(n1);
            user.getNotifications().add(n1);
            if (currentLeaderboard.size() > currentRank + 1) {
                Optional<MyUser> droppedUser = userRepository.findById(currentLeaderboard.get(currentRank + 1).getId());
                if (droppedUser.isPresent()) {
                    Notification n2 = new Notification();
                    if (isGlobal) {
                        n2.setDescription(user.getUsername() + " took your #" + (currentRank + 1) + " place in the global leaderboard!");
                    } else {
                        n2.setDescription(user.getUsername() + " took your #" + (currentRank + 1) + " place in the " + user.getLeague().getTitle() + " league!");
                    }
                    n2.setPageLink("/leaderboard");
                    n2.setIconLink(user.getLeague().getImageUrl());
                    notificationRepository.save(n2);
                    droppedUser.get().getNotifications().add(n2);
                    userRepository.save(droppedUser.get());
                }
            }
        }
    }


}
