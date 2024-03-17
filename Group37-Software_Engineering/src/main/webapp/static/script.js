let submitted = false;

/* Colour change listener */
function changeHues() {
    const cards = document.querySelectorAll(".card, .carousel-item");
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

$(document).ready(function () {
    $(".nav-item a").click(function () {
        $(".nav-item a").removeClass(("active"));
        $(this).addClass(("active"));
        var target = $(this).data('target');
        $(".friends > ul > li").hide();
        $("#" + target + "-content").show();
    });
})

function generateStars(rating) {
    let stars = '';
    let fullStars = Math.floor(rating);
    let fractionStar = rating - fullStars;

    for (let i = 0; i < fullStars; i++) {
        stars += '<i class="bi bi-star-fill" style="color: #fcc200; font-size: 1.5em;"></i>';
    }

    if (fractionStar >= 0.75) {
        stars += '<i class="bi bi-star-three-quarters" style="color: #fcc200; font-size: 1.5em;"></i>';
    } else if (fractionStar >= 0.5) {
        stars += '<i class="bi bi-star-half" style="color: #fcc200; font-size: 1.5em;"></i>';
    } else if (fractionStar >= 0.25) {
        stars += '<i class="bi bi-star-quarter" style="color: #fcc200; font-size: 1.5em;"></i>';
    }

    return stars;
}

const commentsContainer = document.querySelector('#commentsContainer');

function fetchComments(courseId, courseTitle) {
    $.ajax({
        type: 'GET',
        url: '/comments?courseId=' + courseId,
        success: function (response) {
            const parsedData = JSON.parse(response);
            commentsContainer.innerHTML = '';
            for (const comment of parsedData.comments) {
                // Create a new div for each comment
                const commentDiv = document.createElement('div');


                const avatarImg = document.createElement('img');
                avatarImg.src = `https://eu.ui-avatars.com/api/?name=${comment.user.firstname}+${comment.user.lastname}&size=100`;
                avatarImg.alt = 'User Initials Image';
                avatarImg.style.marginRight = '10px';

                const usernameDiv = document.createElement('div');
                usernameDiv.innerHTML = `<h1>${comment.user.username}</h1>`;
                usernameDiv.style.marginTop = '30px';
                usernameDiv.style.textTransform = 'capitalize';

                const userDiv = document.createElement('div');
                userDiv.style.display = 'flex';
                userDiv.appendChild(avatarImg);
                userDiv.appendChild(usernameDiv);

                const ratingDiv = document.createElement('div');
                ratingDiv.innerHTML = `${generateStars(comment.comment.review)}`;

                const dateDiv = document.createElement('div');
                dateDiv.innerHTML = `<p><strong>Date:</strong> ${comment.dateCommented}</p>`;
                dateDiv.style.marginTop = '12px';

                const CombinedDiv = document.createElement('div');
                CombinedDiv.style.display = 'flex';
                CombinedDiv.style.justifyContent = 'space-between';
                CombinedDiv.appendChild(ratingDiv);
                CombinedDiv.appendChild(dateDiv);
                // CombinedDiv.style.marginRight = '10px';

                const DescriptionDiv = document.createElement('div');
                DescriptionDiv.innerHTML = `<p><strong>Description:</strong> ${comment.comment.description}</p>`;

                DescriptionDiv.style.marginTop = '10px';
                ratingDiv.style.marginTop = '10px';

                commentDiv.appendChild(userDiv);
                // commentDiv.appendChild(ratingDiv);
                // commentDiv.appendChild(dateDiv);
                commentDiv.appendChild(CombinedDiv);
                commentDiv.appendChild(DescriptionDiv);

                commentsContainer.appendChild(commentDiv);
                const hr = document.createElement('hr');
                commentsContainer.appendChild(hr);
            }
            const offcanvasTitle = document.querySelector('#offcanvasExampleLabel');
            offcanvasTitle.textContent = `Comments for ${courseTitle}:`;
        },
        error: function () {
            console.error('Failed to fetch comments.');
        }
    });
}

$(document).ready(function () {
    $('.review-course-btn').click(function () {
        const courseId = $(this).data('course-id');
        const courseTitle = $(this).data('course-title');
        fetchComments(courseId, courseTitle);
    });
});