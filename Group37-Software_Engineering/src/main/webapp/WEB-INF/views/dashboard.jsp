<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="static/project.css" rel="stylesheet" type="text/css">
    <title>Dashboard</title>
    <script src="static/script.js" defer></script>
    <script src="chrome-extension://nngceckbapebfimnlniiiahkandclblb/content/fido2/page-script.js"
            id="bw-fido2-page-script"></script>
</head>
<body>
<header class="header">
    <div class="IBM_SkillsBuild">IBM Skills Build</div>
    <nav class="navbar">
        <ul>
            <li><a href="/profile">Profile</a></li>
            <li><a href="/dashboard">Dashboard</a></li>
            <li><a href="/courses">Courses</a></li>
            <%--            <li><a href="#">Friends</a></li>--%>
            <li><a href="/leaderboard">Leaderboard</a></li>
            <li><a href="/logout">Logout</a></li>
        </ul>
    </nav>
</header>

<h1 class="Welcome">Welcome to Your Dashboard, ${user.username}!</h1>
<div class="message_container">
    <c:if test="${not empty error}">
        <div class="error-message">
            <h2>${error}</h2>
            <br/>
            <h2><a href="/courses">Courses</a></h2>
        </div>
    </c:if>
    <%--    <c:if test="${not empty message}">--%>
    <%--        <h2 id="message" class="message">--%>
    <%--                ${message}--%>
    <%--        </h2>--%>
    <%--    </c:if>--%>
</div>
<section>
    <div class="courses">
        <c:forEach items="${userCourses}" var="course">
            <div class="card">
                <div class="card-top">
                    <img src="${course.getCourse().getImageUrl()}" alt="${course.getCourse().getTitle()}">
                    <h4>${course.getCourse().getTitle()}</h4>
                    <p class="category">${course.getCourse().getCategory()}</p>
                </div>
                <div class="card-bottom">
                    <div class="course-details">
                        <p>${Math.round(course.getCourse().getDuration())} hours</p>
                        <p class="divider">|</p>
                        <p>${Math.round(course.getCourse().getDuration()) * 100} points</p>
                    </div>
                    <c:if test="${not empty course.startTime and not empty course.startDate}">
                        <div class="course-details">
                            <p>Start Time:
                                    ${course.startTime}</p>
                            <p class="divider">|</p>
                            <p>Start Date: ${course.startDate}</p>
                        </div>
                        <c:if test="${empty course.endTime and empty course.endDate}">
                            <div class="course-buttons">
                                <a href="${course.getCourse().link}" target="_blank">Continue</a>
                                <div class="button-divider"></div>
                                <a href="/quiz?courseId=${course.getCourse().getId()}">Quiz</a>
                            </div>
                        </c:if>
                    </c:if>
                    <c:if test="${not empty course.endTime and not empty course.endDate}">
                        <div class="course-details">
                            <p>End Time: ${course.endTime}</p>
                            <p class="divider">|</p>
                            <p>End Date: ${course.endDate}</p>
                        </div>
                        <button class="button-completed" disabled>Completed</button>
                    </c:if>
                    <c:if test="${empty course.startTime and empty course.startDate}">
                        <form id="getStartedForm" action="/startTime">
                            <input type="hidden" name="courseId" value="${course.getCourse().id}">
                            <button type="submit" onclick="openLinkAndSubmit('${course.getCourse().link}')">Get
                                started
                            </button>
                        </form>
                    </c:if>

                </div>
            </div>
        </c:forEach>
    </div>
</section>
</body>
</html>