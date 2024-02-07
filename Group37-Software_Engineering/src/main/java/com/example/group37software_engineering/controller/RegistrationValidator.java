package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import java.util.Objects;

/**
 * Validator for user registration data.
 */
public class RegistrationValidator implements Validator {

    private UserRepository userRepository;

    /**
     * Constructor to initialize the validator with a UserRepository.
     *
     * @param userRepository The UserRepository to check for existing usernames and emails.
     */
    public RegistrationValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Check if the validator supports the given class.
     *
     * @param clazz The class to be checked.
     * @return True if the class is MyUser, false otherwise.
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return MyUser.class.equals(clazz);
    }

    /**
     * Validate the user registration data.
     *
     * @param target The object to be validated.
     * @param errors The Errors object to record validation errors.
     */
    @Override
    public void validate(Object target, Errors errors) {
        MyUser user = (MyUser) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "", "Username cannot be empty!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "", "First Name cannot be empty!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "", "Last Name cannot be empty!");
        if (userRepository.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "", "Username already taken!");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Email cannot be empty!");
        if (userRepository.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "", "Email already taken!");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Password cannot be empty!");

        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "", "Password must be between 8 and 32 characters");
        }
        if(!Objects.equals(user.getPassword(), user.getConfirmpassword())){
            errors.rejectValue("confirmpassword", "", "Passwords do not match!");
        }
    }
}
