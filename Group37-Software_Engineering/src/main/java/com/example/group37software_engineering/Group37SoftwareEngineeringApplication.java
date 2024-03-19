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
        if (courseRepository.count() == 0) {
            courseData.readDataAndSaveToRepo("courses_data.csv");
        }
        if (quizRepository.count() == 0) {
            questionsData.importQuestionsFromCSV("questions.csv");
        }
        if (leagueRepository.count() == 0) {
            leagueData.readCSVAndSaveToRepo("leagues.csv");
        }

        if (AchievementRepository.count() == 0) {
            achievementsData.readCSVAndSaveToRepo("achievements.csv");
        }

        if (userRepository.count() == 0) {
            for (int i = 0; i < 2; i++) {
                MyUser user = new MyUser();
                user.setUsername(String.format("test%d", i));
                user.setEmailVerificationStatus(true);
                user.setPassword(passwordEncoder.encode("password"));
                user.setFirstname(String.format("Test%d", i));
                user.setLastname(String.format("%d", i));
                user.setLeague(leagueRepository.findLeagueByTitle("Unranked"));
                userRepository.save(user);
            }

            for (int i = 0; i < 2; i++) {
                MyUser user = new MyUser();
                user.setUsername(String.format("Bronze%d", i));
                user.setEmailVerificationStatus(true);
                user.setPassword(passwordEncoder.encode("password"));
                user.setFirstname(String.format("Bronze%d", i));
                user.setLastname(String.format("%d", i));
                user.setPoints(1000);
                user.setLeague(leagueRepository.findLeagueByTitle("Bronze Scholar"));
                userRepository.save(user);
            }

            for (int i = 0; i < 2; i++) {
                MyUser user = new MyUser();
                user.setUsername(String.format("Silver%d", i));
                user.setEmailVerificationStatus(true);
                user.setPassword(passwordEncoder.encode("password"));
                user.setFirstname(String.format("Silver%d", i));
                user.setLastname(String.format("%d", i));
                user.setPoints(2500);
                user.setLeague(leagueRepository.findLeagueByTitle("Silver Sage"));
                userRepository.save(user);
            }

            for (int i = 0; i < 2; i++) {
                MyUser user = new MyUser();
                user.setUsername(String.format("Gold%d", i));
                user.setEmailVerificationStatus(true);
                user.setPassword(passwordEncoder.encode("password"));
                user.setFirstname(String.format("Gold%d", i));
                user.setLastname(String.format("%d", i));
                user.setPoints(4000);
                user.setLeague(leagueRepository.findLeagueByTitle("Gold Guru"));
                userRepository.save(user);
            }

            for (int i = 0; i < 2; i++) {
                MyUser user = new MyUser();
                user.setUsername(String.format("Platinum%d", i));
                user.setEmailVerificationStatus(true);
                user.setPassword(passwordEncoder.encode("password"));
                user.setFirstname(String.format("Platinum%d", i));
                user.setLastname(String.format("%d", i));
                user.setPoints(8000);
                user.setLeague(leagueRepository.findLeagueByTitle("Platinum Prodigy"));
                userRepository.save(user);
            }

            for (int i = 0; i < 2; i++) {
                MyUser user = new MyUser();
                user.setUsername(String.format("Titanium%d", i));
                user.setEmailVerificationStatus(true);
                user.setPassword(passwordEncoder.encode("password"));
                user.setFirstname(String.format("Titanium%d", i));
                user.setLastname(String.format("%d", i));
                user.setPoints(12000);
                user.setLeague(leagueRepository.findLeagueByTitle("Titanium Titan"));
                userRepository.save(user);
            }

            for (int i = 0; i < 2; i++) {
                MyUser user = new MyUser();
                user.setUsername(String.format("Elysium%d", i));
                user.setEmailVerificationStatus(true);
                user.setPassword(passwordEncoder.encode("password"));
                user.setFirstname(String.format("Elysium%d", i));
                user.setLastname(String.format("%d", i));
                user.setPoints(16000);
                user.setLeague(leagueRepository.findLeagueByTitle("Elysium"));
                userRepository.save(user);
            }
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
