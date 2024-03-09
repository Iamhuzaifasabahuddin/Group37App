<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Friends</title>
    <%@include file="includes/head.jsp"%>
</head>
<%@include file="includes/navbar.jsp"%>
<body>
<h1>Friends</h1>
<c:forEach items="${friends}" var="friend">
    <p>${friend.getUsername()}</p>
</c:forEach>
<h1>Search Friends</h1>
<c:forEach items="${senderRequests}" var="request">
    <p>${request.getReceiver().getUsername()} | Request sent</p>
</c:forEach>
<c:forEach items="${users}" var="user">
    <form action="/addFriend">
        <p>${user.getUsername()}</p>
        <input type="hidden" name="receiver" value="${user.getUsername()}">
        <button>Send Request</button>
    </form>

</c:forEach>
<h1>Requests</h1>
<c:forEach items="${receiverRequests}" var="request">
    <form action="/acceptRequest">
        <p>Request from ${request.getSender().getUsername()}</p>
        <input type="hidden" name="senderUsername" value="${request.getSender().getUsername()}">
        <button>Accept Request</button>
    </form>
</c:forEach>

</body>

</html>
