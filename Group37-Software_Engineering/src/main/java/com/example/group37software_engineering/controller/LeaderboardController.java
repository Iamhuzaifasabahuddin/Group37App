package com.example.group37software_engineering.controller;


import com.example.group37software_engineering.model.Course;
import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class LeaderboardController {

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/leaderboard")
    public String getLeaderboard(Model model) {
        List<MyUser> myUsers = StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .sorted(Comparator.comparingDouble(MyUser::getPoints).reversed())
                .toList().subList(0, 5);
        model.addAttribute("Users", myUsers);
        return "leaderboard";
    }

}
