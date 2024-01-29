package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class LoginValidator implements Validator {

    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    public LoginValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MyUser.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MyUser user = (MyUser) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "", "Username cannot be empty!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Password cannot be empty!");

        MyUser storedUser = userRepository.findByUsername(user.getUsername());
        if (storedUser == null) {
            errors.rejectValue("username", "", "Username not found!");
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (!passwordEncoder.matches(user.getPassword(), storedUser.getPassword())) {
                errors.rejectValue("password", "", "Incorrect password!");
            }
        }
    }


}
