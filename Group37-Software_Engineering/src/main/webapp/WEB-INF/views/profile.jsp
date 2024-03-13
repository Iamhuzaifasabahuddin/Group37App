<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Profile</title>
    <%@include file="includes/head.jsp"%>
</head>
<%@include file="includes/navbar.jsp"%>
<body>

<div class="container-fluid">
 <%--   <h1 class="text-center my-3">Welcome to your profile, ${user.username}!</h1>--%>
    <div class="row mb-5 mt-5">
        <div class="col-md-4 d-flex justify-content-center">
            <img src="https://eu.ui-avatars.com/api/?name=${(user.firstname)}+${(user.lastname)}&size=200"
                 alt="User Initials Image" class="img-thumbnail"/>
        </div>
        <div class="col-md-6">
            <h1>Profile</h1>
            <h5><strong>Full Name:</strong> ${user.firstname} ${user.lastname}</h5>
            <h5><strong>Username:</strong> ${user.username}</h5>
            <h5><strong>Email:</strong> ${user.email}</h5>
            <h5><strong>League:</strong> ${user.league.title}</h5>
            <h5><strong>Rank:</strong> ${Rank}</h5>
            <h5><strong>Points:</strong> ${user.points}</h5>
        </div>

        <div class="col-md-2 d-flex justify-content-center">
            <img src="${league}" alt="League" style="width: 200px; height: 200px;" title="${user.getLeague().title}">
        </div>


    <div class="row mb-5">
        <div class="col-md-12">
            <h1>Achievements</h1>
            <p>This is all the achievements you have gained until now, <c:out value="${user.username}"/>!</p>

            <div class="row mb-5 achievements">
                <c:forEach items="${achievements}" var="achievement">
                    <div class="col-lg-4 col-md-6 col-sm-12 mb-4">
                        <div class="achievement-card ${notachieved.contains(achievement) ? 'locked' : ''}">
                            <img src="${achievement.imageUrl}" alt="${achievement.title}" class="achievement-image ${notachieved.contains(achievement) ? 'locked-img' : ''}">
                            <div class="achievement-info">
                                <h5 class="achievement-title">${achievement.title}</h5>
                                <p class="achievement-desc">${achievement.description}</p>
                                <div class="achievement-meta">
                                    <span class="achievement-time">${achievement.points} points</span>
                                </div>
                                <button class="achievement-btn">${notachieved.contains(achievement) ? 'Locked' : 'View'}</button>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

    <div class="row mb-5">
        <div class="col-md-12">
            <h1>The inspirational quote of the day</h1>
            <h3>"${Quote}"</h3>
        </div>
    </div>
        </div>
    </div>
    </div>
</div>


</body>
</html>