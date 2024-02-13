package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.model.Question;
import com.example.group37software_engineering.model.Quiz;
import com.example.group37software_engineering.repo.QuizRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class QuizValidator implements Validator {
    private QuizRepository quizRepository;

    public QuizValidator(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Quiz.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Quiz quizAnswers = (Quiz) target;
        if (quizAnswers.getQuestions() == null) {
            errors.rejectValue("questions", "", "Answer the questions");
            return;
        }
        Quiz quiz = quizRepository.findById(quizAnswers.getId());
        for (int i = 0; i < quiz.getQuestions().size(); i++) {
            if (quizAnswers.getQuestions().get(i).getAnswer() == null) {
                errors.rejectValue("questions", "", "Answer all questions");
                return;
            }
        }
    }
}
