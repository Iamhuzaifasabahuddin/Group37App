<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Courses</title>
    <link href="static/project.css" rel="stylesheet" type="text/css">
</head>

<div class="card">
    <h3>${course.getCategory()}</h3>
    <img src="${course.getImageUrl()}" alt="${course.getTitle()}">
    <h4>${course.getTitle()}</h4>
    <p>Duration: ${course.getDuration()} hours</p>
    <a href="${course.getLink()}">Get Started</a>
    <form action="${pageContext.request.contextPath}/enroll" onsubmit="return confirmEnrollment()">
        <input type="hidden" name="course" value="${course.id}">
        <input type="submit" value="Enroll">
    </form>
</div>
</html>