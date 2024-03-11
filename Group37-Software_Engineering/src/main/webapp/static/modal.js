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

function validateResetEmail() {
    const id = 'resetEmail';
    const input = document.querySelector(`#${id}`);
    const feedback = document.querySelector(`.invalid-feedback.${id}`);
    changeValidity(input, feedback, false);
    if (validateNotEmpty(id)) {
        const emailExists = checkResetEmail(input.value);
        if (!emailExists) {
            feedback.textContent = 'Email is not registered!';
            return false;
        } else if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(input.value)) {
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

function checkResetEmail(resetEmail) {
    let emailExists;
    $.ajax({
        url: '/checkResetEmail',
        type: 'GET',
        async: false,
        data: {resetEmail: resetEmail},
        success: function (data) {
            const parsedData = JSON.parse(data);
            emailExists = parsedData.emailExists;
        },
        error: function () {
            emailExists = false;
        }
    });
    return emailExists;
}

function validateAll() {
    const modalSubmitButton = document.querySelector('#exampleModal button[type="submit"]');
    const result = validateResetEmail();
    modalSubmitButton.disabled = !result;
    return result;
}

(() => {
    'use strict'
    const forms = document.querySelectorAll('#modalForm')
    Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
            if (!validateAll()) { //change valid method
                event.preventDefault()
                event.stopPropagation()
            }
            document.querySelectorAll('input.form-control#resetEmail').forEach(element => element.addEventListener('keyup', () => {
                validateAll();
            }));
        }, false)
    })
})()
