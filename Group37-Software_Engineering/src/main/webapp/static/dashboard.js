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

function validateLength(id) {
    const input = document.querySelector(`#${id}`);
    const feedback = document.querySelector(`.invalid-feedback.${id}`);
    if (input.value.length < 20) {
        feedback.textContent = `Must be at least 20 characters long!`;
        return false;
    }
    if (input.value.length >50){
        feedback.textContent = "";
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

function validateDescription(submit) {
    const id = 'description';
    const input = document.querySelector(`#${id}`);
    const feedback = document.querySelector(`.invalid-feedback.${id}`);
    $.ajax({
        url: '/checkProfanity',
        type: 'GET',
        data: { comment: input.value },
        success: function (data) {
            let reviewValid = false;
            const parsedData = JSON.parse(data);
            changeValidity(input, feedback, false);
            if (validateNotEmpty(id) && validateLength(id)) {
                if (!!parsedData) {
                    feedback.textContent = 'Contains profanity!';
                }
                else if(!/^[a-zA-Z\s',.!?]+$/.test(input.value)) {
                    feedback.textContent = `Must contain letters, spaces, or common punctuation characters!`;
                }
                else{
                    reviewValid = true;
                    changeValidity(input, feedback, true);
                }
            }
            validateReview(submit, reviewValid);
        },
    });
}

const ratingElement = document.querySelector('#rating');

function updateStars() {
    const rating = this.value;
    const stars = document.querySelectorAll('#stars .star');

    if (rating > 5) {
        stars.forEach((star) => {
            star.classList.remove('bi-star-fill');
            star.classList.remove('bi-star-half');
            star.classList.add('bi-star');
        });
    } else {
        const wholeStars = Math.floor(rating);
        const fractionStar = rating - wholeStars;

        stars.forEach((star, index) => {
            if (index < wholeStars) {
                star.classList.remove('bi-star');
                star.classList.remove('bi-star-half');
                star.classList.add('bi-star-fill');
            } else if (index === wholeStars) {
                if (fractionStar < 0.5) {
                    star.classList.remove('bi-star-fill');
                    star.classList.remove('bi-star-half');
                    star.classList.add('bi-star');
                } else if (fractionStar === 0.5) {
                    star.classList.remove('bi-star');
                    star.classList.remove('bi-star-fill');
                    star.classList.add('bi-star-half');
                } else if (fractionStar > 0.5) {
                    star.classList.remove('bi-star');
                    star.classList.remove('bi-star-half');
                    star.classList.add('bi-star-fill');
                }
            } else {
                star.classList.remove('bi-star-fill');
                star.classList.remove('bi-star-half');
                star.classList.add('bi-star');
            }
        });
    }
}

ratingElement.addEventListener('keyup', updateStars);
ratingElement.addEventListener('change', updateStars);

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
    ratingFeedback.textContent = 'Must be between 1 and 5!';
    return false;
}

document.getElementById('description').addEventListener('input', function() {
    this.value = this.value.replace(/\s{2,}/g, ' ');
    const characterCount = this.value.length;
    const remainingCharacters = 50 - characterCount;
    const wordCountElement = document.getElementById('wordCount');
    const button = document.querySelector('#modalForm button[type="submit"]');
    wordCountElement.innerHTML = `<p><strong>${remainingCharacters} characters remaining</strong></p>`;

    if (remainingCharacters < 0) {
        wordCountElement.style.color = 'red';
        document.querySelector('#modalForm button[type="submit"]').disabled = true;
        this.classList.add('is-invalid');
    } else {
        wordCountElement.style.color = 'initial';
        if (!hasBeenClicked) {
            document.querySelector('#modalForm button[type="submit"]').disabled = false;
            this.classList.remove('is-invalid');
        }
    }
});

function validateReview(submit, reviewValid) {
    if (validateRating() && reviewValid) {
        if (submit) {
            if (!submitted) {
                submitted = true;
                document.querySelector('form').submit();
            } else {
                document.querySelector('#submit-review').disabled = true;
            }
        } else {
            document.querySelector('#submit-review').disabled = false;
        }
    } else {
        document.querySelector('#submit-review').disabled = true;
    }

}

const exampleModal = document.getElementById('exampleModal');
let hasBeenClicked = false;
exampleModal.addEventListener('show.bs.modal', function (event) {
    const button = event.relatedTarget;
    const courseId = button.getAttribute('data-course-id');
    const courseTitle = button.getAttribute('data-course-title');
    const form = exampleModal.querySelector('form');
    const modalTitle = exampleModal.querySelector('.modal-title');
    const hiddenInput = exampleModal.querySelector('input[name="courseId"]');

    form.action = "/addComment?courseId=" + courseId;
    modalTitle.innerHTML = `Review for <strong>${courseTitle}</strong>:`;
    hiddenInput.value = courseId;

    document.querySelector("#submit-review").addEventListener("click", (event) => {
        event.preventDefault();
        event.stopPropagation();
        hasBeenClicked = true;
        validateDescription(true);
        document.querySelectorAll('input.form-control').forEach(element => {
            element.addEventListener('keyup', () => {
                validateDescription(false);
            });
        });
        document.querySelectorAll('input.form-control').forEach(element => {
            element.addEventListener('change', () => {
                validateDescription(false);
            });
        });
    });
});
