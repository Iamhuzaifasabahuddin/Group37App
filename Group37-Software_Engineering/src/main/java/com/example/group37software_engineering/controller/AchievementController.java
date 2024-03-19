package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.*;
import com.example.group37software_engineering.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * Controller for handling achievements related operations.
 */
@Controller
public class AchievementController {

    @Autowired
    private AchievementRepository achievementRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserAchievementRepository userAchievementRepository;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private UserCommentRepository userCommentRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    /**
     * Endpoint for showing achievements.
     *
     * @param model     the model to add attributes to for rendering in the view
     * @param principal the currently authenticated user
     * @return the name of the view to render
     */
    @GetMapping("/achievements")
    public String showAchievements(Model model, Principal principal) {
        MyUser user = userRepository.findByUsername(principal.getName());
        List<Achievement> achievements = (List<Achievement>) achievementRepository.findAll();
        model.addAttribute("achievements", achievements);
        model.addAttribute("user", user);
        model.addAttribute("notachieved", NotAchieved(user));
        return "achievements";
    }

    /**
     * Checks if a user has achieved a specific achievement.
     *
     * @param user        the user to check
     * @param achievement the achievement to check
     * @return true if the user has not achieved the achievement, false otherwise
     */
    private boolean IsUserAchievement(MyUser user, Achievement achievement) {
        return user.getUserAchievements().stream()
                .noneMatch(userAchievement -> userAchievement.getAchievement().equals(achievement));
    }

    /**
     * Gets a list of achievements not achieved by a user.
     *
     * @param user the user to get not achieved achievements for
     * @return a list of achievements not achieved by the user
     */
    public List<Achievement> NotAchieved(MyUser user) {
        List<Achievement> allAchievements = (List<Achievement>) achievementRepository.findAll();
        List<Achievement> notachieved = new ArrayList<Achievement>();
        for (Achievement achievement : allAchievements) {
            if (IsUserAchievement(user, achievement)) {
                notachieved.add(achievement);
            }
        }
        return notachieved;
    }


    /**
     * Gets the count of distinct course categories.
     *
     * @return the count of distinct course categories
     */
    private Integer getDistinctCategoryCount() {
        List<Course> courses = (List<Course>) courseRepository.findAll();
        return Math.toIntExact(courses.stream()
                .map(Course::getCategory)
                .distinct()
                .count());
    }

    /**
     * This method adds a notification for the user to inform of a completed achievement.
     *
     * @param user The user to send notifications to.
     * @param achievement The achievement the user should be notified of.
     */
    private void setNotifications(MyUser user, Achievement achievement) {
        Notification n = new Notification();
        n.setDescription("You earned the achievement " + achievement.getTitle() + "!");
        n.setPageLink("/profile");
        n.setIconLink(achievement.getImageUrl());
        notificationRepository.save(n);
        user.getNotifications().add(n);
        userRepository.save(user);
    }

    /**
     * Checks and updates the "Fantastic 4" achievement for a user.
     *
     * @param user the user to check and update the achievement for
     */
    public void Fantastic4(MyUser user) {
        Achievement achievement = achievementRepository.findAchievementByTitle("Fantastic 4");
        UserAchievement userAchievement = userAchievementRepository.findUserAchievementByUserAndAchievement(user, achievement);
        List<UserCourses> userCourses = userCourseRepository.findByUser(user);
        List<Course> courseList = userCourses.stream()
                .filter(userCourse -> userCourse.getPercentage() >= 80)
                .map(UserCourses::getCourse)
                .toList();

        List<String> distinctCategories = courseList.stream()
                .map(Course::getCategory)
                .distinct()
                .toList();
        System.out.println("distinctCategories: " + distinctCategories.size());
        Integer TotalCategoryCount = getDistinctCategoryCount();
        System.out.println("TotalCategoryCount: " + TotalCategoryCount);
        if (distinctCategories.size() >= 4 && userAchievement == null) {
            userAchievement = new UserAchievement();
            userAchievement.setUser(user);
            userAchievement.setAchievement(achievement);
            userAchievement.setAchieved(true);
            userAchievement.setDateAchieved();
            userAchievement.setTimeAchieved();
            user.setPoints(user.getPoints() + achievement.getPoints());
            userRepository.save(user);
            userAchievementRepository.save(userAchievement);
            setNotifications(user, achievement);
        }
    }

