package com.example.group37software_engineering.controller;


import com.example.group37software_engineering.model.Course;
import com.example.group37software_engineering.repo.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class FilterController {

    @Autowired
    private CourseRepository courseRepository;

    @RequestMapping(value = "/filter")
    public String getFilter(@RequestParam String category, Model model){
        model.addAttribute("Filtered", courseRepository.findAllByCategory(category));
        return "Course/filter";

    }

    @RequestMapping(value = "/search")
    public String getSearch(@RequestParam String searchTerm, Model model) {
        model.addAttribute("course", courseRepository.findCoursesByTitle(searchTerm));
        return "Course/search";
    }

    @RequestMapping(value = "/duration")
    public String getDuration(@RequestParam double duration, Model model) {
        List<Course> sortedCourses = courseRepository.findAllByDurationGreaterThanEqual(duration)
                .stream()
                .sorted(Comparator.comparingDouble(Course::getDuration))
                .toList();
        model.addAttribute("CourseList",sortedCourses);
        return "Course/duration";
    }

}
