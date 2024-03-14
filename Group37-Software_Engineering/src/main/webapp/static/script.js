/* Colour change listener */
function changeHues() {
    const cards = document.querySelectorAll(".card");
    const categories = ["ARTIFICIAL INTELLIGENCE", "CLOUD", "DATA SCIENCE", "CYBERSECURITY", "SUSTAINABILITY"];
    const angles = [145, 180, 215, 250, 285].reverse();
    cards.forEach(card => {
        const category = card.querySelector(".category").textContent;
        const angle = angles[categories.indexOf(category.toUpperCase())];
        card.querySelector("img").style.filter = `hue-rotate(${angle}deg)`;
    });
}

/* Pop up box for enrolment */
function showConfirmationBox(id, title) {
    document.querySelector(".input-course-id").value = id;
    document.querySelector(".modal-body").innerHTML = `Are you sure you want to enroll in <b>${title}</b>?`;
}

window.onload = function () {
    var errorElement = document.getElementById('error-message');
    if (errorElement) {
        setTimeout(function () {
            errorElement.style.display = 'none';
        }, 5000);
    }
    changeHues();
    displayQuizScore();
};

var prevScrollpos = window.pageYOffset;
window.onscroll = function () {
    var currentScrollPos = window.pageYOffset;
    if (prevScrollpos > currentScrollPos) {
        document.getElementById("navbar").style.top = "0";
    } else {
        document.getElementById("navbar").style.top = "-50px";
    }
    prevScrollpos = currentScrollPos;
}

/* message display */
var messageElement = document.getElementById("message");
setTimeout(function () {
    messageElement.style.display = "none";
}, 5000);

var errorMessageElement = document.getElementById("error-message");
setTimeout(function () {
    errorMessageElement.style.display = "none";
}, 5000);

/* Course Quiz and Start and End Time */
function openLinkAndSubmit(courseLink) {
    window.open(courseLink, '_blank');
    document.getElementById('getStartedForm').submit();
}

function displayQuizScore() {
    const scoreText = document.querySelector(".quiz-score p");
    const score = parseInt(scoreText.innerText.substring(0, scoreText.innerText.length - 1));
    const quizScore = document.querySelector(".quiz-score");
    let i = 0;
    scoreText.innerText = `${i}%`;
    scoreText.style.opacity = 100;
    let animateScore = setInterval(() => {
        scoreText.innerText = `${i}%`;
        quizScore.style.cssText = `background: radial-gradient(closest-side, white 93%, transparent 94% 100%),
            conic-gradient(green ${i}%, var(--secondary-light) 0);`;
        if (i >= score) {
            clearInterval(animateScore);
            document.querySelector(".quiz-result").style.opacity = 100;
            if (score < 80) {
                document.querySelector(".quiz-result").innerText = "Sorry, you need at least 80% to pass this course.";
            }
        }
        i++;
    }, 25);
}

/* Login inline validation */
(() => {
    'use strict'
    const forms = document.querySelectorAll('.needs-validation')
    Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
            if (!form.checkValidity()) {
                event.preventDefault()
                event.stopPropagation()
            }
            form.classList.add('was-validated')
        }, false)
    })
})()

$('a[data-toggle="tooltip"]').tooltip({
    animated: 'fade',
    placement: 'right',
    html: true
});
