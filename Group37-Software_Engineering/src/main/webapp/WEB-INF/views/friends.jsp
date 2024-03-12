<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <title>Friends</title>
    <%@include file="includes/head.jsp"%>
</head>
    <body>
<%@include file="includes/navbar.jsp"%>

<div class="flex-content" style="margin: 1em">
<ul class="nav nav-tabs">
    <li class="nav-item">
        <a class="nav-link active" href="#friends" data-target="friends">Friends</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="#add-friends" data-target="add-friends">Add Friends</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="#requests" data-target="requests">Requests</a>
    </li>
</ul>

<div class="friends" style="margin: 0.5em">
    <ul class="list-group list-group-flush" style="list-style-type: none">
        <li class="list-group-item" id="friends-content">
            <c:forEach items="${friends}" var="friend">
                <form action="/removeFriend">
            <div class="row" style="border-bottom: 0.05em solid var(--primary-darker);">
                <div class="col d-flex align-items-center">
                <img src="https://eu.ui-avatars.com/api/?name=${(friend.getFirstname())}+${(friend.getLastname())}&size=250"
                     alt="User Initials Image" class="rounded-circle">
                    <h5><a href="#" class="profile-link">${friend.getUsername()}</a></h5>
                </div>
                <div class="col d-flex align-items-center" style="transform: scale(0.8);">
                    <input type="hidden" name="receiverUsername" value="${friend.getUsername()}">
                    <button class="btn btn-primary pull-right">Remove Friend</button>
                </div>
            </div>
                </form>
            </c:forEach>
        </li>

        <li class="list-group-item" id="add-friends-content">
            <c:forEach items="${senderRequests}" var="request">
                    <div class="row" style="border-bottom: 0.05em solid var(--primary-darker);">
                        <div class="col d-flex align-items-center">
                        <img src="https://eu.ui-avatars.com/api/?name=${(request.getReceiver().getFirstname())}+${(request.getReceiver().getLastname())}&size=250"
                             alt="User Initials Image" class="rounded-circle"/>
                            <h5><a href="#" class="profile-link">${request.getReceiver().getUsername()}</a></h5>
                        </div>
                        <div class="col d-flex align-items-center" style="transform: scale(0.8);">
                            <p class="btn btn-primary pull-right">Request sent</p>
                        </div>
                    </div>
            </c:forEach>

            <c:forEach items="${users}" var="user">
                <form action="/addFriend">
                    <div class="row" style="border-bottom: 0.05em solid var(--primary-darker);">
                        <div class="col d-flex align-items-center">
                        <img src="https://eu.ui-avatars.com/api/?name=${(user.getFirstname())}+${(user.getLastname())}&size=250"
                             alt="User Initials Image" class="rounded-circle"/>
                            <h5><a href="#" class="profile-link">${user.getUsername()}</a></h5>
                        </div>
                        <div class="col d-flex align-items-center" style="transform: scale(0.8);">
                            <input type="hidden" name="receiver" value="${user.getUsername()}">
                            <button class="btn btn-primary pull-right">Send Request</button>
                        </div>
                    </div>
                </form>
            </c:forEach>
        </li>

        <li class="list-group-item" id="requests-content">
            <c:forEach items="${receiverRequests}" var="request">
                <form action="/handleRequest">
                    <div class="row align-items-center" style="border-bottom: 0.05em solid var(--primary-darker);">
                        <div class="col d-flex align-items-center">
                        <img src="https://eu.ui-avatars.com/api/?name=${(request.getSender().getFirstname())}+${(request.getSender().getLastname())}&size=250"
                             alt="User Initials Image" class="rounded-circle"/>
                            <h5><a href="#" class="profile-link">${request.getSender().getUsername()}</a></h5>
                        </div>
                        <div class="col" style="transform: scale(0.7);">
                            <input type="hidden" name="senderUsername" value="${request.getSender().getUsername()}">
                            <button class="btn btn-primary pull-right" name="decision" value="accept" style="margin-bottom: 0.2em">Accept Request</button>
                            <br/>
                            <button class="btn btn-primary pull-right" name="decision" value="decline">Decline Request</button>
                        </div>
                    </div>
                </form>
            </c:forEach>
        </li>
    </ul>
</div>
</div>

</body>

</html>