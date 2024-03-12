// Function to change the validity of an input field based on a boolean flag
function changeValidity(input, feedback, validity) {
    if (validity) {
        input.classList.add('is-valid');
        input.classList.remove('is-invalid');
        feedback.style.display = 'none';
    } else {
        input.classList.add('is-invalid');
        input.classList.remove('is-valid');
        feedback.style.display = 'block';
    }
}

// Function to validate if an input field is not empty
function validateNotEmpty(id) {
    const input = document.querySelector(`#${id}`);
    const feedback = document.querySelector(`.invalid-feedback.${id}`);
    if (input.value.length === 0) {
        feedback.textContent = 'Cannot be empty!';
        return false;
    }
    return true;
}

// Function to validate if an input field does not contain spaces
function validateNoSpaces(id) {
    const input = document.querySelector(`#${id}`);
    const feedback = document.querySelector(`.invalid-feedback.${id}`);
    if (input.value.includes(' ')) {
        feedback.textContent = 'Cannot contain spaces!';
        return false;
    }
    return true;
}

// Function to validate the length of an input field
function validateLength(id, min, max) {
    const input = document.querySelector(`#${id}`);
    const feedback = document.querySelector(`.invalid-feedback.${id}`);
    if (input.value.length < min || input.value.length > max) {
        feedback.textContent = `Must be ${min}-${max} long!`;
        return false;
    }
    return true;
}


// Function to validate if an input field contains only alphabetic characters
function validateAlphabetic(id) {
    const input = document.querySelector(`#${id}`);
    const feedback = document.querySelector(`.invalid-feedback.${id}`);
    if (!/^[a-zA-Z]+$/.test(input.value)) {
        feedback.textContent = `Must contain letters only!`;
        return false;
    }
    return true;
}

/*
Function to validate the first name
    - Must not be empty
    - Must have 3-15 characters
    - Must not have any spaces
    - Must only contain alphabetic characters
*/
function validateFirstname() {
    const id = 'firstname';
    const input = document.querySelector(`#${id}`);
    const feedback = document.querySelector(`.invalid-feedback.${id}`);
    changeValidity(input, feedback, false);
    if (validateNotEmpty(id) && validateNoSpaces(id) &&
        validateLength(id, 3, 15) && validateAlphabetic(id)) {
        changeValidity(input, feedback, true);
        feedback.textContent = '';
        return true;
    }
    return false;
}

/*
Function to validate the last name
    - Must not be empty
    - Must have 3-20 characters
    - Must not have any spaces
    - Must only contain alphabetic characters
*/
function validateLastname() {
    const id = 'lastname';
    const input = document.querySelector(`#${id}`);
    const feedback = document.querySelector(`.invalid-feedback.${id}`);
    changeValidity(input, feedback, false);
    if (validateNotEmpty(id) && validateNoSpaces(id) &&
        validateLength(id, 3, 20) && validateAlphabetic(id)) {
        changeValidity(input, feedback, true);
        input.textContent = '';
        return true;
    }
    return false;
}

/*
Function to validate the username
    - Must not be empty
    - Must have 4-20 characters
    - Must not have any spaces
    - Must not already be taken
*/
function validateUsername(submit=false) {
    const id = 'username';
    const input = document.querySelector(`#${id}`);
    const feedback = document.querySelector(`.invalid-feedback.${id}`);
    $.ajax({
        url: '/checkUsername',
        type: 'GET',
        data: {username: input.value},
        success: function (data) {
            const parsedData = JSON.parse(data);
            let usernameValid = false;
            changeValidity(input, feedback, false);
            if (validateNotEmpty(id) && validateNoSpaces(id) && validateLength(id, 4, 20)) {
                if (parsedData.usernameExists) {
                    generateUsernameSuggestions(input.value, feedback);
                } else {
                    changeValidity(input, feedback, true);
                    feedback.textContent = '';
                    usernameValid = true;
                }
            }
            validateEmail(submit, usernameValid);
        }
    });
}


let usernameSuggestions = [];
let originalName = "";

// Function to generate username suggestions
function generateUsernameSuggestions(username, feedback, count=0) {
    if (originalName !== username) {
        usernameSuggestions = [];
    }
    if (usernameSuggestions.length >= 5 && originalName === username) {
        feedback.textContent = 'Username is already taken! Suggestions: ' + usernameSuggestions.join(', ');
    } else {
        let suggestedUsername = username + Math.floor(Math.random() * 100);
        $.ajax({
            url: '/checkUsername',
            type: 'GET',
            data: {username: suggestedUsername},
            success: function (data) {
                const parsedData = JSON.parse(data);
                if (!parsedData.usernameExists) {
                    originalName = username;
                    usernameSuggestions.push(suggestedUsername);
                    count++;
                }
                if (count <= 5) {
                    generateUsernameSuggestions(username, feedback, count)
                }
            }
        })
    }
}

