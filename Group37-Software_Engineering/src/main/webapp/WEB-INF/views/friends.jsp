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

<%--<h1>Friends</h1>--%>
<%--<c:forEach items="${friends}" var="friend">--%>
<%--    <p>${friend.getUsername()}</p>--%>
<%--</c:forEach>--%>


<%--<h1>Search Friends</h1>--%>
<%--<c:forEach items="${senderRequests}" var="request">--%>
<%--    <p>${request.getReceiver().getUsername()} | Request sent</p>--%>
<%--</c:forEach>--%>
<%--<c:forEach items="${users}" var="user">--%>
<%--    <form action="/addFriend">--%>
<%--        <p>${user.getUsername()}</p>--%>
<%--        <input type="hidden" name="receiver" value="${user.getUsername()}">--%>
<%--        <button>Send Request</button>--%>
<%--    </form>--%>

<%--<h1>Requests</h1>--%>
<%--<c:forEach items="${receiverRequests}" var="request">--%>
<%--    <form action="/acceptRequest">--%>
<%--        <p>Request from ${request.getSender().getUsername()}</p>--%>
<%--        <input type="hidden" name="senderUsername" value="${request.getSender().getUsername()}">--%>
<%--        <button>Accept Request</button>--%>
<%--    </form>--%>
<%--</c:forEach>--%>

<div class="tabs-to-dropdown" style="transform: scale(0.8);">
    <div class="nav-wrapper d-flex align-items-center justify-content-between">
        <ul class="nav nav-pills d-none d-md-flex" id="pills-tab" role="tablist">
            <li class="nav-item" role="presentation">
                <a class="nav-link active" id="pills-company-tab" data-toggle="pill" href="#pills-company" role="tab" aria-controls="pills-company" aria-selected="true">Friends</a>
            </li>
            <li class="nav-item" role="presentation">
                <a class="nav-link" id="pills-product-tab" data-toggle="pill" href="#pills-product" role="tab" aria-controls="pills-product" aria-selected="false">Search Friends</a>
            </li>
            <li class="nav-item" role="presentation">
                <a class="nav-link" id="pills-news-tab" data-toggle="pill" href="#pills-news" role="tab" aria-controls="pills-news" aria-selected="false">Requests</a>
            </li>
        </ul>
    </div>

    <div class="tab-content" id="pills-tabContent">
        <div class="tab-pane fade show active" id="pills-company" role="tabpanel" aria-labelledby="pills-company-tab">
            <div class="container-fluid">
                <h2 class="mb-3 font-weight-bold">Friends</h2>
                <div class="container">
                    <div class="row">
                        <div class="col-md-8">
                            <c:forEach items="${friends}" var="friend">
<%--                                <form action="/addFriend">--%>
                                    <div class="people-nearby">
                                        <div class="nearby-user">
                                            <div class="row">
                                                <div class="col-md-2 col-sm-2">
                                                    <img src="https://eu.ui-avatars.com/api/?name=${(friend.getFirstname())}+${(friend.getLastname())}&size=250"
                                                         alt="User Initials Image" class="profile-photo-lg"/>
                                                </div>
                                                <div class="col-md-7 col-sm-7">
                                                    <h5><a href="#" class="profile-link">${friend.getUsername()}</a></h5>
                                                    <p>${friend.getFirstname()} ${friend.getLastname()}</p>
                                                    <p class="text-muted">${friend.getPoints()} points</p>
                                                </div>
                                                <div class="col-md-3 col-sm-3">
                                                    <input type="hidden" name="receiver" value="${friend.getUsername()}">
                                                    <button class="btn btn-primary pull-right">Remove Friend</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
<%--                                </form>--%>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="tab-pane fade" id="pills-product" role="tabpanel" aria-labelledby="pills-product-tab">
            <div class="container-fluid">
                <h2 class="mb-3 font-weight-bold">Search Friends</h2>
                <div class="container">
                    <div class="row">
                        <div class="col-md-8">
                            <c:forEach items="${users}" var="user">
                                <form action="/addFriend">
                                    <div class="people-nearby">
                                        <div class="nearby-user">
                                            <div class="row">
                                                <div class="col-md-2 col-sm-2">
                                                    <img src="https://eu.ui-avatars.com/api/?name=${(user.firstname)}+${(user.lastname)}&size=250"
                                                         alt="User Initials Image" class="profile-photo-lg"/>
                                                </div>
                                                <div class="col-md-7 col-sm-7">
                                                    <h5><a href="#" class="profile-link">${user.getUsername()}</a></h5>
                                                    <p>${user.getFirstname()} ${user.getLastname()}</p>
                                                    <p class="text-muted">${user.getPoints()} points</p>
                                                </div>
                                                <div class="col-md-3 col-sm-3">
                                                    <input type="hidden" name="receiver" value="${user.getUsername()}">
                                                    <button class="btn btn-primary pull-right">Send Request</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="tab-pane fade" id="pills-news" role="tabpanel" aria-labelledby="pills-news-tab">
            <div class="container-fluid">
                <h2 class="mb-3 font-weight-bold">Requests</h2>
                <div class="container">
                    <div class="row">
                        <div class="col-md-8">
                            <c:forEach items="${receiverRequests}" var="request">
                                <form action="/acceptRequest">
                                    <div class="people-nearby">
                                        <div class="nearby-user">
                                            <div class="row">
                                                <div class="col-md-2 col-sm-2">
                                                    <img src="https://eu.ui-avatars.com/api/?name=${(request.getSender().getFirstname())}+${(request.getSender().getLastname())}&size=250"
                                                         alt="User Initials Image" class="profile-photo-lg"/>
                                                </div>
                                                <div class="col-md-7 col-sm-7">
                                                    <h5><a href="#" class="profile-link">${request.getSender().getUsername()}</a></h5>
                                                    <p>${request.getSender().getFirstname()} ${request.getSender().getLastname()}</p>
                                                    <p class="text-muted">${request.getSender().getPoints()} points</p>
                                                </div>
                                                <div class="col-md-3 col-sm-3">
                                                    <input type="hidden" name="senderUsername" value="${request.getSender().getUsername()}">
                                                    <button class="btn btn-primary pull-right">Accept Request</button>
                                                    <br/> <br/>
                                                    <input type="hidden" name="senderUsername" value="${request.getSender().getUsername()}">
                                                    <button class="btn btn-primary pull-right">Decline Request</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



</body>

</html>
