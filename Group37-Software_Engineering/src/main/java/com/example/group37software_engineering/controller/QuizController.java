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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
        List<League> leagues = (List<League>) leagueRepository.findAll();
        for(League league : leagues) {
            if(league.getThreshold() <= user.getPoints()) {
                user.setLeague(league);
            }
        }
        userRepository.save(user);
        userCourseRepository.save(userCourse);
        achievementController.Fantastic4(user);
        achievementController.Daredevil(user);
        achievementController.TheDarkKnight(user);
        achievementController.CyberGuardian(user);
        achievementController.Cortana(user);
        achievementController.Noble6(user);
        achievementController.TheFlash(user);
        achievementController.DataSorcerer(user);
        achievementController.IronLegion(user);
        achievementController.Octane(user);
        achievementController.MrFantastic(user);
        achievementController.HyperLethal(user);
        achievementController.TheChosenOne(user);
        model.addAttribute("score", score);
        return "quiz";
    }
}
