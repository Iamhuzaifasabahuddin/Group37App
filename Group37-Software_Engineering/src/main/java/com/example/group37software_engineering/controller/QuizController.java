package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.Course;
import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.model.Question;
import com.example.group37software_engineering.repo.CourseRepository;
import com.example.group37software_engineering.repo.QuestionRepository;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
public class QuizController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/quiz")
    public String getQuiz(@RequestParam int courseId, Model model) {
        Course course = courseRepository.findCourseById(courseId);
        model.addAttribute("questions", course.getQuestions());
        return "quiz";
    }

    @PostMapping("/completeQuiz")
    public String completeQuiz(@RequestBody String body, Model model) {
        List<String> values = Arrays.asList(body.split("&"));
        int correct = 0;
        for (String value : values) {
            if (!value.startsWith("_csrf")) {
                String[] keyValue = value.split("=");
                int questionId = Integer.parseInt(keyValue[0]);
                String answer = keyValue[1];
                Question question = questionRepository.findById(questionId);
                if (Objects.equals(answer, question.getAnswer())) {
                    correct += 1;
                }
            }
        }
        double score = correct * 100.0 / (values.size() - 1);
        model.addAttribute("score", score);
        return "quiz";
    }
}
