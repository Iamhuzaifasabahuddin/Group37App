package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.Course;
import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.repo.CourseRepository;
import com.example.group37software_engineering.repo.UserCourseRepository;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * Controller class for handling filtering and searching of courses.
 */
@Controller
public class FilterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserCourseRepository userCourseRepository;

    /**
     * Handle filtering of courses based on category.
     *
     * @param category  The category to filter courses by.
     * @param model     The model to add attributes to.
     * @param principal The currently logged-in user.
     * @return The view name for displaying filtered courses.
     */
    @RequestMapping(value = "/filter")
    public String getFilter(@RequestParam String category, Model model, Principal principal) {
        MyUser user = userRepository.findByUsername(principal.getName());
        List<Course> filtered = courseRepository.findAllByCategory(category);
        List<Course> coursesNotEnrolled = new ArrayList<>();
        for (Course course : filtered) {
            if (!isUserEnrolledInCourse(user, course)) {
                coursesNotEnrolled.add(course);
            }
        }
        if(coursesNotEnrolled.isEmpty()){
            model.addAttribute("CourseError", "No such course found!");
        }
        model.addAttribute("user", user);
        model.addAttribute("courseList", coursesNotEnrolled);
        return "Course/courses";
    }

    /**
     * Handle searching for courses by title.
     *
     * @param searchTerm The term to search for.
     * @param model      The model to add attributes to.
     * @param principal  The currently logged-in user.
     * @return The view name for displaying search results.
     */
    @RequestMapping(value = "/search")
    public String getSearch(@RequestParam String searchTerm, Model model, Principal principal) {
        MyUser user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        List<Course> searched = courseRepository.findCoursesByTitleContaining(searchTerm.trim());
        List<Course> coursesNotEnrolled = new ArrayList<>();
        for (Course course : searched) {
            if (!isUserEnrolledInCourse(user, course)) {
                coursesNotEnrolled.add(course);
            }
        }
        if(coursesNotEnrolled.isEmpty()){
            model.addAttribute("CourseError", "No such course found!");
        }
        model.addAttribute("courseList", coursesNotEnrolled);
        return "Course/courses";
    }

    /**
     * Handle filtering courses by duration.
     *
     * @param duration  The minimum duration to filter courses by.
     * @param model     The model to add attributes to.
     * @param principal The currently logged-in user.
     * @return The view name for displaying filtered courses.
     */
    @RequestMapping(value = "/duration")
    public String getDuration(@RequestParam double duration, Model model, Principal principal) {
        MyUser user = userRepository.findByUsername(principal.getName());
        List<Course> sortedCourses = courseRepository.findAllByDurationGreaterThanEqual(duration)
                .stream()
                .sorted(Comparator.comparingDouble(Course::getDuration))
                .toList();
        List<Course> coursesNotEnrolled = new ArrayList<>();
        for (Course course : sortedCourses) {
            if (!isUserEnrolledInCourse(user, course)) {
                coursesNotEnrolled.add(course);
            }
        }
        if(coursesNotEnrolled.isEmpty()){
            model.addAttribute("CourseError", "No such course found!");
        }
        model.addAttribute("user", user);
        model.addAttribute("courseList", coursesNotEnrolled);
        return "Course/courses";
    }

    private boolean isUserEnrolledInCourse(MyUser user, Course course) {
        return user.getUserCourses().stream()
                .anyMatch(uc -> uc.getCourse().equals(course));
    }
}
