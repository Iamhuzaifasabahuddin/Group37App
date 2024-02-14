package com.example.group37software_engineering;
import com.example.group37software_engineering.model.Course;
import com.example.group37software_engineering.model.Question;
import com.example.group37software_engineering.model.Quiz;
import com.example.group37software_engineering.repo.CourseRepository;
import com.example.group37software_engineering.repo.QuestionRepository;
import com.example.group37software_engineering.repo.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionsData {

    private QuestionRepository questionRepository;

    private QuizRepository quizRepository;

    private CourseRepository courseRepository;

    @Autowired
    public QuestionsData(QuestionRepository questionRepository, QuizRepository quizRepository, CourseRepository courseRepository) {

        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
        this.courseRepository = courseRepository;

    }

    /**
     * Imports questions from a CSV file and associates them with quizzes and courses.
     *
     * @param csvFilePath The path to the CSV file containing question data.
     */
    public void importQuestionsFromCSV(String csvFilePath) {

        List<Question> questions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new ClassPathResource(csvFilePath).getInputStream()))) {
            String line;
            int i = 1;
            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");
                if (data.length >= 3) { // Assuming CSV format: prompt, answer, list of options

                    Question question = new Question();
                    question.setPrompt(data[0]);
                    question.setAnswer(data[1]);

                    // Parse the list of options
                    List<String> options = parseOptions(data[data.length - 1]);

                    question.setOptions(options);

                    questions.add(question);



                    switch (i) {

                        case (5):

                            Quiz quiz = new Quiz();
                            quiz.setQuestions(questions);
                            for (int j = 0; j < 5; j++) {
                                questions.get(j).setQuiz(quiz);
                            }
                            quizRepository.save(quiz);
                            for (int j = 1; j<=4; j++) {

                                Course course = courseRepository.findCourseById(j);
                                course.setQuiz(quiz);
                                courseRepository.save(course);

                            }
                            questions = new ArrayList<>();
                            break;

                        case (10):

                            Quiz quiz1 = new Quiz();
                            for (int j = 0; j < 5; j++) {
                                questions.get(j).setQuiz(quiz1);
                            }
                            quiz1.setQuestions(questions);
                            quizRepository.save(quiz1);
                            for (int j = 5; j<=10; j++) {

                                Course course = courseRepository.findCourseById(j);
                                course.setQuiz(quiz1);
                                courseRepository.save(course);

                            }
                            questions = new ArrayList<>();
                            break;

                        case (15):

                            Quiz quiz2 = new Quiz();
                            for (int j = 0; j < 5; j++) {
                                questions.get(j).setQuiz(quiz2);
                            }
                            quiz2.setQuestions(questions);
                            quizRepository.save(quiz2);
                            for (int j = 11; j<=14; j++) {

                                Course course = courseRepository.findCourseById(j);
                                course.setQuiz(quiz2);
                                courseRepository.save(course);

                            }
                            questions = new ArrayList<>();
                            break;

                        case (20):

                            Quiz quiz3 = new Quiz();
                            for (int j = 0; j < 5; j++) {
                                questions.get(j).setQuiz(quiz3);
                            }
                            quiz3.setQuestions(questions);
                            quizRepository.save(quiz3);
                            for (int j = 15; j<=17; j++) {

                                Course course = courseRepository.findCourseById(j);
                                course.setQuiz(quiz3);
                                courseRepository.save(course);

                            }
                            questions = new ArrayList<>();
                            break;

                        case (25):

                            Quiz quiz4 = new Quiz();
                            for (int j = 0; j < 5; j++) {
                                questions.get(j).setQuiz(quiz4);
                            }
                            quiz4.setQuestions(questions);
                            quizRepository.save(quiz4);
                            for (int j = 18; j<=18; j++) {

                                Course course = courseRepository.findCourseById(j);
                                course.setQuiz(quiz4);
                                courseRepository.save(course);

                            }
                            questions = new ArrayList<>();
                            break;

                    }

                    i++;

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle IOException (e.g., log, throw a custom exception)
        }

//        // Save the questions to the repository

    }

    /**
     * Parses options from a delimited string and returns a list.
     *
     * @param options The delimited string containing options.
     * @return The list of options.
     */
    private List<String> parseOptions(String options) {
        return Arrays.asList(options.split(";"));
    }

}