    /**
     * This method checks if the user has verified their email. If they have and they haven't already achieved the "Crypto" achievement,
     * it creates a new UserAchievement for the user, sets the achievement to "Crypto", marks it as achieved, sets the date and time of achievement,
     * adds the achievement points to the user's total points, and saves the user and the UserAchievement in their respective repositories.
     *
     * @param user The user to check for the achievement.
     */
    public void Crypto(MyUser user) {
        Achievement achievement = achievementRepository.findAchievementByTitle("Crypto");
        UserAchievement userAchievement = userAchievementRepository.findUserAchievementByUserAndAchievement(user, achievement);
        if (user.getEmailVerificationStatus() && userAchievement == null) {
            userAchievement = new UserAchievement();
            userAchievement.setUser(user);
            userAchievement.setAchievement(achievement);
            userAchievement.setAchieved(true);
            userAchievement.setDateAchieved();
            userAchievement.setTimeAchieved();
            user.setPoints(user.getPoints() + achievement.getPoints());
            userRepository.save(user);
            userAchievementRepository.save(userAchievement);
            setNotifications(user, achievement);
        }
    }

    /**
     * This method checks if the user has completed a course in the "Cybersecurity" category. If they have and they haven't already achieved the "Daredevil" achievement,
     * it creates a new UserAchievement for the user, sets the achievement to "Daredevil", marks it as achieved, sets the date and time of achievement,
     * adds the achievement points to the user's total points, and saves the user and the UserAchievement in their respective repositories.
     *
     * @param user The user to check for the achievement.
     */
    public void Daredevil(MyUser user) {
        Achievement achievement = achievementRepository.findAchievementByTitle("Daredevil");
        UserAchievement userAchievement = userAchievementRepository.findUserAchievementByUserAndAchievement(user, achievement);
        List<UserCourses> userCourses = userCourseRepository.findByUser(user);
        List<Course> courseList = userCourses.stream()
                .filter(userCourse -> userCourse.getPercentage() >= 80)
                .map(UserCourses::getCourse)
                .toList();
        List<Course> Courses = (List<Course>) courseRepository.findAll();

        if (courseList.size() == Courses.size() && userAchievement == null) {
            userAchievement = new UserAchievement();
            userAchievement.setUser(user);
            userAchievement.setAchievement(achievement);
            userAchievement.setAchieved(true);
            userAchievement.setDateAchieved();
            userAchievement.setTimeAchieved();
            user.setPoints(user.getPoints() + achievement.getPoints());
            userRepository.save(user);
            userAchievementRepository.save(userAchievement);
            setNotifications(user, achievement);
        }
    }

    /**
     * This method checks if the user has completed a course in the "Cybersecurity" category. If they have and they haven't already achieved the "The Dark Knight" achievement,
     * it creates a new UserAchievement for the user, sets the achievement to "The Dark Knight", marks it as achieved, sets the date and time of achievement,
     * adds the achievement points to the user's total points, and saves the user and the UserAchievement in their respective repositories.
     *
     * @param user The user to check for the achievement.
     */
    public void TheDarkKnight(MyUser user) {
        Achievement achievement = achievementRepository.findAchievementByTitle("The Dark Knight");
        UserAchievement userAchievement = userAchievementRepository.findUserAchievementByUserAndAchievement(user, achievement);
        List<UserCourses> userCourses = userCourseRepository.findByUser(user);
        List<Course> courseList = userCourses.stream()
                .filter(userCourse -> userCourse.getPercentage() >= 80)
                .map(UserCourses::getCourse)
                .filter(course -> course.getCategory().equalsIgnoreCase("Cybersecurity"))
                .toList();

        if (!courseList.isEmpty() && userAchievement == null) {
            userAchievement = new UserAchievement();
            userAchievement.setUser(user);
            userAchievement.setAchievement(achievement);
            userAchievement.setAchieved(true);
            userAchievement.setDateAchieved();
            userAchievement.setTimeAchieved();
            user.setPoints(user.getPoints() + achievement.getPoints());
            userRepository.save(user);
            userAchievementRepository.save(userAchievement);
            setNotifications(user, achievement);
        }
    }

