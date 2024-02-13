package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.Course;
import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.model.UserCourses;
import com.example.group37software_engineering.repo.CourseRepository;
import com.example.group37software_engineering.repo.UserCourseRepository;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserCourseRepository userCourseRepository;

//    @GetMapping("/courses")
//    public String getCourse(Model model, Principal principal) {
//        MyUser user = userRepository.findByUsername(principal.getName());
//        model.addAttribute("user", user);
//        List<Course> allCourses = (List<Course>) courseRepository.findAll();
//        List<Course> coursesNotEnrolled = new ArrayList<>();
//        for (Course course : allCourses) {
//            if (!course.getUserCourses().contains(user)) {
//                coursesNotEnrolled.add(course);
//            }
//        }
//        model.addAttribute("courseList", coursesNotEnrolled);
//        return "Course/courses";
//    }

//    @RequestMapping("/enroll")
//    public String enroll(@RequestParam int course, Principal principal, Model model) {
//        MyUser user = userRepository.findByUsername(principal.getName());
//        Course c = courseRepository.findCourseById(course);
//        if (c != null && !user.getCourse().contains(c)) {
//            user.getCourse().add(c);
//            userRepository.save(user);
//            c.getUsers().add(user);
//            return "redirect:/dashboard";
//        }
//        model.addAttribute("error", "You already have enrolled in this course!");
//        model.addAttribute("user", user);
//        model.addAttribute("courseList", courseRepository.findAll());
//        return "Course/courses";
//    }

    //    @RequestMapping("/starttime")
//    public String getStartTime(@RequestParam int courseId, Principal principal) {
//        MyUser user = userRepository.findByUsername(principal.getName());
//        Course course = courseRepository.findCourseById(courseId);
//        if (course != null && user != null && user.getCourse().contains(course)) {
//            for (Course userCourse : user.getCourse()) {
//                if (userCourse.getId().equals(course.getId())) {
//                    userCourse.setStartTime();
//                    userCourse.setStartDate();
//                }
//            }
//            userRepository.save(user);
//        }
//        return "redirect:/dashboard";
//    }



    @GetMapping("/courses")
    public String getCourse(Model model, Principal principal) {
        MyUser user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        List<Course> allCourses = (List<Course>) courseRepository.findAll();
        List<Course> coursesNotEnrolled = new ArrayList<>();
        for (Course course : allCourses) {
            if (isUserEnrolledInCourse(user, course)) {
                coursesNotEnrolled.add(course);
            }
        }
        model.addAttribute("courseList", coursesNotEnrolled);
        return "Course/courses";
    }


    @RequestMapping("/enroll")
    public String enroll(@RequestParam int course, Principal principal, Model model) {
        MyUser user = userRepository.findByUsername(principal.getName());
        Course c = courseRepository.findCourseById(course);
        if (c != null && isUserEnrolledInCourse(user, c)) {
            UserCourses userCourse = new UserCourses();
            userCourse.setUser(user);
            userCourse.setCourse(c);
            userCourseRepository.save(userCourse);
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
        UserCourses userCourse = userCourseRepository.findByUserAndCourse(user, course);
        if (userCourse != null) {
            userCourse.setStartTime();
            userCourse.setStartDate();
            userCourseRepository.save(userCourse);
        }
        return "redirect:/dashboard";
    }

    private boolean isUserEnrolledInCourse(MyUser user, Course course) {
        return user.getUserCourses().stream()
                .noneMatch(uc -> uc.getCourse().equals(course));
    }

}
