function sendRequest(e, receiverUsername) {
    $.ajax({
        url: '/sendRequest',
        type: 'GET',
        data: {receiverUsername: receiverUsername},
        success: function (data) {
            e.target.textContent = "Request Sent";
            e.target.disabled = true;
        }
    });

}

function removeFriend(e, receiverUsername) {
    $.ajax({
        url: '/removeFriend',
        type: 'GET',
        data: {receiverUsername: receiverUsername},
        success: function (data) {
            e.target.parentNode.parentNode.style.display = "none";
            getNewFriends(false);
        }
    });

}

function handleRequest(e, senderUsername) {
    $.ajax({
        url: '/handleRequest',
        type: 'GET',
        data: {senderUsername: senderUsername, decision: e.target.getAttribute("value")},
        success: function (data) {
            const parsedData = JSON.parse(data);
            e.target.parentNode.parentNode.remove();
            getNewFriends(parsedData.decision);
        }
    });
}

function createAlert(message) {
    const alert = document.createElement("div");
    alert.innerHTML = `
        <div class="alert alert-primary d-flex align-items-center alert-dismissible fade show" role="alert">
            <div><i class="bi bi-info-circle-fill"></i>
                ${message}
            </div>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    `;
    return alert;
}

function getNewFriends(decision) {
    $.ajax({
        url: '/getFriends',
        type: 'GET',
        data: {},
        success: function (response) {
            const parsedData = JSON.parse(response);
            const requests = document.querySelector("li#requests-content");
            if (requests.children.length === 0) {
                requests.appendChild(createAlert("No incoming requests."));
            }
            if (decision) {
                const li = document.querySelector("li#friends-content");
                li.innerHTML = "";
                if (parsedData.friends.length === 0) {
                    li.appendChild(createAlert("You have no friends."));
                }
                for (const friend of parsedData.friends) {
                    const newFriend = document.createElement("div");
                    newFriend.innerHTML = `
                            <div class="row" style="border-bottom: 0.05em solid var(--primary-darker);">
                                <div class="col d-flex align-items-center">
                                    <img src="https://eu.ui-avatars.com/api/?name=${friend.firstname}+${friend.lastname}&size=250"
                                         alt="User Initials Image" class="rounded-circle">
                                    <h5><a href="#" class="profile-link">${friend.username}</a></h5>
                                </div>
                                <div class="col d-flex align-items-center" style="transform: scale(0.8);">
                                    <input type="hidden" name="receiverUsername" value="${friend.username}">
                                    <button class="btn btn-primary pull-right remove-friend" data-receiver="${friend.username}">Remove Friend</button>
                                </div>
                            </div>
                            `;
                    const button = newFriend.querySelector("button");
                    button.addEventListener("click", e => {
                        removeFriend(e, button.getAttribute("data-receiver"));

                    })
                    li.appendChild(newFriend);
                }
            } else {
                const li = document.querySelector("li#add-friends-content");
                li.innerHTML = "";
                if (parsedData.senderRequests.length === 0 && parsedData.users.length === 0) {
                    li.appendChild(createAlert("No other users."));
                }
                for (const request of parsedData.senderRequests) {
                    const senderRequest = document.createElement("div");
                    senderRequest.innerHTML = `
                    <div class="row" style="border-bottom: 0.05em solid var(--primary-darker);">
                        <div class="col d-flex align-items-center">
                        <img src="https://eu.ui-avatars.com/api/?name=${request.receiver.firstname}+${request.receiver.lastname}&size=250"
                             alt="User Initials Image" class="rounded-circle"/>
                            <h5><a href="#" class="profile-link">${request.receiver.username}</a></h5>
                        </div>
                        <div class="col d-flex align-items-center" style="transform: scale(0.8);">
                            <p class="btn btn-primary pull-right disabled">Request sent</p>
                        </div>
                    </div>
                    `;
                    li.appendChild(senderRequest);
                }
                for (const request of parsedData.users) {
                    const newRequest = document.createElement("div");
                    newRequest.innerHTML = `
                           
                    <div class="row" style="border-bottom: 0.05em solid var(--primary-darker);">
                        <div class="col d-flex align-items-center">
                        <img src="https://eu.ui-avatars.com/api/?name=${request.firstname}+${request.lastname}&size=250"
                             alt="User Initials Image" class="rounded-circle"/>
                            <h5><a href="#" class="profile-link">${request.username}</a></h5>
                        </div>
                        <div class="col d-flex align-items-center" style="transform: scale(0.8);">
                            <input type="hidden" name="receiver" value="${request.username}">
                            <button class="btn btn-primary pull-right send-request" data-receiver="${request.username}">Send Request</button>
                        </div>
                    </div>
                            `;
                    const button = newRequest.querySelector("button");
                    button.addEventListener("click", e => {
                        sendRequest(e, button.getAttribute("data-receiver"));
                    })
                    li.appendChild(newRequest);
                }
            }

        }
    });
}

window.onload = function() {
    getNewFriends(true);
    getNewFriends(false);
};

document.querySelectorAll("button[value]").forEach(button => {
    button.addEventListener("click", e => {
        handleRequest(e, button.getAttribute("data-sender"));
    })
})