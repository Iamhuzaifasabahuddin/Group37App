package com.example.group37software_engineering.controller;


import com.example.group37software_engineering.repo.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class LoginController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login-form")
    public String loginForm() {
        return "Login/login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "Login/denied";
    }

    @RequestMapping(value = "/error-login")
    public String errorLogin() {
        return "Login/login";
    }
    @RequestMapping(value = "/success-login", method = RequestMethod.GET)
    public String successLogin(Principal principal) {
          return "redirect:/dashboard";
    }
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login-form?logout";
    }

}
