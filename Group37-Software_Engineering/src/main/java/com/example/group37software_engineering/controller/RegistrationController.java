package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.RegistrationService;
import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.repo.UserRepository;
import com.example.group37software_engineering.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller class handling user registration operations.
 */
@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private VerificationService verificationService;

    /**
     * Initialize the WebDataBinder with a custom validator.
     *
     * @param binder The WebDataBinder to be initialized.
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new RegistrationValidator(userRepository));
    }

    /**
     * Display the registration form for a new user.
     *
     * @param model The Model to be used in the view.
     * @return The view name for the registration form.
     */
    @GetMapping("/NewUser")
    public String newUser(Model model) {
        model.addAttribute("user", new MyUser());
        return "Login/registration";
    }

    /**
     * Process the registration form submission and add a new user.
     *
     * @param user   The MyUser object representing the new user.
     * @param result The BindingResult for validation errors.
     * @return The view name to redirect to after processing the form submission.
     */
    @PostMapping("/AddUser")
    public String addUser(@Valid @ModelAttribute("user") MyUser user, BindingResult result, RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            return "Login/registration";
        }
        registrationService.registerUser(user);
        verificationService.initiateEmailVerification(user);
        redirectAttributes.addFlashAttribute("Message", "Verification link sent to your email!");
        return "redirect:/login";
    }
}
