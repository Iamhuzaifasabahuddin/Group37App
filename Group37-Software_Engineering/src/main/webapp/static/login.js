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

function validateUsernameEmail(){
    const id = 'username-email';
    const input = document.querySelector(`#${id}`);
    const feedback = document.querySelector(`.invalid-feedback.${id}`);
    changeValidity(input, feedback, false);
    if (validateNotEmpty(id)) {
        changeValidity(input, feedback, true);
        feedback.textContent = '';
        return true;
    }
    return false;
}

function validatePassword(){
    const id = 'password';
    const input = document.querySelector(`#${id}`);
    const feedback = document.querySelector(`.invalid-feedback.${id}`);
    changeValidity(input, feedback, false);
    if (validateNotEmpty(id)) {
        changeValidity(input, feedback, true);
        feedback.textContent = '';
        return true;
    }
    return false;
}


function validateAll(submit) {
    if ([validatePassword(), validateUsernameEmail()].every(f => f)) {
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