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
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Controller class handling AJAX requests related to comments retrieval.
 */
@RestController
public class AjaxCommentsController {

    @Autowired
    private UserCommentRepository userCommentRepository;

    @Autowired
    private CourseRepository courseRepository;

    /**
     * Endpoint to retrieve comments for a particular course.
     *
     * @param courseId The ID of the course for which comments are retrieved.
     * @return ResponseEntity containing JSON data of comments for the course.
     */
    @GetMapping("comments")
    public ResponseEntity<?> getComments(@RequestParam int courseId) {
        Course course = courseRepository.findCourseById(courseId);
        List<UserComment> comments = userCommentRepository.findByCourse(course);
        comments.sort(Comparator.comparing(userComment -> userComment.getComment().getReview(), Comparator.reverseOrder()));
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        return ResponseEntity.ok().body("{\"comments\":" + gson.toJson(comments) + "}");
    }


    @GetMapping("/checkProfanity")
    public ResponseEntity<?> checkProfanity(@RequestParam String comment) throws IOException, InterruptedException {
        String encodedComment = URLEncoder.encode(comment, StandardCharsets.UTF_8);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://community-purgomalum.p.rapidapi.com/containsprofanity?text=" + encodedComment))
                .header("X-RapidAPI-Key", "f6826816bbmsh80d2fb3930802c5p11af9bjsn94c3610517e4")
                .header("X-RapidAPI-Host", "community-purgomalum.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return ResponseEntity.ok().body(response.body());
    }
}
