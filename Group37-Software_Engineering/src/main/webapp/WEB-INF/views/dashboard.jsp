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
    <a href="/dashboard" class="IBM_SkillsBuild">IBM SkillsBuild</a>
    <nav class="navbar">
        <a href="#">Profile</a>
        <a href="/courses">Courses</a>
        <a href="#">Friends</a>
        <a href="#">Leaderboard</a>
        <a href="/logout">Logout</a>
    </nav>
</header>

<h2 class="nav_h2">Welcome to Your Dashboard, ${user.username}!</h2>
<c:if test="${not empty error}">
<div class="error-message">
        ${error}
    <li><a href="/courses">Courses</a></li>
</div>
</c:if>
<c:if test="${not empty message}">
    <h2 id="message" class="message">
            ${message}
    </h2>
</c:if>
    <section>
    <div class="courses">
        <c:forEach items="${courseList}" var="course">
            <div class="card">
                <h3>${course.category}</h3>
                <img src="${course.imageUrl}" alt="${course.title}">
                <h4>${course.title}</h4>
                <p>Duration: ${course.duration} hours</p>
                <a href="${course.link}">Get Started</a>
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
</script>
</html>