    /**
     * This method checks if the user has completed a course in the "Artificial Intelligence" category. If they have and they haven't already achieved the "Cyber Guardian" achievement,
     * it creates a new UserAchievement for the user, sets the achievement to "Cyber Guardian", marks it as achieved, sets the date and time of achievement,
     * adds the achievement points to the user's total points, and saves the user and the UserAchievement in their respective repositories.
     *
     * @param user The user to check for the achievement.
     */
    public void CyberGuardian(MyUser user) {
        Achievement achievement = achievementRepository.findAchievementByTitle("Cyber Guardian");
        UserAchievement userAchievement = userAchievementRepository.findUserAchievementByUserAndAchievement(user, achievement);
        List<UserCourses> userCourses = userCourseRepository.findByUser(user);
        List<Course> courseList = userCourses.stream()
                .filter(userCourse -> userCourse.getPercentage() >= 80)
                .map(UserCourses::getCourse)
                .filter(course -> course.getCategory().equals("Cybersecurity"))
                .toList();

        if (courseList.size() >= 2 && userAchievement == null) {
            userAchievement = new UserAchievement();
            userAchievement.setUser(user);
            userAchievement.setAchievement(achievement);
            userAchievement.setAchieved(true);
            userAchievement.setDateAchieved();
            userAchievement.setTimeAchieved();
            user.setPoints(user.getPoints() + achievement.getPoints());
            userRepository.save(user);
            userAchievementRepository.save(userAchievement);
            setNotifications(user, achievement);
        }
    }

    /**
     * This method checks if the user has completed a course in the "Artificial Intelligence" category. If they have and they haven't already achieved the "Cortana" achievement,
     * it creates a new UserAchievement for the user, sets the achievement to "Cortana", marks it as achieved, sets the date and time of achievement,
     * adds the achievement points to the user's total points, and saves the user and the UserAchievement in their respective repositories.
     *
     * @param user The user to check for the achievement.
     */
    public void Cortana(MyUser user) {
        Achievement achievement = achievementRepository.findAchievementByTitle("Cortana");
        UserAchievement userAchievement = userAchievementRepository.findUserAchievementByUserAndAchievement(user, achievement);
        List<UserCourses> userCourses = userCourseRepository.findByUser(user);
        List<Course> courseList = userCourses.stream()
                .filter(userCourse -> userCourse.getPercentage() >= 80)
                .map(UserCourses::getCourse)
                .filter(course -> course.getCategory().equals("Artificial Intelligence"))
                .toList();
        System.out.println("courseList: " + courseList.size());
        if (!courseList.isEmpty() && userAchievement == null) {
            userAchievement = new UserAchievement();
            userAchievement.setUser(user);
            userAchievement.setAchievement(achievement);
            userAchievement.setAchieved(true);
            userAchievement.setDateAchieved();
            userAchievement.setTimeAchieved();
            user.setPoints(user.getPoints() + achievement.getPoints());
            userRepository.save(user);
            userAchievementRepository.save(userAchievement);
            setNotifications(user, achievement);
        }
    }

