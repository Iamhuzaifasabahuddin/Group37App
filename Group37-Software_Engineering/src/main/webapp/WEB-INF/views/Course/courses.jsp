<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
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
    <div class="error-message">${error}</div>
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
</script>
</html>