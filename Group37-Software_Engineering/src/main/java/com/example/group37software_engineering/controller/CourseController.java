package com.example.group37software_engineering.controller;
import com.example.group37software_engineering.model.Course;
import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.repo.CourseRepository;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@Controller
public class CourseController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;
    @GetMapping("/courses")
    public String getCourse(Model model, Principal principal) {
        MyUser user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("courseList", courseRepository.findAll());
        return "Course/courses";
    }
    @RequestMapping("/enroll")
    public String enroll(@RequestParam int course, Principal principal, Model model) {
        MyUser user = userRepository.findByUsername(principal.getName());
        Course c = courseRepository.findCourseById(course);
        if (c != null && !user.getCourse().contains(c)) {
            user.getCourse().add(c);
            userRepository.save(user);
            c.getUsers().add(user);
            courseRepository.save(c);
            return "redirect:/dashboard";
        }
        model.addAttribute("error", "You already have enrolled in this course!");
        model.addAttribute("user", user);
        model.addAttribute("courseList", courseRepository.findAll());
        return "Course/courses";
    }

    @RequestMapping("/starttime")
    public String getStartTime(@RequestParam int courseId, Principal principal) {
        MyUser user = userRepository.findByUsername(principal.getName());
        Course course = courseRepository.findCourseById(courseId);
        if (course != null && user != null && user.getCourse().contains(course)) {
            for (Course userCourse : user.getCourse()) {
                if (userCourse.getId().equals(course.getId())) {
                    userCourse.setStartTime();
                    userCourse.setStartDate();
                    userRepository.save(user);
                    courseRepository.save(userCourse);
                }
            }
        }
        return "redirect:/dashboard";
    }


}
