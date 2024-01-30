package com.example.group37software_engineering;

import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Group37SoftwareEngineeringApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(Group37SoftwareEngineeringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        MyUser user = new MyUser();

        user.setUsername("Hs540");
        user.setFirstname("Huzaifa Sabah");
        user.setLastname("Uddin");
        user.setEmail("Hs540@student.le.ac.uk");
        user.setPassword(passwordEncoder.encode("password"));

        userRepository.save(user);
    }
}