/*
Function to validate email
    - Must follow valid format
    - Must not already be registered
 */
function validateEmail(submit=false, usernameValid=false) {
    const id = 'email';
    const input = document.querySelector(`#${id}`);
    const feedback = document.querySelector(`.invalid-feedback.${id}`);
    $.ajax({
        url: '/checkEmail',
        type: 'get',
        data: {email: input.value},
        success: function (data) {
            const parsedData = JSON.parse(data);
            let emailValid = false;
            changeValidity(input, feedback, false);
            if (validateNotEmpty(id)) {
                if (parsedData.emailExists) {
                    feedback.textContent = 'Email is already registered!';
                } else if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(input.value)) {
                    feedback.textContent = 'Must be a valid email address!'
                } else {
                    changeValidity(input, feedback, true);
                    feedback.textContent = '';
                    emailValid = true;
                }
            }
            asyncValidate(submit, usernameValid, emailValid)
        }
    });
}

/*
Function to check the strength of the password
    - Must be between 8-32 characters
    - Must have uppercase
    - Must have lowercase
    - Must have a digit
    - Must have a special character
 */
function checkPasswordStrength() {
    var password = document.getElementById('password').value;
    var strength = 'Weak';
    var missing = [];

    var hasUpperCase = /[A-Z]/.test(password);
    var hasLowerCase = /[a-z]/.test(password);
    var hasDigit = /\d/.test(password);
    var hasSpecialChar = /[!@#£$%^&*()_+{}:;<>,.?~='/-]/.test(password);

    if (password.length >= 8) {
        if (hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar) {
            strength = 'Strong';
        } else {
            strength = 'Medium';
            if (!hasUpperCase) missing.push('an uppercase letter');
            if (!hasLowerCase) missing.push('a lowercase letter');
            if (!hasDigit) missing.push('a digit');
            if (!hasSpecialChar) missing.push('a special character');
        }
    } else {
        missing.push('at least 8 characters');
    }

    return {strength: strength, missing: missing};
}

// Function to validate the password
function validatePassword() {
    const id = 'password';
    const input = document.querySelector(`#${id}`);
    const feedback = document.querySelector(`.invalid-feedback.${id}`);
    changeValidity(input, feedback, false);
    if (validateNotEmpty(id) && validateLength(id, 8, 32)) {
        const result = checkPasswordStrength();
        if (result.strength === 'Strong') {
            changeValidity(input, feedback, true);
            feedback.textContent = '';
        } else {
            feedback.textContent = 'Password is missing: ' + result.missing.join(', ');
        }
        return result.strength === 'Strong';
    }
    return false;
}

// Function to validate that both of the passwords match
function validateConfirmPassword() {
    const id = 'confirmPassword';
    const input = document.querySelector(`#${id}`);
    const feedback = document.querySelector(`.invalid-feedback.${id}`);
    changeValidity(input, feedback, false);
    if (validateNotEmpty(id) && validateLength(id, 8, 32)) {
        if (document.querySelector('#password').value !== input.value) {
            feedback.textContent = 'Passwords must be the same!'
        } else {
            changeValidity(input, feedback, true);
            feedback.textContent = '';
            return true;
        }
    }
    return false;
}

// Function to call all synchronous validation for input fields
function syncValidate() {
    return !![validateFirstname(), validateLastname(), validatePassword(), validateConfirmPassword()].every(f => f);
}

// Function to disable submit button if any input fields are invalid
function asyncValidate(submit=false, usernameValid=false, emailValid=false) {
    if (syncValidate() && usernameValid && emailValid) {
        if (submit) {
            document.querySelector('form').submit();
        } else {
            document.querySelector('button[type="submit"]').disabled = false;
        }
    } else {
        document.querySelector('button[type="submit"]').disabled = true;
    }
}

document.getElementById('backButton').addEventListener('click', function () {
    window.location.href = "${pageContext.request.contextPath}/login";
});


(() => {
    'use strict'
    const button = document.querySelector("button[type='submit']");
    button.addEventListener('click', event => {
        event.preventDefault();
        event.stopPropagation();
        validateUsername(true);
        document.querySelectorAll('input.form-control').forEach(element => element.addEventListener('keyup', () => {
            validateUsername(false);
        }));
    })
})()
