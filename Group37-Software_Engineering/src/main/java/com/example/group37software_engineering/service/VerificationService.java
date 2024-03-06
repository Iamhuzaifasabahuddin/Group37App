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
        String text = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Email Verification</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            line-height: 1.6;\n" +
                "            color: black;\n" +
                "        }\n" +
                "\n" +
                "        .container {\n" +
                "            max-width: 400px;\n" +
                "            margin: 0 auto;\n" +
                "            padding: 20px;\n" +
                "        }\n" +
                "\n" +
                "        .header {\n" +
                "            background-color: #007bff;\n" +
                "            color: #fff;\n" +
                "            padding: 10px;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .content {\n" +
                "            padding: 20px;\n" +
                "            background-color: #f4f4f4;\n" +
                "            border-radius: 5px;\n" +
                "        }\n" +
                "\n" +
                "        .footer {\n" +
                "            text-align: center;\n" +
                "            margin-top: 20px;\n" +
                "            color: #777;\n" +
                "        }\n" +
                "\n" +
                "        .button {\n" +
                "            background-color: #007bff;\n" +
                "            border: none;\n" +
                "            color: white;\n" +
                "            padding: 10px 20px;\n" +
                "            text-align: center;\n" +
                "            text-decoration: none;\n" +
                "            display: inline-block;\n" +
                "            font-size: 16px;\n" +
                "            border-radius: 5px;\n" +
                "            cursor: pointer;\n" +
                "            transition: background-color 0.3s;\n" +
                "            align-items: center;\n" +
                "            justify-content: center;\n" +
                "        }\n" +
                "\n" +
                "        .button:hover {\n" +
                "            background-color: #0056b3;\n" +
                "        }\n" +
                "\n" +
                "        .button_container{\n" +
                "            justify-content: center;\n" +
                "            display: flex;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"container\">\n" +
                "    <div class=\"header\">\n" +
                "        <h2>Email Verification</h2>\n" +
                "    </div>\n" +
                "    <div class=\"content\">\n" +
                "        <p>Dear " + user.getFirstname() + ",</p>\n" +
                "        <p>Welcome to our platform! 🚀</p>\n" +
                "        <p>Thank you for signing up. Please click the button below to verify your email address. This link will expire in 48 hours:</p>\n" +
                "        <div class=\"button_container\">\n" +
                "           <a href=\"" + verificationLink + "\" class=\"button button_link\">Reset Password</a>\n" +
                "        </div>\n" +
                "        <p>If you didn't sign up for our service, you can safely ignore this email.</p>\n" +
                "    </div>\n" +
                "    <div class=\"footer\">\n" +
                "        <p>Thanks, </p>\n" +
                "        <p>Group-37</p>\n" +
                "    </div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";

        emailService.sendSimpleMessage(user.getEmail(), subject, text);
    }

    public void successfulEmailVerification(MyUser user){
        String subject = "Email Successfully Verified";
        String text = "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<title>Welcome to SkillsBuild</title>" +
                "<style>" +
                "body {" +
                "    font-family: Arial, sans-serif;" +
                "    line-height: 1.6;" +
                "    color: black;" +
                "}" +
                ".container {" +
                "    max-width: 400px;" +
                "    margin: 0 auto;" +
                "    padding: 20px;" +
                "}" +
                ".header {" +
                "    background-color: #007bff;" +
                "    color: #fff;" +
                "    padding: 10px;" +
                "    text-align: center;" +
                "}" +
                ".content {" +
                "    padding: 20px;" +
                "    background-color: #f4f4f4;" +
                "    border-radius: 5px;" +
                "}" +
                ".content h1 {" +
                "    text-align: center;" +
                "}" +
                ".footer {" +
                "    text-align: center;" +
                "    margin-top: 20px;" +
                "    color: #777;" +
                "}" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class=\"container\">" +
                "    <div class=\"header\">" +
                "        <h2>Email Verified</h2>" +
                "    </div>" +
                "    <div class=\"content\">" +
                "        <p>Dear " + user.getFirstname() + ",</p>" +
                "        <h1>Welcome to SkillsBuild Simulator!</h1>" +
                "        <p>We're thrilled to have you join our learning community.🙌🏼</p>" +
                "        <p>Your email has been successfully verified.✅</p>" +
                "        <p>We warmly welcome you and look forward to seeing you thrive with us. 🎉</p>" +
                "        <p>If you didn't request this, please contact us immediately.</p>" +
                "    </div>" +
                "    <div class=\"footer\">" +
                "        <p>Thanks,</p>" +
                "        <p>Group-37</p>" +
                "    </div>" +
                "</div>" +
                "</body>" +
                "</html>";

        emailService.sendSimpleMessage(user.getEmail(), subject, text);
    }


    public void initiateEmailVerification(MyUser user) {
        String token = generateToken();
        user.setEmailVerificationToken(token);
        user.setEmailVerificationTokenExpiry(LocalDateTime.now().plusHours(48));
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
