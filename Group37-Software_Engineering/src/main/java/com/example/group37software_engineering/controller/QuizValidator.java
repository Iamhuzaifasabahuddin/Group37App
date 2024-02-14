package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.model.Question;
import com.example.group37software_engineering.model.Quiz;
import com.example.group37software_engineering.repo.QuizRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator class for validating Quiz objects.
 * Ensures that all questions are answered in a submitted quiz.
 */
public class QuizValidator implements Validator {
    private QuizRepository quizRepository;

    /**
     * Constructor for QuizValidator.
     *
     * @param quizRepository The repository for accessing Quiz data.
     */
    public QuizValidator(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    /**
     * Check if this Validator supports the given class.
     *
     * @param clazz The class to be validated.
     * @return True if the class is supported; false otherwise.
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return Quiz.class.equals(clazz);
    }

    /**
     * Validate the given object, applying constraints defined in this validator.
     *
     * @param target The object to be validated.
     * @param errors The errors instance to record validation errors.
     */
    @Override
    public void validate(Object target, Errors errors) {
        Quiz quizAnswers = (Quiz) target;
        Quiz quiz = quizRepository.findById(quizAnswers.getId());
        if (quizAnswers.getQuestions() == null || quizAnswers.getQuestions().size() != quiz.getQuestions().size()) {
            errors.rejectValue("questions", "", "Answer the questions");
            return;
        }

        for (int i = 0; i < quiz.getQuestions().size(); i++) {
            if (quizAnswers.getQuestions().get(i).getAnswer() == null) {
                errors.rejectValue("questions", "", "Answer all questions");
                return;
            }
        }
    }
}
