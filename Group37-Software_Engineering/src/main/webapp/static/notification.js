const notifications = document.querySelector("#notifications");
const bell = document.querySelector("#bell");
function showNotifications() {
    $.ajax({
        url: '/notifications',
        type: 'GET',
        data: {},
        success: function (data) {
            const parsedData = JSON.parse(data);
            notifications.innerHTML = "";
            for (const notification of parsedData.notifications.reverse()) {
                const a = document.querySelector("#template").cloneNode(true);
                a.classList.remove("d-none");
                const div = a.querySelector("div");
                const description = div.querySelector(".notification-description");
                const time = div.querySelector(".notification-time");
                const img = div.querySelector("img");
                description.innerText = notification.description;
                time.innerText = jQuery.timeago(notification.timeReceived);
                img.src = notification.iconLink;
                a.href = notification.pageLink;
                if (!notification.seen) {
                    div.classList.add("bg-secondary-subtle");
                }
                a.addEventListener("click", (e) => {
                    markAsRead(notification.id);
                })
                notifications.appendChild(a);
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
