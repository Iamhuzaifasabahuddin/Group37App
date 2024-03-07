package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AjaxController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/checkUsername")
    public ResponseEntity<?> checkUsername(@RequestParam String username) {
        MyUser user = userRepository.findByUsername(username);
        boolean usernameExists = user != null;
        return ResponseEntity.ok().body("{\"usernameExists\":" + usernameExists + "}");
    }

    @GetMapping("/checkEmail")
    public ResponseEntity<?> checkEmail(@RequestParam String email) {
        MyUser user = userRepository.findByEmail(email);
        boolean emailExists = user != null;
        return ResponseEntity.ok().body("{\"emailExists\":" + emailExists + "}");
    }

    @GetMapping("/checkResetEmail")
    public ResponseEntity<?> checkresetEmail(@RequestParam String resetEmail) {
        MyUser user = userRepository.findByEmail(resetEmail);
        boolean emailExists = user != null;
        return ResponseEntity.ok().body("{\"emailExists\":" + emailExists + "}");
    }
}