    /**
     * This method checks if the user has completed a course in the "Data Science" category. If they have and they haven't already achieved the "Data Wizard" achievement,
     * it creates a new UserAchievement for the user, sets the achievement to "Data Wizard", marks it as achieved, sets the date and time of achievement,
     * adds the achievement points to the user's total points, and saves the user and the UserAchievement in their respective repositories.
     *
     * @param user The user to check for the achievement.
     */
    public void Noble6(MyUser user) {
        Achievement achievement = achievementRepository.findAchievementByTitle("Noble 6");
        UserAchievement userAchievement = userAchievementRepository.findUserAchievementByUserAndAchievement(user, achievement);
        List<UserCourses> userCourses = userCourseRepository.findByUser(user);
        List<Course> courseList = userCourses.stream()
                .filter(userCourse -> userCourse.getPercentage() >= 80)
                .map(UserCourses::getCourse)
                .toList();

        if (courseList.size() >= 6 && userAchievement == null) {
            userAchievement = new UserAchievement();
            userAchievement.setUser(user);
            userAchievement.setAchievement(achievement);
            userAchievement.setAchieved(true);
            userAchievement.setDateAchieved();
            userAchievement.setTimeAchieved();
            user.setPoints(user.getPoints() + achievement.getPoints());
            userRepository.save(user);
            userAchievementRepository.save(userAchievement);
            setNotifications(user, achievement);
        }
    }

    /**
     * This method checks if the user has completed a course in the "Data Science" category. If they have and they haven't already achieved the "Data Wizard" achievement,
     * it creates a new UserAchievement for the user, sets the achievement to "Data Wizard", marks it as achieved, sets the date and time of achievement,
     * adds the achievement points to the user's total points, and saves the user and the UserAchievement in their respective repositories.
     *
     * @param user The user to check for the achievement.
     */
    public void TheFlash(MyUser user) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");

