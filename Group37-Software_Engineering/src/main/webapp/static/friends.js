function sendRequest(e, receiverUsername) {
    $.ajax({
        url: '/sendRequest',
        type: 'GET',
        data: {receiverUsername: receiverUsername},
        success: function (data) {
            e.target.textContent = "Request Sent";
            e.target.disabled = true;
            const searchResultsContainer = document.getElementById("search-results-container");
            const userElement = searchResultsContainer.querySelector(`[data-receiver="${receiverUsername}"]`).closest(".row");
            if (userElement) {
                const buttonElement = userElement.querySelector(".send-request");
                buttonElement.textContent = "Request Sent";
                buttonElement.disabled = true;
            }
            getNewFriends(false);
        }
    });
}

function removeFriend(e, receiverUsername) {
    $.ajax({
        url: '/removeFriend',
        type: 'GET',
        data: {receiverUsername: receiverUsername},
        success: function (data) {
            e.target.parentNode.parentNode.remove();
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
            const number = document.querySelector("#request-badge");
            number.textContent = parseInt(number.textContent) - 1;
            getNewFriends(parsedData.decision);
            showNotifications();
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
    return alert.children[0];
}

function getNewFriends(decision) {
    $.ajax({
        url: '/getFriends',
        type: 'GET',
        data: {},
        success: function (response) {
            var reqCount = 0;
            const parsedData = JSON.parse(response);
            const mutualFriends = parsedData.mutual;
            const friends = document.querySelector("li#friends-content");
            const requests = document.querySelector("li#requests-content");
            if (requests.children.length === 0) {
                updateRequestsCount(0);
                requests.appendChild(createAlert("No incoming requests."));
            }
            else {

                const dropdown = document.querySelectorAll(".dropdown.mutual-friends");
                dropdown.forEach(dd => {dd.remove()});

                const li = document.querySelector("li#requests-content");

                for (const reqs of parsedData.receiverRequests) {
                    reqCount++;
                    const elem = li.querySelector(
                    `[data-sender-username='${reqs.sender.username}']`

                );

                    updateRequestsCount(reqCount);
                    const newRequests = document.createElement("div");

                newRequests.innerHTML = `
                    <div class="dropdown mutual-friends">
                            <a href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false" style="color: slategray; text-decoration: none;">
                            ${mutualFriends[reqs.sender.username].length === 0 ? "No" : mutualFriends[reqs.sender.username].length} Mutual Friends
                            </a>
                            <h6 class="dropdown-menu text-center p-2">${mutualFriends[reqs.sender.username].length === 0 ? "Nothing to View Here" : mutualFriends[reqs.sender.username]}</h6>
                            </div>`;

                elem.appendChild(newRequests);
                }
            }
            if (parsedData.friends.length === 0) {
                friends.appendChild(createAlert("You have no friends."));
            }
            if (decision) {
                friends.innerHTML = "";
                for (const friend of parsedData.friends) {

                    const newFriend = document.createElement("div");
                    newFriend.innerHTML = `
                            <div class="row" style="border-bottom: 0.05em solid var(--primary-darker);">
                                <div class="col d-flex align-items-center">
                                    <img src="https://eu.ui-avatars.com/api/?name=${friend.firstname}+${friend.lastname}&size=250"
                                         alt="User Initials Image" class="rounded-circle">
                                    <h5><a href="/friend-profile?username=${friend.username}" class="profile-link">${friend.username}</a></h5>
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
                    friends.appendChild(newFriend.children[0]);
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
                            <div>
                            <h5><a class="profile-link">${request.receiver.username}</a></h5>
                            <div class="dropdown">
                            <a href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false" style="color: slategray; text-decoration: none;">
                            ${mutualFriends[request.receiver.username].length === 0 ? "No" : mutualFriends[request.receiver.username].length} Mutual Friends
                            </a>
                            <h6 class="dropdown-menu text-center p-2">${mutualFriends[request.receiver.username].length === 0 ? "Nothing to View Here" : mutualFriends[request.receiver.username]}</h6>
                            </div>
                            </div>
                        </div>
                        <div class="col d-flex align-items-center" style="transform: scale(0.8);">
                            <p class="btn btn-primary pull-right disabled">Request sent</p>
                        </div>
                    </div>
                    `;
                    li.appendChild(senderRequest.children[0]);
                }
                for (const request of parsedData.users) {
                    const newRequest = document.createElement("div");
                    newRequest.innerHTML = `
                           
                    <div class="row" style="border-bottom: 0.05em solid var(--primary-darker);">
                        <div class="col d-flex align-items-center">
                        <img src="https://eu.ui-avatars.com/api/?name=${request.firstname}+${request.lastname}&size=250"
                             alt="User Initials Image" class="rounded-circle"/>
                            <div>
                            <h5><a class="profile-link">${request.username}</a></h5>
                            <div class="dropdown">
                            <a href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false" style="color: slategray; text-decoration: none;">
                            ${mutualFriends[request.username].length === 0 ? "No" : mutualFriends[request.username].length} Mutual Friends
                            </a>
                            <h6 class="dropdown-menu text-center p-2">${mutualFriends[request.username].length === 0 ? "Nothing to view here" : mutualFriends[request.username]}</h6>
                            </div>
                            </div>
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
                    li.appendChild(newRequest.children[0]);
                }
            }

        }
    });
}


const searchResultsContainer = document.getElementById("search-results-container");

function searchFriends(searchTerm) {
    searchResultsContainer.innerHTML = "";
    if (searchTerm.trim() === "") {
        searchResultsContainer.appendChild(createAlert("Please enter a search term."));
        return;
    }
    $.ajax({
        url: '/SearchFriends',
        type: 'GET',
        data: { search: searchTerm },
        success: function (response) {
            const parsedData = JSON.parse(response);
            displaySearchResults(parsedData.search, parsedData.mutual, parsedData.senderRequests);
        }
    });
}


function displaySearchResults(users, mutualFriends, senderRequests) {
    searchResultsContainer.innerHTML = "";

    if (!users || users.length === 0) {
        searchResultsContainer.appendChild(createAlert("No users found."));
        return;
    }

    for (const user of users) {
        const userElement = document.createElement("div");
        const requestSent = senderRequests.some(request => request.receiver.username === user.username);

        userElement.innerHTML = `
            <div class="row" style="border-bottom: 0.05em solid var(--primary-darker);">
                <div class="col d-flex align-items-center">
                    <img src="https://eu.ui-avatars.com/api/?name=${user.firstname}+${user.lastname}&size=250" alt="User Initials Image" class="rounded-circle"/>
                    <div>
                        <h5><a class="profile-link">${user.username}</a></h5>
                        <div class="dropdown">
                            <a href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false" style="color: slategray; text-decoration: none;">
                                ${mutualFriends[user.username].length === 0 ? "No" : mutualFriends[user.username].length} Mutual Friends
                            </a>
                            <h6 class="dropdown-menu text-center p-2">${mutualFriends[user.username].length === 0 ? "Nothing to view here" : mutualFriends[user.username]}</h6>
                        </div>
                    </div>
                </div>
                <div class="col d-flex align-items-center" style="transform: scale(0.8);">
                    <input type="hidden" name="receiver" value="${user.username}">
                    ${requestSent ? '<p class="btn btn-primary pull-right disabled">Request Sent</p>' 
            : `<button class="btn btn-primary pull-right send-request" data-receiver="${user.username}">Send Request</button>`
        }
                </div>
            </div>
        `;

        if (!requestSent) {
            const button = userElement.querySelector("button");
            button.addEventListener("click", e => {
                sendRequest(e, button.getAttribute("data-receiver"));
            });
        }

        searchResultsContainer.appendChild(userElement.children[0]);
    }
}
const searchTermInput = document.getElementById("searchTerm");

searchTermInput.addEventListener("keyup", function(event) {
    searchFriends(searchTermInput.value);
});


function updateRequestsCount(number) {
    const requestBadge = document.getElementById("request-badge");
    requestBadge.innerHTML = number > 10 ? '10+' : number.toString();
    requestBadge.style.display = 'inline-block';
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