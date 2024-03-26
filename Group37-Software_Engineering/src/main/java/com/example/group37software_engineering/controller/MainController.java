package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.*;
import com.example.group37software_engineering.repo.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Principal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
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

    @Autowired
    private AchievementRepository achievementRepository;

    @Autowired
    private UserCommentRepository userCommentRepository;

    /**
     * Display the welcome page with top 3 courses.
     *
     * @return The view name for the welcome page.
     */

    @GetMapping("/welcome")
    public String welcomePage(Model model) throws IOException {
        URL url = new URL("https://zenquotes.io/api/random");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();


            String jsonResponse = response.toString();
            String quote = jsonResponse.split("\"q\":\"")[1].split("\",\"a\"")[0];
            String author = jsonResponse.split("\"a\":\"")[1].split("\"}")[0].replaceAll(",\"h\":.*", "").replace("\"", "");
            model.addAttribute("quote", quote);
            model.addAttribute("author", author);
        }

        List<Course> popularity = userCourseRepository.findTop5CoursesWithHighestUsers();
        List<UserComment> top5Comments = userCommentRepository.findTop5ByCommentReview();
        model.addAttribute("top5Courses", popularity);
        model.addAttribute("top5Comments", top5Comments);
        return "welcome";
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
        for (Course course : courseList) {
            List<UserComment> coursecomments = userCommentRepository.findByCourse(course);
            double average = coursecomments.stream().map(UserComment::getComment).mapToDouble(Comment::getReview).average().orElse(0.0);

            DecimalFormat df = new DecimalFormat("#.#");
            average = Double.parseDouble(df.format(average));

            course.setAverageRating(average);
            courseRepository.save(course);
        }
        achievementController.Mirage(user);
        if (courseList.isEmpty()) {
            model.addAttribute("error", "You have no courses.");
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
        List<UserAchievement> sortedUserAchievements = user.getUserAchievements().stream()
                .sorted(Comparator.comparing(UserAchievement::getDateAchieved))
                .toList();
        model.addAttribute("Achieved", sortedUserAchievements);
        model.addAttribute("user", user);
        model.addAttribute("notachieved", achievementController.NotAchieved(user));
        model.addAttribute("Courses_taken", userCourses.size());
        model.addAttribute("Completed", countCompleted(principal.getName()));
        model.addAttribute("Percentage", hoursCompleted(principal.getName()));
        model.addAttribute("Hours", hoursLeft(principal.getName()));
        if(user.getPoints() <=0){

            model.addAttribute("Rank", "N/A");
        }
        else{
            model.addAttribute("Rank", addRankSuffix(getRank(principal.getName())));
        }
        model.addAttribute("Achievements", user.getUserAchievements().size());
        model.addAttribute("TotalAchievements", achievementRepository.count());
        model.addAttribute("friends", user.getFriends().size());
        model.addAttribute("DateJoined", user.getDateJoined());
        try {
            String endpoint1 = "https://api.api-ninjas.com/v1/quotes?category=knowledge";
            String endpoint2 = "https://api.api-ninjas.com/v1/quotes?category=dreams";
            String endpoint3 = "https://api.api-ninjas.com/v1/quotes?category=inspirational";
            String endpoint4 = "https://api.api-ninjas.com/v1/quotes?category=success";
            int randomInt = new Random().nextInt(4);
            URL url;

            switch (randomInt) {
                case 0:
                    url = new URL(endpoint1);
                    break;
                case 1:
                    url = new URL(endpoint2);
                    break;
                case 2:
                    url = new URL(endpoint3);
                    break;
                default:
                    url = new URL(endpoint4);
                    break;
            }
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Api-Key", "movMhmJN0D6VHkp9D7bWbQ==6EG59VfmVFu6L3Hb");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream responseStream = connection.getInputStream();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(responseStream);
                System.out.println(root);
                String quote = root.get(0).get("quote").asText();
                String author = root.get(0).get("author").asText();
                model.addAttribute("Quote", quote);
                model.addAttribute("Author", author);
                responseStream.close();
            } else {
                System.out.println("Failed to fetch quote. Response code: " + responseCode);
                model.addAttribute("Quote", "Failed to fetch quote");
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
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
    public Integer countCompleted(String username) {
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
    public Double hoursCompleted(String username) {
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
    public Double hoursLeft(String username) {
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
    public Integer getRank(String username) {
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

    public String addRankSuffix(int rank) {
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
