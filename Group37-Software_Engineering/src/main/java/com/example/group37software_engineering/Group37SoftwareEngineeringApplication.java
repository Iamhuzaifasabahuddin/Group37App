package com.example.group37software_engineering;

import com.example.group37software_engineering.model.Course;
import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.model.Question;
import com.example.group37software_engineering.model.Quiz;
import com.example.group37software_engineering.repo.*;
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
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private CourseData courseData;

    @Autowired
    private QuestionsData questionsData;

    @Autowired LeagueData leagueData;

    @Autowired
    private AchievementsData achievementsData;

    @Autowired
    private AchievementRepository AchievementRepository;

    public static void main(String[] args) {
        SpringApplication.run(Group37SoftwareEngineeringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if(courseRepository.count() == 0){
            courseData.readDataAndSaveToRepo("courses_data.csv");
        }
        if(quizRepository.count() == 0){
            questionsData.importQuestionsFromCSV("questions.csv");
        }
        if(leagueRepository.count() == 0){
            leagueData.readCSVAndSaveToRepo("leagues.csv");
        }

        if(AchievementRepository.count() == 0){
            achievementsData.readCSVAndSaveToRepo("achievements.csv");
        }
    }

    @Bean
    public ErrorPageRegistrar errorPageRegistrar() {
        return registry -> {
            registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"),
                    new ErrorPage(HttpStatus.BAD_REQUEST, "/400"),
                    new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500"),
                    new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/405"));
        };
    }

}
