function changeHues() {
    const cards = document.querySelectorAll(".card");
    const categories = ["ARTIFICIAL INTELLIGENCE", "CLOUD", "DATA SCIENCE", "CYBERSECURITY", "SUSTAINABILITY"];
    const angles = [145, 180, 215, 250,  285].reverse();
    cards.forEach(card => {
        const category = card.querySelector(".category").textContent;
        const angle = angles[categories.indexOf(category.toUpperCase())];
        card.querySelector("img").style.filter = `hue-rotate(${angle}deg)`;
    });
}

function showConfirmationBox(id, title) {
    document.querySelector(".confirmation-background").style.display = "flex";
    document.querySelector(".input-course-id").value = id;
    document.querySelector(".confirmation-box p").innerHTML = `Are you sure you want to enroll in <b>${title}</b>?`;
}

function closeConfirmationBox() {
    document.querySelector(".confirmation-background").style.display = "none";
}

window.onload = function() {
    var errorElement = document.getElementById('error-message');
    if (errorElement) {
        setTimeout(function() {
            errorElement.style.display = 'none';
        }, 5000);
    }
    changeHues();
};

var prevScrollpos = window.pageYOffset;
window.onscroll = function() {
    var currentScrollPos = window.pageYOffset;
    if (prevScrollpos > currentScrollPos) {
        document.getElementById("navbar").style.top = "0";
    } else {
        document.getElementById("navbar").style.top = "-50px";
    }
    prevScrollpos = currentScrollPos;
}

var messageElement = document.getElementById("message");
setTimeout(function() {
    messageElement.style.display = "none";
}, 5000);

var errorMessageElement = document.getElementById("error-message");
setTimeout(function() {
    errorMessageElement.style.display = "none";
}, 5000);
function openLinkAndSubmit(courseLink) {
    window.open(courseLink, '_blank');
    document.getElementById('getStartedForm').submit();
}

