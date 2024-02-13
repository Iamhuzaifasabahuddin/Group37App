package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.Course;
import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.repo.CourseRepository;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/")
    public String homePage(OAuth2AuthenticationToken oAuth2AuthenticationToken, Model model) {
        model.addAttribute("user", oAuth2AuthenticationToken.getPrincipal().getName());
        model.addAttribute("username", oAuth2AuthenticationToken.getPrincipal().getAttributes().get("given_name"));
        return "oauth";

    }

    @GetMapping("/profile")
    public String getProfile(Model model, Principal principal) {
        MyUser user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("Courses_taken", user.getCourse().size());
        model.addAttribute("Completed", countCompleted(principal.getName()));
        model.addAttribute("Percentage", hoursCompleted(principal.getName()));
        model.addAttribute("Hours", hoursLeft(principal.getName()));
        return "profile";
    }

    @GetMapping("/admin")
    public String getadmin(Model model, Principal principal) {
        MyUser user = userRepository.findByUsername(principal.getName());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("firstName", user.getFirstname());
        model.addAttribute("lastName", user.getLastname());
        model.addAttribute("AIU", courseRepository.findAllUsersByCourseCategory("Artificial Intelligence"));
        model.addAttribute("CU", courseRepository.findAllUsersByCourseCategory("Cloud"));
        model.addAttribute("DS", courseRepository.findAllUsersByCourseCategory("Data Science"));
        model.addAttribute("CS", courseRepository.findAllUsersByCourseCategory("Cybersecurity"));
        model.addAttribute("SU", courseRepository.findAllUsersByCourseCategory("Sustainability"));
        model.addAttribute("AI", courseRepository.findAllUsersByCourseCategory("Artificial Intelligence").size());
        model.addAttribute("Cloud", courseRepository.findAllUsersByCourseCategory("Cloud").size());
        model.addAttribute("DataScience", courseRepository.findAllUsersByCourseCategory("Data Science").size());
        model.addAttribute("CyberSecurity", courseRepository.findAllUsersByCourseCategory("Cybersecurity").size());
        model.addAttribute("Sustainability", courseRepository.findAllUsersByCourseCategory("Sustainability").size());

        return "admin";
    }

    @RequestMapping("/404")
    public String handle404() {
        return "redirect:/dashboard";
    }

    private Integer countCompleted(String username) {
        MyUser user = userRepository.findByUsername(username);
        if (user != null) {
            List<Course> courses = user.getCourse();

            if (courses != null) {
                int total = 0;

                for (Course c : courses) {
                    if (c != null && c.isCompleted()) {
                        total += 1;
                    }
                }

                return total;
            }
        }
        return 0;
    }

    private Double hoursCompleted(String username) {
        MyUser user = userRepository.findByUsername(username);
        if (user != null) {
            List<Course> courses = user.getCourse();

            if (courses != null) {
                double percent = 0;

                for (Course c : courses) {
                    percent += c.getPercentage();
                }
                return percent;
            }
        }
        return 0.0;
    }

    private Double hoursLeft(String username) {
        MyUser user = userRepository.findByUsername(username);
        if (user != null) {
            List<Course> courses = user.getCourse();

            if (courses != null) {
                double hours = 0;

                for (Course c : courses) {
                    hours += c.getDuration();

                }
                return hours;
            }
        }
        return 0.0;
    }


}
