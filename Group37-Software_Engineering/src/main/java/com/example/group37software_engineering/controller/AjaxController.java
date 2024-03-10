package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling AJAX requests related to user data validation.
 */
@RestController
public class AjaxController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Endpoint for checking if a username already exists.
     *
     * @param username The username to check.
     * @return ResponseEntity indicating whether the username exists.
     */
    @GetMapping("/checkUsername")
    public ResponseEntity<?> checkUsername(@RequestParam String username) {
        MyUser user = userRepository.findByUsername(username);
        boolean usernameExists = user != null;
        return ResponseEntity.ok().body("{\"usernameExists\":" + usernameExists + "}");
    }

    /**
     * Endpoint for checking if an email already exists.
     *
     * @param email The email to check.
     * @return ResponseEntity indicating whether the email exists.
     */
    @GetMapping("/checkEmail")
    public ResponseEntity<?> checkEmail(@RequestParam String email) {
        MyUser user = userRepository.findByEmail(email);
        boolean emailExists = user != null;
        return ResponseEntity.ok().body("{\"emailExists\":" + emailExists + "}");
    }

    /**
     * Endpoint for checking if a reset email exists and is verified.
     *
     * @param resetEmail The email to check.
     * @return ResponseEntity indicating whether the reset email exists and is verified.
     */
    @GetMapping("/checkResetEmail")
    public ResponseEntity<?> checkResetEmail(@RequestParam String resetEmail) {
        MyUser user = userRepository.findByEmail(resetEmail);
        boolean emailExists = user != null && user.getEmailVerificationStatus();
        return ResponseEntity.ok().body("{\"emailExists\":" + emailExists + "}");
    }
}
