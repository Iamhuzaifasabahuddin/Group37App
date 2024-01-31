<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <script src="chrome-extension://nngceckbapebfimnlniiiahkandclblb/content/fido2/page-script.js" id="bw-fido2-page-script"></script>
</head>
<body>
<header>
    <p>IBM SkillsBuild</p>
    <nav>
        <ul>
            <li><a href="/courses">Courses</a></li>
            <li>Friends</li>
            <li>Leaderboard</li>
        </ul>
    </nav>
    <a href="/logout">Logout</a>
</header>
<h2>Welcome to Your Dashboard, ${user.username}!</h2>
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
</html>
