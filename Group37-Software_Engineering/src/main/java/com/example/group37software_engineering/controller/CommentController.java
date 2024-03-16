package com.example.group37software_engineering.controller;


import com.example.group37software_engineering.model.*;
import com.example.group37software_engineering.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;

@Controller
public class CommentController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserCommentRepository userCommentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserCourseRepository userCourseRepository;

    @GetMapping("/comment")
    public String Comment(@RequestParam int courseId, Model model){
        Course course = courseRepository.findCourseById(courseId);
        model.addAttribute("comment", new Comment());
        model.addAttribute("courseId", course.getId());
        return "comment";
    }

    @RequestMapping("/addComment")
    public String addComment(@RequestParam int courseId, @ModelAttribute Comment comment, Principal principal){
        Course course = courseRepository.findCourseById(courseId);
        MyUser user = userRepository.findByUsername(principal.getName());
        UserCourses userCourse = userCourseRepository.findByUserAndCourse(user, course);
        UserComment userComment = new UserComment();
        commentRepository.save(comment);
        userComment.setComment(comment);
        userComment.setCourse(course);
        userComment.setUser(user);
        userComment.setDateCommented(LocalDate.now());
        userCourse.setCommented(true);
        userCourseRepository.save(userCourse);
        userCommentRepository.save(userComment);
        return "redirect:/dashboard";
    }
}
