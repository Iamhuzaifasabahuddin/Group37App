package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.Achievement;
import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.model.Notification;
import com.example.group37software_engineering.repo.NotificationRepository;
import com.example.group37software_engineering.repo.UserRepository;
import com.example.group37software_engineering.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

/**
 * Controller class for handling email verification requests.
 */
@Controller
public class VerificationController {

    @Autowired
    private VerificationService verificationService;

    @Autowired
    private AchievementController achievementController;

    /**
     * Endpoint for verifying user email.
     *
     * @param token The verification token sent via email.
     * @param redirectAttributes Redirect attributes to add flash messages.
     * @return The redirection URL to the login page.
     */
    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token, RedirectAttributes redirectAttributes) {
        MyUser user = verificationService.findByEmailVerificationToken(token);
        if (user == null || user.getEmailVerificationTokenExpiry().isBefore(LocalDateTime.now())) {
            redirectAttributes.addFlashAttribute("EmailError", "Invalid token");
        } else {
            verificationService.verifyEmail(user);
            user.setDateJoined();
            redirectAttributes.addFlashAttribute("Message", "Email verified successfully!");
            verificationService.successfulEmailVerification(user);
            achievementController.Crypto(user);
        }
        return "redirect:/login";
    }
}
