package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
public class VerificationController {

    @Autowired
    private VerificationService verificationService;

    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token, RedirectAttributes redirectAttributes) {
        MyUser user = verificationService.findByEmailVerificationToken(token);
        if (user == null || user.getEmailVerificationTokenExpiry().isBefore(LocalDateTime.now())) {
            redirectAttributes.addFlashAttribute("EmailError", "Invalid token");
        } else {
            verificationService.verifyEmail(user);
            redirectAttributes.addFlashAttribute("Message", "Email verified successfully!");
            verificationService.successfulEmailVerification(user);
        }
        return "redirect:/login";
    }
}
