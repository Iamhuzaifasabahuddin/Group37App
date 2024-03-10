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

/* Taken from "https://codepen.io/tutsplus/pen/JjbryOQ" */

const $tabsToDropdown = $(".tabs-to-dropdown");

function generateDropdownMarkup(container) {
    const $navWrapper = container.find(".nav-wrapper");
    const $navPills = container.find(".nav-pills");
    const firstTextLink = $navPills.find("li:first-child a").text();
    const $items = $navPills.find("li");
    const markup = `
    <div class="dropdown d-md-none">
      <button class="btn dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        ${firstTextLink}
      </button>
      <div class="dropdown-menu" aria-labelledby="dropdownMenuButton"> 
        ${generateDropdownLinksMarkup($items)}
      </div>
    </div>
  `;
    $navWrapper.prepend(markup);
}

function generateDropdownLinksMarkup(items) {
    let markup = "";
    items.each(function () {
        const textLink = $(this).find("a").text();
        markup += `<a class="dropdown-item" href="#">${textLink}</a>`;
    });

    return markup;
}

function showDropdownHandler(e) {
    // works also
    //const $this = $(this);
    const $this = $(e.target);
    const $dropdownToggle = $this.find(".dropdown-toggle");
    const dropdownToggleText = $dropdownToggle.text().trim();
    const $dropdownMenuLinks = $this.find(".dropdown-menu a");
    const dNoneClass = "d-none";
    $dropdownMenuLinks.each(function () {
        const $this = $(this);
        if ($this.text() == dropdownToggleText) {
            $this.addClass(dNoneClass);
        } else {
            $this.removeClass(dNoneClass);
        }
    });
}

function clickHandler(e) {
    e.preventDefault();
    const $this = $(this);
    const index = $this.index();
    const text = $this.text();
    $this.closest(".dropdown").find(".dropdown-toggle").text(`${text}`);
    $this
        .closest($tabsToDropdown)
        .find(`.nav-pills li:eq(${index}) a`)
        .tab("show");
}

function shownTabsHandler(e) {
    // works also
    //const $this = $(this);
    const $this = $(e.target);
    const index = $this.parent().index();
    const $parent = $this.closest($tabsToDropdown);
    const $targetDropdownLink = $parent.find(".dropdown-menu a").eq(index);
    const targetDropdownLinkText = $targetDropdownLink.text();
    $parent.find(".dropdown-toggle").text(targetDropdownLinkText);
}

$tabsToDropdown.each(function () {
    const $this = $(this);
    const $pills = $this.find('a[data-toggle="pill"]');

    generateDropdownMarkup($this);

    const $dropdown = $this.find(".dropdown");
    const $dropdownLinks = $this.find(".dropdown-menu a");

    $dropdown.on("show.bs.dropdown", showDropdownHandler);
    $dropdownLinks.on("click", clickHandler);
    $pills.on("shown.bs.tab", shownTabsHandler);
});