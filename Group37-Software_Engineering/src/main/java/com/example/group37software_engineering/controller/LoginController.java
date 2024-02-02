package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.repo.CourseRepository;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

/**
 * Controller class handling user login and related operations.
 */
@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CourseRepository courseRepository;
    /**
     * Display the login form.
     *
     * @return The view name for the login form.
     */
    @GetMapping("/login-form")
    public String loginForm() {
        return "Login/login";
    }

    /**
     * Display the access denied page.
     *
     * @return The view name for the access denied page.
     */
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "Login/denied";
    }

    /**
     * Handle errors during login and redirect to the login form.
     *
     * @return The view name for the login form.
     */
    @RequestMapping(value = "/error-login")
    public String errorLogin(Model model) {
        model.addAttribute("error", "Invalid Credentials!");
        return "Login/login";
    }
    /**
     * Handle successful login and redirect to the dashboard.
     *
     * @param principal The authenticated user's principal.
     * @return The redirection URL to the dashboard.
     */
    @RequestMapping(value = "/success-login", method = RequestMethod.GET)
    public String successLogin(Principal principal, Model model, RedirectAttributes redirectAttributes) {
        MyUser user = userRepository.findByUsername(principal.getName());
        if (user.getCourse().isEmpty()) {
            return "redirect:/courses";
        } else {
            redirectAttributes.addFlashAttribute("message", "Successfully logged in");
            return "redirect:/dashboard";
        }
    }


    /**
     * Handle user logout and redirect to the login form with a logout parameter.
     *
     * @return The redirection URL to the login form with a logout parameter.
     */
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login-form?logout";
    }
}
