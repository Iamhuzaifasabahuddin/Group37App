package com.example.group37software_engineering.service;

import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class PasswordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void sendPasswordResetEmail(String to, String token) {
        String subject = "Password Reset Request";
        String resetLink = "https://localhost:8443/reset-password-form?token=" + token;
        String text = "Dear User,\n\nYou requested a password reset. Please click on the following link to reset your password" +
                "This link will expire in an hour:\n"
                + resetLink + "\n\nIf you didn't request this, you can safely ignore this email.\n\nThanks,\nThe Team";
        emailService.sendSimpleMessage(to, subject, text);
    }

    public void successfulPasswordResetEmail(String to) {
        String subject = "Password Reset Successful";
        String text = "Dear User,\n\nYour password has been successfully reset.\n\nIf you didn't request this, please contact us immediately.\n\nThanks,\nThe Team";
        emailService.sendSimpleMessage(to, subject, text);
    }

    public void initiatePasswordReset(MyUser user) {
        String token = generateToken();
        user.setPasswordResetToken(token);
        user.setPasswordResetTokenExpiry(LocalDateTime.now().plusHours(1));
        userRepository.save(user);
        sendPasswordResetEmail(user.getEmail(), token);
    }

    public MyUser findByPasswordResetToken(String token) {
        return userRepository.findByPasswordResetToken(token);
    }

    public void resetPassword(MyUser user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setPasswordResetToken(null);
        user.setPasswordResetTokenExpiry(null);
        userRepository.save(user);
        successfulPasswordResetEmail(user.getEmail());
    }

    private String generateToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[32];
        secureRandom.nextBytes(tokenBytes);
        return new BigInteger(1, tokenBytes).toString(16);
    }
}
