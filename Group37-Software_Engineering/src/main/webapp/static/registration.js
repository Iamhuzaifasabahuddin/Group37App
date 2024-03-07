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

function validateNoSpaces(id) {
    const input = document.querySelector(`#${id}`);
    const feedback = document.querySelector(`.invalid-feedback.${id}`);
    if (input.value.includes(' ')) {
        feedback.textContent = 'Cannot contain spaces!';
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

function validateAlphabetic(id) {
    const input = document.querySelector(`#${id}`);
    const feedback = document.querySelector(`.invalid-feedback.${id}`);
    if (!/^[a-zA-Z]+$/.test(input.value)) {
        feedback.textContent = `Must contain letters only!`;
        return false;
    }
    return true;
}

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
function checkUsername(username) {
    let usernameExists;
    $.ajax({
        url: '/checkUsername',
        type: 'GET',
        async: false,
        data: { username: username },
        success: function(data) {
            const parsedData = JSON.parse(data);
            usernameExists = parsedData.usernameExists;
        },
        error: function() {
            usernameExists = false;
        }
    });
    return usernameExists;
}

function validateUsername() {
    const id = 'username';
    const input = document.querySelector(`#${id}`);
    const feedback = document.querySelector(`.invalid-feedback.${id}`);
    changeValidity(input, feedback, false);
    if (validateNotEmpty(id) && validateNoSpaces(id) && validateLength(id, 4, 20)) {
        const usernameExists = checkUsername(input.value);
        if (usernameExists) {
            const suggestions = generateUsernameSuggestions(input.value);
            feedback.textContent = 'Username is already taken! Suggestions: ' + suggestions.join(', ');
            return false;
        } else {
            changeValidity(input, feedback, true);
            feedback.textContent = '';
            return true;
        }
    }
    return false;
}


let usernameSuggestions = [];

function generateUsernameSuggestions(username) {
    if (usernameSuggestions.length > 0) {
        return usernameSuggestions;
    }

    let count = 0;
    while (count < 5) {
        let suggestedUsername = username + Math.floor(Math.random() * 100);
        $.ajax({
            url: '/checkUsername',
            type: 'GET',
            async: false,
            data: {username: suggestedUsername},
            success: function (data) {
                const parsedData = JSON.parse(data);
                if (!parsedData.usernameExists) {
                    usernameSuggestions.push(suggestedUsername);
                    count++;
                }
            }
        });
    }
    return usernameSuggestions;
}

function checkEmail(email) {
    let emailExists;
    $.ajax({
        url: '/checkEmail',
        type: 'GET',
        async: false,
        data: { email: email },
        success: function(data) {
            const parsedData = JSON.parse(data);
            emailExists = parsedData.emailExists;
        },
        error: function() {
            emailExists = false;
        }
    });
    return emailExists;
}
function validateEmail() {
    const id = 'email';
    const input = document.querySelector(`#${id}`);
    const feedback = document.querySelector(`.invalid-feedback.${id}`);
    changeValidity(input, feedback, false);
    if (validateNotEmpty(id)) {
        const emailExists = checkEmail(input.value);
        if (emailExists) {
            feedback.textContent = 'Email is already registered!';
            return false;
        }
        else if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(input.value)) {
            feedback.textContent = 'Must be a valid email address!';
            return false;
        } else {
            changeValidity(input, feedback, true);
            feedback.textContent = '';
            return true;
        }
    }
    return false;
}

function validatePassword() {
    const id = 'password';
    const input = document.querySelector(`#${id}`);
    const feedback = document.querySelector(`.invalid-feedback.${id}`);
    changeValidity(input, feedback, false);
    if (validateNotEmpty(id) && validateLength(id, 8, 32)) {
        changeValidity(input, feedback, true);
        feedback.textContent = '';
        return true;
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

function validateAll() {
    if ([validateFirstname(), validateLastname(), validateUsername(),
        validateEmail(), validatePassword(), validateConfirmPassword()].every(f => f)) {
        document.querySelector('button[type="submit"]').disabled = false;
        return true;
    } else {
        document.querySelector('button[type="submit"]').disabled = true;
        return false;
    }
}

document.getElementById('backButton').addEventListener('click', function () {
    window.location.href = "${pageContext.request.contextPath}/login";
});


(() => {
    'use strict'
    const forms = document.querySelectorAll('form')
    Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
            if (!validateAll()) { //change valid method
                event.preventDefault()
                event.stopPropagation()
            }
            document.querySelectorAll('input.form-control').forEach(element => element.addEventListener('keyup', () => {
                validateAll();
            }));
        }, false)
    })
})()
