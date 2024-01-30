package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class RegistrationValidator implements Validator {

    private UserRepository userRepository;

    public RegistrationValidator(UserRepository userRepository) {
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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "", "First Name cannot be empty!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "", "Last Name cannot be empty!");
        if (userRepository.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "", "username already taken!");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Email cannot be empty!");
        if (userRepository.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "", "email already taken!");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Password cannot be empty!");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "", "\nPassword must be between 8 and 32 characters");
        }
    }
}