        Achievement achievement = achievementRepository.findAchievementByTitle("The Flash");
        UserAchievement userAchievement = userAchievementRepository.findUserAchievementByUserAndAchievement(user, achievement);
        List<UserCourses> userCourses = userCourseRepository.findByUser(user);
        List<Course> courseList = userCourses.stream()
                .filter(userCourse -> userCourse.getPercentage() >= 80)
                .filter(userCourse -> {
                    LocalDate startDate = userCourse.getStartDate();
                    LocalTime startTime = LocalTime.parse(userCourse.getStartTime(), formatter);
                    LocalDate endDate = userCourse.getEndDate();
                    LocalTime endTime = LocalTime.parse(userCourse.getEndTime(), formatter);

                    LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
                    LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);

                    return ChronoUnit.HOURS.between(startDateTime, endDateTime) <= 24;
                })
                .map(UserCourses::getCourse)
                .toList();

        if (!courseList.isEmpty() && userAchievement == null) {
            userAchievement = new UserAchievement();
            userAchievement.setUser(user);
            userAchievement.setAchievement(achievement);
            userAchievement.setAchieved(true);
            userAchievement.setDateAchieved();
            userAchievement.setTimeAchieved();
            user.setPoints(user.getPoints() + achievement.getPoints());
            userRepository.save(user);
            userAchievementRepository.save(userAchievement);
            setNotifications(user, achievement);
        }
    }

    /**
     * This method checks if the user has completed a course in the "Data Science" category. If they have and they haven't already achieved the "Data Wizard" achievement,
     * it creates a new UserAchievement for the user, sets the achievement to "Data Wizard", marks it as achieved, sets the date and time of achievement,
     * adds the achievement points to the user's total points, and saves the user and the UserAchievement in their respective repositories.
     *
     * @param user The user to check for the achievement.
     */
    public void DataSorcerer(MyUser user) {
        Achievement achievement = achievementRepository.findAchievementByTitle("Data sorcerer");
        UserAchievement userAchievement = userAchievementRepository.findUserAchievementByUserAndAchievement(user, achievement);
        List<UserCourses> userCourses = userCourseRepository.findByUser(user);
        List<Course> courseList = userCourses.stream()
                .filter(userCourse -> userCourse.getPercentage() >= 80)
                .map(UserCourses::getCourse)
                .filter(course -> course.getCategory().equalsIgnoreCase("Data Science"))
                .toList();

        if (!courseList.isEmpty() && userAchievement == null) {
            userAchievement = new UserAchievement();
            userAchievement.setUser(user);
            userAchievement.setAchievement(achievement);
            userAchievement.setAchieved(true);
            userAchievement.setDateAchieved();
            userAchievement.setTimeAchieved();
            user.setPoints(user.getPoints() + achievement.getPoints());
            userRepository.save(user);
            userAchievementRepository.save(userAchievement);
            setNotifications(user, achievement);
        }
    }

    /**
     * This method checks if the user has completed a course in the "Data Science" category. If they have and they haven't already achieved the "Data Wizard" achievement,
     * it creates a new UserAchievement for the user, sets the achievement to "Data Wizard", marks it as achieved, sets the date and time of achievement,
     * adds the achievement points to the user's total points, and saves the user and the UserAchievement in their respective repositories.
     *
     * @param user The user to check for the achievement.
     */
    public void IronLegion(MyUser user) {
        Achievement achievement = achievementRepository.findAchievementByTitle("Iron Legion");
        UserAchievement userAchievement = userAchievementRepository.findUserAchievementByUserAndAchievement(user, achievement);
        List<UserCourses> userCourses = userCourseRepository.findByUser(user);
        List<Course> courseList = userCourses.stream()
                .filter(userCourse -> userCourse.getPercentage() >= 80)
                .map(UserCourses::getCourse)
                .filter(course -> course.getCategory().equals("Artificial Intelligence"))
                .toList();
        List<Course> Courses = courseRepository.findAllByCategory("Artificial Intelligence");
        if (courseList.size() == Courses.size() && userAchievement == null) {
            userAchievement = new UserAchievement();
            userAchievement.setUser(user);
            userAchievement.setAchievement(achievement);
            userAchievement.setAchieved(true);
            userAchievement.setDateAchieved();
            userAchievement.setTimeAchieved();
            user.setPoints(user.getPoints() + achievement.getPoints());
            userRepository.save(user);
            userAchievementRepository.save(userAchievement);
            setNotifications(user, achievement);
        }
    }

    /**
     * This method checks if the user has completed a course in the "Data Science" category. If they have and they haven't already achieved the "Data Wizard" achievement,
     * it creates a new UserAchievement for the user, sets the achievement to "Data Wizard", marks it as achieved, sets the date and time of achievement,
     * adds the achievement points to the user's total points, and saves the user and the UserAchievement in their respective repositories.
     *
     * @param user The user to check for the achievement.
     */
    public void Mirage(MyUser user) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        Achievement achievement = achievementRepository.findAchievementByTitle("Mirage");
        UserAchievement userAchievement = userAchievementRepository.findUserAchievementByUserAndAchievement(user, achievement);
        List<UserCourses> userCourses = userCourseRepository.findByUser(user);
        LocalDateTime nowMinus24Hours = LocalDateTime.now().minusHours(24);

        List<Course> courseList = userCourses.stream()
                .filter(userCourse -> {
                    String startTimeString = userCourse.getStartTime();
                    if (startTimeString == null) {
                        return false;
                    }
                    LocalDate startDate = userCourse.getStartDate();
                    LocalTime startTime = LocalTime.parse(startTimeString, formatter);
                    LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);

                    return startDateTime.isAfter(nowMinus24Hours);
                })
                .map(UserCourses::getCourse)
                .toList();

        if (courseList.size() >= 2 && userAchievement == null) {
            userAchievement = new UserAchievement();
            userAchievement.setUser(user);
            userAchievement.setAchievement(achievement);
            userAchievement.setAchieved(true);
            userAchievement.setDateAchieved();
            userAchievement.setTimeAchieved();
            user.setPoints(user.getPoints() + achievement.getPoints());
            userRepository.save(user);
            userAchievementRepository.save(userAchievement);
            setNotifications(user, achievement);
        }
    }

    /**
     * This method checks if the user has completed a course in the "Data Science" category. If they have and they haven't already achieved the "Data Wizard" achievement,
     * it creates a new UserAchievement for the user, sets the achievement to "Data Wizard", marks it as achieved, sets the date and time of achievement,
     * adds the achievement points to the user's total points, and saves the user and the UserAchievement in their respective repositories.
     *
     * @param user The user to check for the achievement.
     */
    public void Quicksilver(MyUser user) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        Achievement achievement = achievementRepository.findAchievementByTitle("QuickSilver");
        UserAchievement userAchievement = userAchievementRepository.findUserAchievementByUserAndAchievement(user, achievement);
        List<UserCourses> userCourses = userCourseRepository.findByUser(user);
        List<Course> courseList = userCourses.stream()
                .filter(userCourse -> userCourse.getPercentage() >= 80)
                .filter(userCourse -> {
                    LocalDate startDate = userCourse.getStartDate();
                    LocalTime startTime = LocalTime.parse(userCourse.getStartTime(), formatter);
                    LocalDate endDate = userCourse.getEndDate();
                    LocalTime endTime = LocalTime.parse(userCourse.getEndTime(), formatter);


                    LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
                    LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);

                    return ChronoUnit.HOURS.between(startDateTime, endDateTime) <= 24;
                })
                .map(UserCourses::getCourse)
                .toList();

        if (courseList.size() >= 2 && userAchievement == null) {
            userAchievement = new UserAchievement();
            userAchievement.setUser(user);
            userAchievement.setAchievement(achievement);
            userAchievement.setAchieved(true);
            userAchievement.setDateAchieved();
            userAchievement.setTimeAchieved();
            user.setPoints(user.getPoints() + achievement.getPoints());
            userRepository.save(user);
            userAchievementRepository.save(userAchievement);
            setNotifications(user, achievement);
        }
    }

    /**
     * This method checks if the user has completed a course in the "Data Science" category. If they have and they haven't already achieved the "Data Wizard" achievement,
     * it creates a new UserAchievement for the user, sets the achievement to "Data Wizard", marks it as achieved, sets the date and time of achievement,
     * adds the achievement points to the user's total points, and saves the user and the UserAchievement in their respective repositories.
     *
     * @param user The user to check for the achievement.
     */
    public void MrFantastic(MyUser user) {
        Achievement achievement = achievementRepository.findAchievementByTitle("MrFantastic");
        UserAchievement userAchievement = userAchievementRepository.findUserAchievementByUserAndAchievement(user, achievement);
        League league = leagueRepository.findLeagueByTitle("Titanium Titan");
        if (user.getPoints() >= league.getThreshold() && userAchievement == null) {
            userAchievement = new UserAchievement();
            userAchievement.setUser(user);
            userAchievement.setAchievement(achievement);
            userAchievement.setAchieved(true);
            userAchievement.setDateAchieved();
            userAchievement.setTimeAchieved();
            user.setPoints(user.getPoints() + achievement.getPoints());
            user.setLeague(league);
            userRepository.save(user);
            userAchievementRepository.save(userAchievement);
            setNotifications(user, achievement);
        }

    }

    /**
     * This method checks if the user has completed a course in the "Data Science" category. If they have and they haven't already achieved the "Data Wizard" achievement,
     * it creates a new UserAchievement for the user, sets the achievement to "Data Wizard", marks it as achieved, sets the date and time of achievement,
     * adds the achievement points to the user's total points, and saves the user and the UserAchievement in their respective repositories.
     *
     * @param user The user to check for the achievement.
     */
    public void HyperLethal(MyUser user) {
        Achievement achievement = achievementRepository.findAchievementByTitle("HyperLethal");
        UserAchievement userAchievement = userAchievementRepository.findUserAchievementByUserAndAchievement(user, achievement);
        League league = leagueRepository.findLeagueByTitle("Elysium");
        if (user.getPoints() >= league.getThreshold() && userAchievement == null) {
            userAchievement = new UserAchievement();
            userAchievement.setUser(user);
            userAchievement.setAchievement(achievement);
            userAchievement.setAchieved(true);
            userAchievement.setDateAchieved();
            userAchievement.setTimeAchieved();
            user.setPoints(user.getPoints() + achievement.getPoints());
            user.setLeague(league);
            userRepository.save(user);
            userAchievementRepository.save(userAchievement);
            setNotifications(user, achievement);
        }
    }

    /**
     * This method checks if the user has completed a course in the "Data Science" category. If they have and they haven't already achieved the "Data Wizard" achievement,
     * it creates a new UserAchievement for the user, sets the achievement to "Data Wizard", marks it as achieved, sets the date and time of achievement,
     * adds the achievement points to the user's total points, and saves the user and the UserAchievement in their respective repositories.
     *
     * @param user The user to check for the achievement.
     */
    public void TheChosenOne(MyUser user) {
        Achievement achievement = achievementRepository.findAchievementByTitle("The Chosen One");
        UserAchievement userAchievement = userAchievementRepository.findUserAchievementByUserAndAchievement(user, achievement);
        List<MyUser> Elysium = userRepository.findAllByLeagueId(7);
        List<MyUser> top3 = Elysium.stream()
                .sorted(Comparator.comparingDouble(MyUser::getPoints).reversed())
                .limit(3)
                .toList();
        if (top3.contains(user) && userAchievement == null) {
            userAchievement = new UserAchievement();
            userAchievement.setUser(user);
            userAchievement.setAchievement(achievement);
            userAchievement.setAchieved(true);
            userAchievement.setDateAchieved();
            userAchievement.setTimeAchieved();
            user.setPoints(user.getPoints() + achievement.getPoints());
            userRepository.save(user);
            userAchievementRepository.save(userAchievement);
            setNotifications(user, achievement);
        }
    }

    public void DoubleTrouble(MyUser user) {
        Achievement achievement = achievementRepository.findAchievementByTitle("Double Trouble");
        UserAchievement userAchievement = userAchievementRepository.findUserAchievementByUserAndAchievement(user, achievement);
        List<MyUser> friends = user.getFriends();

        if (!friends.isEmpty() && userAchievement == null) {
            userAchievement = new UserAchievement();
            userAchievement.setUser(user);
            userAchievement.setAchievement(achievement);
            userAchievement.setAchieved(true);
            userAchievement.setDateAchieved();
            userAchievement.setTimeAchieved();
            user.setPoints(user.getPoints() + achievement.getPoints());
            userRepository.save(user);
            userAchievementRepository.save(userAchievement);
            setNotifications(user, achievement);
        }
    }

    public void ReviewConqueror(MyUser user) {
        Achievement achievement = achievementRepository.findAchievementByTitle("Review Conqueror");
        UserAchievement userAchievement = userAchievementRepository.findUserAchievementByUserAndAchievement(user, achievement);
        List<UserComment> userComments = userCommentRepository.findByUser(user);
        if (userComments.size() >= 5 && userAchievement == null) {
            userAchievement = new UserAchievement();
            userAchievement.setUser(user);
            userAchievement.setAchievement(achievement);
            userAchievement.setAchieved(true);
            userAchievement.setDateAchieved();
            userAchievement.setTimeAchieved();
            user.setPoints(user.getPoints() + achievement.getPoints());
            userRepository.save(user);
            userAchievementRepository.save(userAchievement);
            setNotifications(user, achievement);
        }
    }
}
