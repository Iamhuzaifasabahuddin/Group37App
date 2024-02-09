package com.example.group37software_engineering.service;

import com.example.group37software_engineering.RegistrationService;
import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of the RegistrationService interface for user registration.
 */
@Service
public class MyRegistrationService implements RegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Register a new user by encoding the password and saving the user in the repository.
     *
     * @param user The MyUser object representing the new user.
     */
    @Override
    @Transactional
    public void registerUser(MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
