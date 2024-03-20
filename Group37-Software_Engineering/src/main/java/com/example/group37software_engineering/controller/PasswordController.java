/**
 * Controller class handling password reset functionality.
 */
package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.Achievement;
import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.model.Notification;
import com.example.group37software_engineering.repo.NotificationRepository;
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

    /**
     * Handle the password reset request.
     * @param email The email of the user requesting password reset.
     * @param redirectAttributes Redirect attributes to add flash messages.
     * @return Redirect to the login page with appropriate flash message.
     */
    @GetMapping("/reset-password")
    public String requestPasswordReset(@RequestParam("email") String email, RedirectAttributes redirectAttributes) {
        MyUser user = userRepository.findByEmail(email);
        if (user == null) {
            redirectAttributes.addFlashAttribute("EmailError", "User not found");
            return "redirect:/login";
        }
        passwordResetService.initiatePasswordReset(user);
        redirectAttributes.addFlashAttribute("Message", "Reset password email sent");
        return "redirect:/login";
    }

    /**
     * Display the password reset form.
     * @param token The token for password reset.
     * @param redirectAttributes Redirect attributes to add flash messages.
     * @return Redirect to the login page if token is invalid, else display password reset form.
     */
    @GetMapping("/resetPasswordForm")
    public String showPasswordResetForm(@RequestParam("token") String token, RedirectAttributes redirectAttributes) {
        MyUser user = passwordResetService.findByPasswordResetToken(token);
        if (user == null || user.getPasswordResetTokenExpiry().isBefore(LocalDateTime.now())) {
            redirectAttributes.addFlashAttribute("EmailError", "Invalid token");
            return "redirect:/login";
        }
        return "PasswordReset/PasswordResetForm";
    }

    /**
     * Reset the user's password.
     * @param token The token for password reset.
     * @param password The new password.
     * @param redirectAttributes Redirect attributes to add flash messages.
     * @return Redirect to the login page with appropriate flash message.
     */
    @GetMapping("/reset")
    public String resetPassword(@RequestParam("token") String token,
                                @RequestParam("password") String password, RedirectAttributes redirectAttributes) {
        MyUser user = passwordResetService.findByPasswordResetToken(token);
        if (user == null || user.getPasswordResetTokenExpiry().isBefore(LocalDateTime.now())) {
            redirectAttributes.addFlashAttribute("EmailError", "Invalid token");
            return "redirect:/login";
        }
        passwordResetService.resetPassword(user, password);
        redirectAttributes.addFlashAttribute("Message", "Password reset successful!");
        return "redirect:/login";
    }
}
