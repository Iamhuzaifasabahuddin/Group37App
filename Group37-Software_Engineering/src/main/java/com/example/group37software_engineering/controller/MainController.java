package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.repo.CourseRepository;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.group37software_engineering.CourseData;

import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/dashboard")
    public String getDashboard(Model model, Principal principal) {
        MyUser user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        if(user.getCourse().isEmpty()){
            model.addAttribute("error", "Uhoh no course selected please select a course from courses");
        }else{
            model.addAttribute("courseList", user.getCourse());
        }
        return "dashboard";
    }

    @GetMapping("/admin")
    public String getadmin(Model model, Principal principal) {
        MyUser user = userRepository.findByUsername(principal.getName());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("firstName", user.getFirstname());
        model.addAttribute("lastName", user.getLastname());
        return "admin";
    }


}
