package com.example.group37software_engineering.service;

import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class VerificationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public void sendEmailVerification(MyUser user, String token) {
        String subject = "Email Verification";
        String verificationLink = "https://localhost:8443/verifyEmail?token=" + token;
        String text = "Dear " + user.getFirstname() + ",\n\nThank you for signing up. Please click on the following link to verify your email address" +
                "This link will expire in 24 hours:\n"
                + verificationLink + "\n\nIf you didn't sign up for our service, please ignore this email.\n\nThanks,\nThe Team";
        emailService.sendSimpleMessage(user.getEmail(), subject, text);
    }

    public void successfulEmailVerification(MyUser user){
        String subject = "Email Successfully Verified";
        String text = "Dear User,\n\nYour email has been successfully verified.\n\nIf you didn't request this, please contact us immediately.\n\nThanks,\nThe Team";
        emailService.sendSimpleMessage(user.getEmail(), subject, text);
    }


    public void initiateEmailVerification(MyUser user) {
        String token = generateToken();
        user.setEmailVerificationToken(token);
        user.setEmailVerificationTokenExpiry(LocalDateTime.now().plusHours(24));
        userRepository.save(user);
        sendEmailVerification(user, token);
    }

    public MyUser findByEmailVerificationToken(String token) {
        return userRepository.findByEmailVerificationToken(token);
    }

    public void verifyEmail(MyUser user) {
        user.setEmailVerificationStatus(true);
        user.setEmailVerificationToken(null);
        user.setEmailVerificationTokenExpiry(null);
        userRepository.save(user);
    }


    private String generateToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[32];
        secureRandom.nextBytes(tokenBytes);
        return new BigInteger(1, tokenBytes).toString(16);
    }
}
