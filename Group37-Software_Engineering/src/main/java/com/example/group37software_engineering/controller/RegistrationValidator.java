package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import java.util.Objects;

//import static org.hibernate.validator.internal.util.Contracts.assertTrue;

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


        String fn = user.getFirstname();
        for (char c : fn.toCharArray()) {
            if(Character.isDigit(c)) {
                errors.rejectValue("firstname", "", "First name must not contain any numbers");
            }
            if (Character.isWhitespace(c)) {
                errors.rejectValue("firstname", "", "First name must not contain any spaces");
            }
        }

        if (!Character.isUpperCase(fn.charAt(0))) {
            errors.rejectValue("firstname", "", "First name must start with a capital");
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

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "", "Last Name cannot be empty!");

        String ln = user.getLastname();
        for (char c : ln.toCharArray()) {
            if(Character.isDigit(c)) {
                errors.rejectValue("lastname", "", "Last name must not contain any numbers");
            }
            if (Character.isWhitespace(c)) {
                errors.rejectValue("lastname", "", "Last name must not contain any spaces");
            }
        }

        if (!Character.isUpperCase(ln.charAt(0))) {
            errors.rejectValue("lastname", "", "Last name must start with a capital");
        }

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

        if (user.getUsername().length() < 3 || user.getUsername().length() > 15) {
            errors.rejectValue("username", "", "Username must be between 3 to 15 characters");
        }

        String un = user.getUsername();
        for (char c : un.toCharArray()) {
            if (Character.isWhitespace(c)) {
                errors.rejectValue("username", "", "Username must not contain any spaces");
            }
        }


        /*
            Email validations:

        */

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Email cannot be empty!");
        if (userRepository.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "", "Email already taken!");
        }

        String email = user.getEmail();
        for (char c : email.toCharArray()) {
            if (Character.isWhitespace(c)) {
                errors.rejectValue("email", "", "Email must not contain any spaces");
            }
        }


        /*
            Password validations:
            The code above represents the validation check up when users enter a username.
            The validation rules for it are as followed:
                - Input can't be empty
                - Range between 8 - 32 characters
                - Must contain 6 characters

        */

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Password cannot be empty!");

        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "", "Password must be between 8 and 32 characters");
        }

        String pw = user.getPassword();
        int charactersCount = 0;
        int numbersCount = 0;
        for (char c : pw.toCharArray()) {
            if (Character.isWhitespace(c)) {
                errors.rejectValue("password", "", "Password must not contain any spaces");
            }
            if (Character.isLetter(c)) {
                charactersCount++;
            }
            if (Character.isDigit(c)) {
                numbersCount++;
            }
        }

        if (charactersCount < 6) {
            errors.rejectValue("password", "", "Password must contain a minimum of 8 characters");
        }

        if (numbersCount == 0) {
            errors.rejectValue("password", "", "Password must contain at least 1 number");
        }


        if (!Character.isUpperCase(fn.charAt(0))) {
            errors.rejectValue("password", "", "Password must start with a capital");
        }

        if(!Objects.equals(user.getPassword(), user.getConfirmpassword())){
            errors.rejectValue("confirmpassword", "", "Passwords do not match!");
        }

        if(!user.getEmail().matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$")){
            errors.rejectValue("email", "", "Invalid email pattern! e.g example@example.com");
        }

        if(user.getUsername().length() < 4 || user.getUsername().length() > 20){
            errors.rejectValue("username", "", "Username should be in between 4 and 20");
        }


    }
}
