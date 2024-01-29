package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.RegistrationService;
import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RegistrationService registrationService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new RegistrationValidator(userRepository));
    }

    @GetMapping("/NewUser")
    public String newUser(Model model) {
        model.addAttribute("user", new MyUser());
        return "Login/registeration";
    }

    @PostMapping("/AddUser")
    public String addUser(@Valid @ModelAttribute("user") MyUser user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "Login/registeration";
        }

        registrationService.registerUser(user);

        return "redirect:/login-form";
    }
}
