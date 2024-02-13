package com.example.group37software_engineering;

import com.example.group37software_engineering.model.Course;
import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.model.Question;
import com.example.group37software_engineering.model.Quiz;
import com.example.group37software_engineering.repo.CourseRepository;
import com.example.group37software_engineering.repo.QuestionRepository;
import com.example.group37software_engineering.repo.QuizRepository;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Group37SoftwareEngineeringApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private CourseData courseData;

    @Autowired
    private QuestionsData questionsData;

    public static void main(String[] args) {
        SpringApplication.run(Group37SoftwareEngineeringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        courseData.readDataAndSaveToRepo("courses_data.csv");
        questionsData.importQuestionsFromCSV("questions.csv");

//        Test data, to be removed after csv of questions are made
//        Course c = courseRepository.findCourseById(1);
//        Question q1 = new Question();
//        Quiz q = new Quiz();
//        q1.setPrompt("What is 1+1?");
//        q1.setOptions(Arrays.asList("1", "2", "3", "4"));
//        q1.setAnswer("2");
//        Question q2 = new Question();
//        q2.setPrompt("What is the smallest multiple of 1, 2, 3, ..., 9, 10?");
//        q2.setOptions(Arrays.asList("2520", "3628800", "10", "idk"));
//        q2.setAnswer("2520");
//        q.setQuestions(new ArrayList<>());
//        q.getQuestions().addAll(Arrays.asList(q1, q2));
//        c.setQuiz(q);
//        quizRepository.save(q);
//        courseRepository.save(c);
    }
    @Bean
    public ErrorPageRegistrar errorPageRegistrar() {
        return registry -> {
            registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
        };
    }
}
