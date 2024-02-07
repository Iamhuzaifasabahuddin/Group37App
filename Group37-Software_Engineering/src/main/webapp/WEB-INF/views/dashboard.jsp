<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="static/project.css" rel="stylesheet" type="text/css">
    <title>Dashboard</title>
    <script src="chrome-extension://nngceckbapebfimnlniiiahkandclblb/content/fido2/page-script.js" id="bw-fido2-page-script"></script>
</head>
<body>

<header class="header">
    <div class="IBM_SkillsBuild">IBM Skills Build</div>
    <nav class="navbar">
        <ul>
            <li><a href="/profile">Profile</a> </li>
            <li><a href="/dashboard">Dashboard</a></li>
            <li><a href="/courses">Courses</a></li>
            <li><a href="#">Friends</a></li>
            <li><a href="#">Leaderboard</a></li>
            <li><a href="/logout">Logout</a></li>
        </ul>
    </nav>
</header>

<h1 class="Welcome">Welcome to Your Dashboard, ${user.username}!</h1>
<div class="message_container">
    <c:if test="${not empty error}">
        <div class="error-message">
                ${error}
            <h2>
                <a href="/courses">Courses</a>
            </h2>
        </div>
    </c:if>
    <c:if test="${not empty message}">
        <h2 id="message" class="message">
                ${message}
        </h2>
    </c:if>
</div>
    <section>
        <div class="courses">
            <c:forEach items="${courseList}" var="course">
                <div class="card">
                    <div class="card-top">
                        <img src="${course.getImageUrl()}" alt="${course.getTitle()}">
                        <h4>${course.getTitle()}</h4>
                        <p class="category">${course.getCategory()}</p>
                    </div>
                    <div class="card-bottom">
                        <div class="course-details">
                            <p>${Math.round(course.getDuration())} hours</p>
                            <p class="divider">|</p>
                            <p>${Math.round(course.getDuration()) * 100} points</p>
                        </div>
                        <a href="${course.link}" target="_blank">Get Started</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </section>
</body>
<script>
    window.onload = function() {
        var messageElement = document.getElementById('message');
        if (messageElement) {
            setTimeout(function() {
                messageElement.style.display = 'none';
            }, 5000);
        }
    };

    var prevScrollpos = window.pageYOffset;
    window.onscroll = function() {
        var currentScrollPos = window.pageYOffset;
        if (prevScrollpos > currentScrollPos) {
            document.getElementById("header").style.top = "0";
        } else {
            document.getElementById("header").style.top = "-50px";
        }
        prevScrollpos = currentScrollPos;
    }
</script>
</html>