<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Courses</title>
    <link href="static/project.css" rel="stylesheet" type="text/css">
</head>
<header>
    <p>IBM SkillsBuild</p>
    <nav>
        <ul>
            <li><a href="/dashboard">Profile</a></li>
            <li>Friends</li>
            <li>Leaderboard</li>

        </ul>
    </nav>
    <a href="/logout">Logout</a>
</header>
<h2>Welcome, ${user.username}!</h2>
<h2>Here is the list of all available courses: </h2>
<c:if test="${not empty error}">
    <h2 id="error-message" class="error-message">${error}</h2>
</c:if>

<section>
    <div class="courses">
        <c:forEach items="${courseList}" var="course">
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
        </c:forEach>
    </div>
</section>
<script>
    function confirmEnrollment() {
        var confirmation = confirm("Are you sure you want to enroll in this course?");

        return confirmation;
    }

    window.onload = function() {
        var errorElement = document.getElementById('error-message');
        if (errorElement) {
            setTimeout(function() {
                errorElement.style.display = 'none';
            }, 5000);
        }
    };
</script>

</html>