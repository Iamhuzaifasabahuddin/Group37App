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

        /*
            First name validations:

            The code above represents the validation check up when users enter their first name.
            The validation rules for it are as followed:
                - Input can't be empty
                - Must start with a capital
                - Range between 3 - 15 characters
                - Must not contain any numbers
                - Must not contain any spaces
        */

        Object firstname = user.getFirstname();
        Object lastname = user.getLastname();
        if (!(firstname instanceof String)) {
            errors.rejectValue("firstname","", "First name should be a string!");
        }

        if (!(lastname instanceof String)) {
            errors.rejectValue("lastname","", "First name should be a string!");
        }

        if (user.getFirstname().length() < 3 || user.getFirstname().length() > 15) {
            errors.rejectValue("firstname", "", "First name must be between 3 to 15 characters");
        }


        /*
            Last name validations:

            The code above represents the validation check up when users enter their last name.
            The validation rules for it are as followed:
                - Input can't be empty
                - Must start with a capital
                - Range between 3 - 20 characters
                - Must not contain any numbers
                - Must not contain any spaces
        */


        if (user.getLastname().length() < 3 || user.getLastname().length() > 20) {
            errors.rejectValue("lastname", "", "Last name must be between 3 to 20 characters");
        }

        /*
            Username validations:

            The code above represents the validation check up when users enter a username.
            The validation rules for it are as followed:
                - Input can't be empty
                - Range between 3 - 20 characters
                -Must not contain any spaces
        */

        if (userRepository.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "", "Username already taken!");
        }


        /*
            Email validations:

        */

        if (userRepository.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "", "Email already taken!");
        }


        /*
            Password validations:
            The code above represents the validation check up when users enter a username.
            The validation rules for it are as followed:
                - Input can't be empty
                - Range between 8 - 32 characters
                - Must contain 6 characters

        */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Email cannot be empty!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "", "Last Name cannot be empty!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "", "First Name cannot be empty!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Password cannot be empty!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmpassword", "", "Password cannot be empty!");

        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "", "Password must be between 8 and 32 characters");
        }

        if(!Objects.equals(user.getPassword(), user.getConfirmpassword())){
            errors.rejectValue("confirmpassword", "", "Passwords do not match!");
        }
        if(user.getUsername().length() < 4 || user.getUsername().length() > 20){
            errors.rejectValue("username", "", "Username should be in between 4 and 20");
        }

        if(!user.getEmail().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")){
            errors.rejectValue("email", "", "Invalid email pattern! e.g example@example.com");
        }



    }

}
