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
    if ([validatePassword(), validateConfirmPassword()].every(f => f)) {
        document.querySelector('button[type="submit"]').disabled = false;
        return true;
    } else {
        document.querySelector('button[type="submit"]').disabled = true;
        return false;
    }
}

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