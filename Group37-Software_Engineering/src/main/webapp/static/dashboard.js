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
    if (input.value.length === 0 || input.value.trim().length === 0){
        feedback.textContent = 'Cannot be empty!';
        return false;
    }
    return true;
}

function validateAlphabetic(id) {
    const input = document.querySelector(`#${id}`);
    const feedback = document.querySelector(`.invalid-feedback.${id}`);
    if (!/^[a-zA-Z\s]+$/.test(input.value)) {
        feedback.textContent = `Must contain letters or spaces only!`;
        return false;
    }
    return true;
}


function validateRange(id, min, max) {
    const input = document.querySelector(`#${id}`);
    const feedback = document.querySelector(`.invalid-feedback.${id}`);
    if (input.value < min || input.value > max) {
        feedback.textContent = `Must be between ${min} and ${max}!`;
        return false;
    }
    return true;

}

function validateDescription() {
    const id = 'description';
    const input = document.querySelector(`#${id}`);
    const feedback = document.querySelector(`.invalid-feedback.${id}`);
    changeValidity(input, feedback, false);
    if (validateNotEmpty(id) && validateAlphabetic(id)) {
        changeValidity(input, feedback, true);
        feedback.textContent = '';
        return true;
    }
    return false;
}

function validateRating() {
    const id = 'rating';
    const rating = document.querySelector(`#${id}`);
    const ratingFeedback = document.querySelector(`.invalid-feedback.${id}`);

    changeValidity(rating, ratingFeedback, false);
    if (validateNotEmpty(id) && validateRange(id, 1, 5)) {
        changeValidity(rating, ratingFeedback, true);
        ratingFeedback.textContent = '';
        return true;
    }
    return false;
}


function validateAll(submit) {
    const isDescriptionValid = validateDescription();
    const isRatingValid = validateRating();

    if (isDescriptionValid && isRatingValid) {
        document.querySelector('#modalForm button[type="submit"]').disabled = false;
        if (submit && !submitted) {
            submitted = true;
            document.querySelector('#modalForm').submit();
        }
    } else {
        document.querySelector('#modalForm button[type="submit"]').disabled = true;
    }
}


var exampleModal = document.getElementById('exampleModal')
exampleModal.addEventListener('show.bs.modal', function (event) {
    var button = event.relatedTarget
    var courseId = button.getAttribute('data-course-id')
    var courseTitle = button.getAttribute('data-course-title')
    var form = exampleModal.querySelector('form');
    var modalTitle = exampleModal.querySelector('.modal-title');
    var hiddenInput = exampleModal.querySelector('input[name="courseId"]');

    form.action = "/addComment?courseId=" + courseId;
    modalTitle.textContent = "Review for " + courseTitle + ":";
    hiddenInput.value = courseId;

    form.addEventListener('submit', function (event) {
        event.preventDefault();
        validateAll(true);
    });

    form.querySelectorAll('input.form-control').forEach(element => element.addEventListener('keyup', () => {
        validateAll(false);
    }));
});

document.querySelector("button").addEventListener('click', event => {
    event.preventDefault();
    event.stopPropagation();
    validateAll(true);
}, false);
