/**
 * Service class responsible for handling password reset functionality.
 */
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

    /**
     * Sends a password reset email to the user containing a unique token.
     *
     * @param user  The user for whom the password reset email is sent.
     * @param token The unique token for password reset.
     */
    public void sendPasswordResetEmail(MyUser user, String token) {
        String subject = "Password Reset Request";
        String resetLink = "https://localhost:8443/reset-password-form?token=" + token;
        String text = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Password Reset</title>\n" +
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
                "        .button_container {\n" +
                "            justify-content: center;\n" +
                "            display: flex;\n" +
                "        }\n" +
                "\n" +
                "        .button_link {\n" +
                "            text-decoration: none;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"container\">\n" +
                "    <div class=\"header\">\n" +
                "        <h2>Password Reset</h2>\n" +
                "    </div>\n" +
                "    <div class=\"content\">\n" +
                "        <p>Dear " + user.getFirstname() + ",</p>\n" +
                "        <p>We've received a request to reset your password.</p>\n" +
                "        <p>Please click the button below to reset your password. This link will expire in <strong>1 hour</strong> ⏳:</p>\n" +
                "        <div class=\"button_container\">\n" +
                "           <a href=\"" + resetLink + "\" class=\"button button_link\">Reset Password</a>\n" +
                "        </div>\n" +
                "        <p>If you didn't request this, you can safely ignore this email. ❌</p>\n" +
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

    /**
     * Sends an email notification to the user upon successful password reset.
     *
     * @param user The user for whom the password has been reset.
     */
    public void successfulPasswordResetEmail(MyUser user) {
        String subject = "Password Reset Successful";
        String text = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Password Changed</title>\n" +
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
                "        .content h1{\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .footer {\n" +
                "            text-align: center;\n" +
                "            margin-top: 20px;\n" +
                "            color: #777;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"container\">\n" +
                "    <div class=\"header\">\n" +
                "        <h2>Password Changed Successfully</h2>\n" +
                "    </div>\n" +
                "    <div class=\"content\">\n" +
                "        <p>Dear " + user.getFirstname() + ",</p>\n" +
                "        <p>You're back to learning spartan.\uD83D\uDE4C\uD83C\uDFFC</p>\n" +
                "        <p>Your password has been successfully reset.</p>\n" +
                "        <p>If you didn't request this, please contact us immediately.</p>\n" +
                "    </div>\n" +
                "    <div class=\"footer\">\n" +
                "        <p>Thanks,</p>\n" +
                "        <p>The Team</p>\n" +
                "    </div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";

        emailService.sendSimpleMessage(user.getEmail(), subject, text);
    }

    /**
     * Initiates the password reset process for the user.
     *
     * @param user The user requesting the password reset.
     */
    public void initiatePasswordReset(MyUser user) {
        String token = generateToken();
        user.setPasswordResetToken(token);
        user.setPasswordResetTokenExpiry(LocalDateTime.now().plusHours(1));
        userRepository.save(user);
        sendPasswordResetEmail(user, token);
    }

    /**
     * Finds a user by their password reset token.
     *
     * @param token The password reset token.
     * @return The user associated with the token.
     */
    public MyUser findByPasswordResetToken(String token) {
        return userRepository.findByPasswordResetToken(token);
    }

    /**
     * Resets the user's password.
     *
     * @param user        The user whose password is being reset.
     * @param newPassword The new password.
     */
    public void resetPassword(MyUser user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setPasswordResetToken(null);
        user.setPasswordResetTokenExpiry(null);
        userRepository.save(user);
        successfulPasswordResetEmail(user);
    }

    /**
     * Generates a secure random token for password reset.
     *
     * @return The generated token.
     */
    private String generateToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[32];
        secureRandom.nextBytes(tokenBytes);
        return new BigInteger(1, tokenBytes).toString(16);
    }
}
