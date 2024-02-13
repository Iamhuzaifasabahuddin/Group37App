package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.Course;
import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.model.Question;
import com.example.group37software_engineering.model.Quiz;
import com.example.group37software_engineering.repo.CourseRepository;
import com.example.group37software_engineering.repo.QuestionRepository;
import com.example.group37software_engineering.repo.QuizRepository;
import com.example.group37software_engineering.repo.UserRepository;
import jakarta.validation.Valid;
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

    @Autowired
    private QuizRepository quizRepository;

    @GetMapping("/quiz")
    public String getQuiz(@RequestParam int courseId, Model model) {
        Course course = courseRepository.findCourseById(courseId);
        model.addAttribute("quiz", new Quiz());
        model.addAttribute("courseQuiz", course.getQuiz());
        model.addAttribute("name", course.getTitle());
        return "quiz";
    }

    @PostMapping("/completeQuiz")
    public String completeQuiz(@Valid @ModelAttribute Quiz quiz, Model model) {
        int correct = 0;
        Quiz courseQuiz = quizRepository.findById(quiz.getId());
        for (int i = 0; i < courseQuiz.getQuestions().size(); i++) {
            if (Objects.equals(courseQuiz.getQuestions().get(i).getAnswer(), quiz.getQuestions().get(i).getAnswer())) {
                correct += 1;
            }
        }
        double score = correct * 100.0 / courseQuiz.getQuestions().size();
        model.addAttribute("score", score);
        return "quiz";
    }
}
