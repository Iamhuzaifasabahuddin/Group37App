const notifications = document.querySelector("#notifications");
const bell = document.querySelector("#bell");
const notificationDropdown = document.querySelector(".nav-item.dropdown");
const standardNav = document.querySelector("#standard-nav");
const hamburgerNav = document.querySelector("#hamburger-nav");
const profileCircle = document.querySelector("#profile-circle");

function updateBellCount(count) {
    const bellBadge = document.querySelector("#bell-badge");
    bellBadge.innerHTML = count > 10 ? '10+' : count.toString();
    bellBadge.style.display = 'inline-block';
}


function showNotifications() {
    $.ajax({
        url: '/notifications',
        type: 'GET',
        data: {},
        success: function (data) {
            const parsedData = JSON.parse(data);
            var UnseenCount = 0;
            if (parsedData.notifications.length !== 0) {
                notifications.innerHTML = "";
                for (const notification of parsedData.notifications.reverse()) {
                    const a = document.querySelector("#template").cloneNode(true);
                    a.classList.remove("d-none");
                    const div = a.querySelector("div");
                    div.querySelector(".notification-description").innerText = notification.description;
                    div.querySelector(".notification-time").innerText = jQuery.timeago(notification.timeReceived);
                    div.querySelector("img").src = notification.iconLink;2
                    a.href = notification.pageLink;
                    if (!notification.seen) {
                        div.classList.add("bg-secondary-subtle");
                        UnseenCount++;
                    }
                    a.addEventListener("click", (e) => {
                        markAsRead(notification.id);
                    })
                    notifications.appendChild(a);
                }
                updateBellCount(UnseenCount);
            }
        }
    });
}
showNotifications();
function markAsRead(id) {
    $.ajax({
        url: '/markAsRead',
        type: 'GET',
        data: {notificationId: id},
        success: function (data) {
        }
    })
}

bell.addEventListener("click", () => {
    showNotifications();
})

document.querySelector("#mark-read").addEventListener("click", () => {
    $.ajax({
        url: '/markAsRead',
        type: 'GET',
        data: {},
        success: function (data) {
            showNotifications();
        }
    })
});


const config = {attributes: true};
const callback = (mutationList, observer) => {
    for (const mutation of mutationList) {
        if (mutation.attributeName === "aria-expanded") {
            bell.classList.toggle("bi-bell");
            bell.classList.toggle("bi-bell-fill");
        }
    }
};
const observer = new MutationObserver(callback);
observer.observe(bell, config);

moveBell();

function moveBell() {
    if (window.innerWidth < 768 && standardNav.contains(notificationDropdown)) {
        standardNav.removeChild(notificationDropdown);
        standardNav.removeChild(profileCircle);
        hamburgerNav.appendChild(notificationDropdown);
        hamburgerNav.appendChild(profileCircle);
    } else if (window.innerWidth >= 768 && hamburgerNav.contains(notificationDropdown)) {
        hamburgerNav.removeChild(notificationDropdown);
        hamburgerNav.removeChild(profileCircle);
        standardNav.appendChild(notificationDropdown);
        standardNav.appendChild(profileCircle);
    }
}

window.addEventListener("resize", () => {
    moveBell();
});
