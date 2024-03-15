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

function validateNotEmpty(id) {
    const input = document.querySelector(`#${id}`);
    const feedback = document.querySelector(`.invalid-feedback.${id}`);
    if (input.value.length === 0) {
        feedback.textContent = 'Cannot be empty!';
        return false;
    }
    return true;
}

function validateLength(id, min, max) {
    const input = document.querySelector(`#${id}`);
    const feedback = document.querySelector(`.invalid-feedback.${id}`);
    if (input.value.length < min || input.value.length > max) {
        feedback.textContent = `Must be ${min}-${max} long!`;
        return false;
    }
    return true;
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
        var result = checkPasswordStrength();
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

function validateAll(submit) {
    if ([validatePassword(), validateConfirmPassword()].every(f => f)) {
        document.querySelector('button[type="submit"]').disabled = false;
        if (submit && !submitted) {
            submitted = true;
            document.querySelector('form').submit();
        }
        return true;
    } else {
        document.querySelector('button[type="submit"]').disabled = true;
        return false;
    }
}

(() => {
    'use strict'
    document.querySelector("button").addEventListener('click', event => {
        event.preventDefault();
        event.stopPropagation();
        validateAll(true);
        document.querySelectorAll('input.form-control').forEach(element => element.addEventListener('keyup', () => {
            validateAll(false);
        }));
    }, false);
})()