package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.Achievement;
import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.repo.AchievementRepository;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class AchievementController {

    @Autowired
    private AchievementRepository achievementRepository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/achievements")
    public String showAchievements(Model model, Principal principal) {
        MyUser user = userRepository.findByUsername(principal.getName());
        List<Achievement> achievements = (List<Achievement>) achievementRepository.findAll();
        model.addAttribute("achievements", achievements);
        model.addAttribute("user", user);
        return "achievements";
    }


}
