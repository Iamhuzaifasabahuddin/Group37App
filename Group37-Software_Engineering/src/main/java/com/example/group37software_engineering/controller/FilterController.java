package com.example.group37software_engineering.controller;


import com.example.group37software_engineering.model.Course;
import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.repo.CourseRepository;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class FilterController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;

    @RequestMapping(value = "/filter")
    public String getFilter(@RequestParam String category, Model model, Principal principal) {
        MyUser user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("courseList", courseRepository.findAllByCategory(category));
        return "Course/courses";

    }

    @RequestMapping(value = "/search")
    public String getSearch(@RequestParam String searchTerm, Model model, Principal principal) {
        MyUser user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        if(searchTerm != null && !searchTerm.isBlank()){
            model.addAttribute("course", courseRepository.findCoursesByTitle(searchTerm));

        }
        model.addAttribute("Courseerror", "No such course found!");
        return "Course/courses";
    }

    @RequestMapping(value = "/duration")
    public String getDuration(@RequestParam double duration, Model model, Principal principal) {
        List<Course> sortedCourses = courseRepository.findAllByDurationGreaterThanEqual(duration)
                .stream()
                .sorted(Comparator.comparingDouble(Course::getDuration))
                .toList();
        MyUser user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("courseList",sortedCourses);
        return "Course/courses";
    }

}
