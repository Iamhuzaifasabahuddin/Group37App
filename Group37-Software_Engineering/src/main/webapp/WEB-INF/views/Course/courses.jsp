<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Courses</title>
    <link href="static/project.css" rel="stylesheet" type="text/css">
</head>

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
<body class="coursesBackground">
<br/>
<h1 class="Welcome">Welcome, ${user.username}!</h1>
<br/>
<br/>
<div class="filter_container">
    <form action="${pageContext.request.contextPath}/filter" method="get" id="filterForm">
        <label for="category">Select Category:</label>
        <select id="category" name="category">
            <option value="Artificial Intelligence">AI</option>
            <option value="Cloud">Cloud</option>
            <option value="Data Science">Data Science</option>
            <option value="CyberSecurity">CyberSecurity</option>
        </select>
        <input type="submit" value="Sort by Course">
    </form>

    <form action="/search" method="get">
        <label for="searchTerm">Search Courses: </label
        ><input type="text" name="searchTerm" id="searchTerm">
        <input type="submit">
    </form>

    <form action="/duration" method="get">
        <label for="duration">Select Duration:</label>
        <select name="duration" id="duration">
            <option value="5.0 <">5 hours</option>
            <option value="5.0 >">5 hours</option>
            <option value="10.0 >">10 hours</option>
            <option value="20.0">20 hours</option>
        </select>
        <input type="submit" value="Sort By Duration">
    </form>
</div>

<br/>
<h2 class="list_courses">Available courses: </h2>
<c:if test="${not empty error}">
    <h2 id="error-message" class="error-message">${error}</h2>
</c:if>

<section>
    <div class="course-message-container">
        <c:if test="${not empty Courseerror}">
            <h2 id="course-error-message" class="course-error-message">${Courseerror}</h2>
        </c:if>
    </div>
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
                    <button class="button-enroll" onclick="showConfirmationBox(${course.id}, `${course.getTitle()}`)">Enroll</button>
                </div>
            </div>
        </c:forEach>
    </div>
</section>
<div class="confirmation-background">
    <div class="confirmation-box">
        <h4>Confirm Enrollment</h4>
        <p></p>
        <form action="${pageContext.request.contextPath}/enroll">
            <input class="input-course-id" type="hidden" name="course" value="">
            <button class="cancel" onclick="closeConfirmationBox()" type="reset">Cancel</button>
            <button class="confirm">Confirm</button>
        </form>
    </div>
</div>
</body>
<script>
    function changeHues() {
        const cards = document.querySelectorAll(".card");
        const categories = ["ARTIFICIAL INTELLIGENCE", "CLOUD", "DATA SCIENCE", "CYBERSECURITY", "SUSTAINABILITY"];
        const angles = [0, 45, 180, 270, 315];
        cards.forEach(card => {
            const category = card.querySelector(".category").textContent;
            const angle = angles[categories.indexOf(category.toUpperCase())];
            card.querySelector("img").style.filter = `hue-rotate(\${angle}deg)`;
        });
    }

    function showConfirmationBox(id, title) {
        document.querySelector(".confirmation-background").style.display = "flex";
        document.querySelector(".input-course-id").value = id;
        document.querySelector(".confirmation-box p").innerHTML = `Are you sure you want to enroll in <b>\${title}</b>?`;
    }

    function closeConfirmationBox() {
        document.querySelector(".confirmation-background").style.display = "none";
    }

    window.onload = function() {
        var errorElement = document.getElementById('error-message');
        if (errorElement) {
            setTimeout(function() {
                errorElement.style.display = 'none';
            }, 5000);
        }
        changeHues();
    };

    var prevScrollpos = window.pageYOffset;
    window.onscroll = function() {
        var currentScrollPos = window.pageYOffset;
        if (prevScrollpos > currentScrollPos) {
            document.getElementById("navbar").style.top = "0";
        } else {
            document.getElementById("navbar").style.top = "-50px";
        }
        prevScrollpos = currentScrollPos;
    }
</script>

</html>