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

function validateEmail() {
    const id = 'resetEmail';
    const input = document.querySelector(`#${id}`);
    const feedback = document.querySelector(`.invalid-feedback.${id}`);
    changeValidity(input, feedback, false);

    if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(input.value)) {
        feedback.textContent = 'Must be a valid email address!';
        return Promise.resolve(false);
    }

    if (validateNotEmpty(id)) {
        return new Promise((resolve, reject) => {
            $.ajax({
                url: '/checkResetEmail',
                type: 'get',
                data: { resetEmail: input.value },
                success: function(data) {
                    const parsedData = JSON.parse(data);
                    if (!parsedData.emailExists) {
                        feedback.textContent = 'Email is not registered!';
                        changeValidity(input, feedback, false);
                        resolve(false);
                    } else {
                        changeValidity(input, feedback, true);
                        feedback.textContent = '';
                        resolve(true);
                    }
                },
                error: function() {
                    reject(false);
                }
            });
        });
    }
    return Promise.resolve(false);
}


function validateAll() {
    const modalSubmitButton = document.querySelector('#exampleModal button[type="submit"]');
    return validateEmail().then(result => {
        modalSubmitButton.disabled = !result;
        return result;
    });
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
