package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.Course;
import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.model.UserCourses;
import com.example.group37software_engineering.repo.CourseRepository;
import com.example.group37software_engineering.repo.UserCourseRepository;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Controller class for managing main application functionality, including user dashboard and profile.
 */
@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Autowired
    private AchievementController achievementController;
    /**
     * Handles the home page, displaying user information from OAuth2 authentication.
     *
     * @param oAuth2AuthenticationToken The OAuth2 authentication token.
     * @param model                     The model to add attributes to.
     * @return The view name for the OAuth2 home page.
     */
    @GetMapping("/")
    public String homePage(OAuth2AuthenticationToken oAuth2AuthenticationToken, Model model) {
        model.addAttribute("user", oAuth2AuthenticationToken.getPrincipal().getName());
        model.addAttribute("username", oAuth2AuthenticationToken.getPrincipal().getAttributes().get("given_name"));
        return "oauth";

    }

    /**
     * Handles displaying the user dashboard.
     * Retrieves user information, user courses, and displays relevant information.
     *
     * @param model     The model to add attributes to.
     * @param principal The currently logged-in user.
     * @return The view name for the user dashboard.
     */
    @GetMapping("/dashboard")
    public String getDashboard(Model model, Principal principal) {
        MyUser user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        List<UserCourses> userCourses = userCourseRepository.findByUser(user);
        List<Course> courseList = userCourses.stream()
                .map(UserCourses::getCourse)
                .toList();
        achievementController.Mirage(user);
        if (courseList.isEmpty()) {
            model.addAttribute("error", "ERROR 404!");
        } else {
            model.addAttribute("courseList", courseList);
            model.addAttribute("userCourses", userCourses);
        }
        return "dashboard";
    }

    /**
     * Handles displaying the user profile.
     * Retrieves user information, user courses, and calculates profile statistics.
     *
     * @param model     The model to add attributes to.
     * @param principal The currently logged-in user.
     * @return The view name for the user profile.
     */
    @GetMapping("/profile")
    public String getProfile(Model model, Principal principal) {
        MyUser user = userRepository.findByUsername(principal.getName());
        List<UserCourses> userCourses = userCourseRepository.findByUser(user);
        model.addAttribute("user", user);
        model.addAttribute("Courses_taken", userCourses.size());
        model.addAttribute("Completed", countCompleted(principal.getName()));
        model.addAttribute("Percentage", hoursCompleted(principal.getName()));
        model.addAttribute("Hours", hoursLeft(principal.getName()));
        if (user.getPoints() == 0) {
            model.addAttribute("Rank", "Unranked");
        } else {
            model.addAttribute("Rank", addRankSuffix(getRank(principal.getName())));
        }
        model.addAttribute("Points", user.getPoints());
        model.addAttribute("league", user.getLeague().getImageUrl());
        return "profile";
    }

    /**
     * Handles the custom 404 error redirection.
     *
     * @return The redirection URL to the user dashboard.
     */
    @RequestMapping("/404")
    public String handle404() {
        return "redirect:/dashboard";
    }

    @RequestMapping("/400")
    public String handle400() {
        return "redirect:/dashboard";
    }

    @RequestMapping("/500")
    public String handle500() {
        return "redirect:/dashboard";
    }

    @RequestMapping("/405")
    public String handle405() {
        return "redirect:/dashboard";
    }

    /**
     * Counts the number of completed courses for a given user.
     *
     * @param username The username of the user.
     * @return The count of completed courses.
     */
    private Integer countCompleted(String username) {
        MyUser user = userRepository.findByUsername(username);
        List<UserCourses> userCourses = userCourseRepository.findByUser(user);
        if (user != null) {
            List<UserCourses> userCourses1 = userCourses;

            if (userCourses1 != null) {
                int total = 0;

                for (UserCourses c : userCourses1) {
                    if (c.getPercentage() >= 80) {
                        total += 1;
                    }
                }
                return total;
            }
        }
        return 0;
    }

    /**
     * Calculates the completion percentage of courses for a given user.
     *
     * @param username The username of the user.
     * @return The completion percentage of courses.
     */
    private Double hoursCompleted(String username) {
        MyUser user = userRepository.findByUsername(username);
        List<UserCourses> userCourses = userCourseRepository.findByUser(user);

        if (user != null && !userCourses.isEmpty()) {
            int completedCoursesCount = 0;
            for (UserCourses uc : userCourses) {
                if (uc.getPercentage() >= 80) {
                    completedCoursesCount++;
                }
            }

            double completionPercentage = (completedCoursesCount * 100.0) / userCourses.size();
            return completionPercentage;
        }
        return 0.0;

    }

    /**
     * Calculates the remaining hours for courses not yet completed by a given user.
     *
     * @param username The username of the user.
     * @return The remaining hours for courses.
     */
    private Double hoursLeft(String username) {
        MyUser user = userRepository.findByUsername(username);
        if (user == null) {
            return 0.0;
        }

        List<UserCourses> userCourses = userCourseRepository.findByUser(user);
        double totalHours = 0.0;
        double completedHours = 0.0;

        for (UserCourses uc : userCourses) {
            Course course = uc.getCourse();
            totalHours += course.getDuration();

            if (uc.getPercentage() >= 80) {
                completedHours += course.getDuration();
            }
        }


        double remainingHours = totalHours - completedHours;
        return remainingHours;
    }

    /**
     * Retrieves the rank of a user based on their username.
     *
     * @param username The username of the user whose rank is to be retrieved.
     * @return The rank of the user if found, or null if the user is not found in the leaderboard.
     */
    private Integer getRank(String username) {
        List<MyUser> myUsers = StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .sorted(Comparator.comparingDouble(MyUser::getPoints).reversed())
                .toList();

        int rank = 0;
        for (MyUser myUser : myUsers) {
            rank++;
            if (myUser.getUsername().equals(username)) {
                return rank;
            }
        }
        return null;
    }

    private String addRankSuffix(int rank) {
        String suffix;
        int lastDigit = rank % 10;
        int lastTwoDigits = rank % 100;

        if (lastTwoDigits >= 11 && lastTwoDigits <= 13) {
            suffix = "th";
        } else {
            suffix = switch (lastDigit) {
                case 1 -> "st";
                case 2 -> "nd";
                case 3 -> "rd";
                default -> "th";
            };
        }

        return rank + suffix;
    }

    private Integer userCountByCourseCategory(String category) {
        List<Course> courses = courseRepository.findAllByCategory(category);
        Set<Integer> userIds = courses.stream()
                .flatMap(course -> course.getUserCourses().stream())
                .map(userCourse -> userCourse.getUser().getId())
                .collect(Collectors.toSet());

        return userIds.size();
    }
}
