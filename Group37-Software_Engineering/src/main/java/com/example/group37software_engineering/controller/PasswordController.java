package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.repo.UserRepository;
import com.example.group37software_engineering.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
public class PasswordController {

    @Autowired
    private PasswordService passwordResetService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/reset-email")
    public String showPasswordResetRequestForm() {
        return "PasswordReset/ResetEmailForm";
    }

    @GetMapping("/request")
    public String requestPasswordReset(@RequestParam("email") String email, RedirectAttributes redirectAttributes) {
        MyUser user = userRepository.findByEmail(email);
        if (user == null) {
            redirectAttributes.addFlashAttribute("EmailError", "User not found");
            return "redirect:/login-form";
        }
        passwordResetService.initiatePasswordReset(user);
        redirectAttributes.addFlashAttribute("Message", "Email sent");
        return "redirect:/login-form";
    }

    @GetMapping("/reset-password-form")
    public String showPasswordResetForm(@RequestParam("token") String token, RedirectAttributes redirectAttributes) {
        MyUser user = passwordResetService.findByPasswordResetToken(token);
        if (user == null || user.getPasswordResetTokenExpiry().isBefore(LocalDateTime.now())) {
            redirectAttributes.addFlashAttribute("EmailError", "Invalid token");
            return "redirect:/login-form";
        }
        return "PasswordReset/PasswordResetForm";
    }

    @GetMapping("/reset")
    public String resetPassword(@RequestParam("token") String token,
                                @RequestParam("password") String password, RedirectAttributes redirectAttributes) {
        MyUser user = passwordResetService.findByPasswordResetToken(token);
        if (user == null || user.getPasswordResetTokenExpiry().isBefore(LocalDateTime.now())) {
            redirectAttributes.addFlashAttribute("EmailError", "Invalid token");
            return "redirect:/login-form";
        }
        passwordResetService.resetPassword(user, password);
        redirectAttributes.addFlashAttribute("Message", "Password reset successful!");
        return "redirect:/login-form";
    }
}