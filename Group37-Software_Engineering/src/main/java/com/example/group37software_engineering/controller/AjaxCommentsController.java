package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.Course;
import com.example.group37software_engineering.model.UserComment;
import com.example.group37software_engineering.repo.CourseRepository;
import com.example.group37software_engineering.repo.UserCommentRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
public class AjaxCommentsController {

    @Autowired
    private UserCommentRepository userCommentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("comments")
    public ResponseEntity<?> getComments(@RequestParam int courseId) {
        Course course = courseRepository.findCourseById(courseId);
        List<UserComment> comments = userCommentRepository.findByCourse(course);
        comments.sort(Comparator.comparing(userComment -> userComment.getComment().getReview(), Comparator.reverseOrder()));
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        System.out.println("{\"comments\":" + gson.toJson(comments) + "}");
        return ResponseEntity.ok().body("{\"comments\":" + gson.toJson(comments) + "}");
    }
}
