<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
            <li>Friends</li>
            <li>Leaderboard</li>
        </ul>
    </nav>
    <a href="/logout">Logout</a>
</header>
<h2>Welcome to Your Dashboard, Hs540!</h2>
<section>
    <div class="courses">
        <c:forEach items="${courseList}" var="course">
            <div class="card">
                <h3>${course.getCategory()}</h3>
                <img src="${course.getImageUrl()}" alt="${course.getTitle()}">
                <h4>${course.getTitle()}</h4>
                <p>Duration: ${course.getDuration()} hours</p>
                <a href="${course.getLink()}">Get Started</a>
            </div>
        </c:forEach>
    </div>
</section>
</body>
</html>