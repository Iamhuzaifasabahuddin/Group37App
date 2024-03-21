package com.example.group37software_engineering.controller;


import com.example.group37software_engineering.model.*;
import com.example.group37software_engineering.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;


/**
 * Controller class handling operations related to comments.
 */
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

    @Autowired
    private AchievementController achievementController;

    /**
     * Endpoint to add a comment to a course.
     *
     * @param courseId    The ID of the course.
     * @param description The comment description.
     * @param rating      The rating given to the course.
     * @param principal   The principal representing the currently authenticated user.
     * @return A redirect to the dashboard page.
     */
    @RequestMapping("/addComment")
    public String addComment(@RequestParam int courseId, @RequestParam String description, @RequestParam double rating, Principal principal){
        Course course = courseRepository.findCourseById(courseId);
        MyUser user = userRepository.findByUsername(principal.getName());
        UserCourses userCourse = userCourseRepository.findByUserAndCourse(user, course);
        Comment comment = new Comment();
        comment.setDescription(description);
        comment.setReview(rating);

        UserComment userComment = new UserComment();
        commentRepository.save(comment);
        userComment.setComment(comment);
        userComment.setCourse(course);
        userComment.setUser(user);
        userComment.setDateCommented();

        userCourse.setCommented(true);

        userCourseRepository.save(userCourse);
        userCommentRepository.save(userComment);
        achievementController.ReviewConqueror(user);
        return "redirect:/dashboard";
    }
}