<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <title>Friends</title>
    <%@include file="includes/head.jsp"%>
    <script src="static/friends.js" defer></script>
</head>
    <body>
<%@include file="includes/navbar.jsp"%>

<div class="flex-content p-4">
<ul class="nav nav-tabs mb-3">
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

<div class="friends">
    <ul class="list-group list-group-flush" style="list-style-type: none">
        <li class="list-group-friends" id="friends-content">
        </li>

        <li class="list-group-friends" id="add-friends-content">
        </li>

        <li class="list-group-friends" id="requests-content">
            <c:forEach items="${receiverRequests}" var="request">
                <div class="row align-items-center" style="border-bottom: 0.05em solid var(--primary-darker);">
                    <div class="col d-flex align-items-center">
                    <img src="https://eu.ui-avatars.com/api/?name=${(request.getSender().getFirstname())}+${(request.getSender().getLastname())}&size=250"
                         alt="User Initials Image" class="rounded-circle"/>
                        <h5><a href="#" class="profile-link">${request.getSender().getUsername()}</a></h5>
                    </div>
                    <div class="col" style="transform: scale(0.7);">
                        <input type="hidden" name="senderUsername" value="${request.getSender().getUsername()}">
                        <button class="btn btn-primary pull-right" name="decision" value="accept" data-sender="${request.getSender().getUsername()}" style="margin-bottom: 0.2em">Accept Request</button>
                        <br/>
                        <button class="btn btn-primary pull-right" name="decision" value="decline" data-sender="${request.getSender().getUsername()}">Decline Request</button>
                    </div>
                </div>
            </c:forEach>
        </li>
    </ul>
</div>
</div>

</body>

</html>