const notifications = document.querySelector("#notifications");
const bell = document.querySelector("#bell");
const notificationDropdown = document.querySelector(".nav-item.dropdown");
const standardNav = document.querySelector("#standard-nav");
const hamburgerNav = document.querySelector("#hamburger-nav");
const profileCircle = document.querySelector("#profile-circle");
const noNotifications = document.querySelector(".no-notifications");
const notificationFooter = document.querySelector("#notification-footer");

function switchDisplay(node, display) {
    if (display === "flex") {
        node.classList.add("d-flex");
        node.classList.remove("d-none");
    } else {
        node.classList.remove("d-flex");
        node.classList.add("d-none");
    }
}

function showNotifications() {
    $.ajax({
        url: '/notifications',
        type: 'GET',
        data: {},
        success: function (data) {
            const parsedData = JSON.parse(data);
            while(notifications.children.length > 1) {
                notifications.removeChild(notifications.lastElementChild);
            }
            if (parsedData.notifications.length === 0) {
                switchDisplay(noNotifications, "flex");
                switchDisplay(notificationFooter, "none");
            }
            if (parsedData.notifications.length !== 0) {
                switchDisplay(noNotifications, "none");
                switchDisplay(notificationFooter, "flex");
                for (const notification of parsedData.notifications.reverse()) {
                    const a = document.querySelector("#template").cloneNode(true);
                    a.classList.remove("d-none");
                    const div = a.querySelector("div");
                    div.querySelector(".notification-description").innerText = notification.description;
                    div.querySelector(".notification-time").innerText = jQuery.timeago(notification.timeReceived);
                    div.querySelector("img").src = notification.iconLink;
                    a.href = notification.pageLink;
                    if (!notification.seen) {
                        div.classList.add("bg-secondary-subtle");
                    }
                    a.addEventListener("click", (e) => {
                        if (e.target.classList.contains("btn-close")) {
                            e.preventDefault();
                            clearNotification(notification.id);
                        } else {
                            markAsRead(notification.id);
                        }
                    })
                    notifications.appendChild(a);
                }
            }
        }
    });
}

function markAsRead(id) {
    $.ajax({
        url: '/markAsRead',
        type: 'GET',
        data: {notificationId: id},
        success: function (data) {
        }
    })
}

function clearNotification(id) {
    $.ajax({
        url: '/clearNotification',
        type: 'GET',
        data: {notificationId: id},
        success: function (data) {
            showNotifications();
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

document.querySelector("#clear-notifications").addEventListener("click", () => {
    $.ajax({
        url: '/clearNotification',
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
