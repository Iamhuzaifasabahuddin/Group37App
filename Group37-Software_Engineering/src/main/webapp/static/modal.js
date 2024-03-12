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

function validateResetEmail(submit=false) {
    const id = 'resetEmail';
    const input = document.querySelector(`#${id}`);
    const feedback = document.querySelector(`.invalid-feedback.${id}`);
    $.ajax({
        url: '/checkResetEmail',
        type: 'GET',
        async: false,
        data: {resetEmail: input.value},
        success: function (data) {
            const parsedData = JSON.parse(data);
            let validEmail = false;
            changeValidity(input, feedback, false);
            if (validateNotEmpty(id)) {
                if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/
                    .test(input.value)) {
                    feedback.textContent = 'Must be a valid email address!';
                } else if (!parsedData.emailExists) {
                    feedback.textContent = 'Email is not registered!';
                } else if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(input.value)) {
                    feedback.textContent = 'Must be a valid email address!';
                } else {
                    changeValidity(input, feedback, true);
                    feedback.textContent = '';
                    validEmail = true;
                }
            }
            if (validEmail) {
                if (submit) {
                    document.querySelector("#modalForm").submit();
                } else {
                    document.querySelector(".modal-footer button[type='submit']").disabled = false;
                }
            } else {
                document.querySelector(".modal-footer button[type='submit']").disabled = true;
            }
        },
        error: function () {
            feedback.textContent = 'Server Error!';
        }
    });
}

(() => {
    'use strict'
    const button = document.querySelector(".modal-footer button[type='submit']");
    button.addEventListener('click', event => {
        event.preventDefault()
        event.stopPropagation()
        validateResetEmail(true);
        document.querySelectorAll('input.form-control#resetEmail').forEach(element => element.addEventListener('keyup', () => {
            validateResetEmail(false);
        }));
    }, false)
})()
