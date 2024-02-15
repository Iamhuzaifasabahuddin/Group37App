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
    document.querySelector(".confirmation-background").style.display = "flex";
    document.querySelector(".input-course-id").value = id;
    document.querySelector(".confirmation-box p").innerHTML = `Are you sure you want to enroll in <b>${title}</b>?`;
}

function closeConfirmationBox() {
    document.querySelector(".confirmation-background").style.display = "none";
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
        quizScore.style.cssText = `background: radial-gradient(closest-side, var(--primary-light) 93%, transparent 94% 100%),
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


/* loading */

document.addEventListener("DOMContentLoaded", function () {
    var loadingOverlay = document.querySelector('.loading-overlay');
    loadingOverlay.style.display = 'flex';

    setTimeout(function () {
        loadingOverlay.style.display = 'none';
    }, 1000);
});
