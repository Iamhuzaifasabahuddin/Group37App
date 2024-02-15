<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Profile Information</title>

    <link href="static/project.css" rel="stylesheet" type="text/css">
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
            <li><a href="/leaderboard">Leaderboard</a></li>
            <li><a href="/logout">Logout</a></li>
        </ul>
    </nav>
</header>

<div class="container-profile">

    <!-- The image is a random avatar i found on the web, currently working on the API -->

    <img src="https://png2.cleanpng.com/sh/3a912fcfc1f3f232ef993c93a9ac6e0b/L0KzQYq3UcI2N6JtfZH0aYP2gLBuTgZma6V0ip9wcnHzeLrqk71kd551jeZucj3sc7F1k71kdJp1RdN7dD3sfbLuhb1xd6N5RadsNHK7dYK9UcJla2o7RqYBNki8QYi8UcU1QGU6TaY9NkC6R4S1kP5o/kisspng-vector-graphics-computer-icons-clip-art-image-port-5c4b8e1612dc96.4668917515484554460773.png" alt="Personal Information Image" class="personal-info-image"/>

    <h1>Personal Information</h1>

    <form class="registration">
        <label for="firstName">First name</label>
        <input type="text" id="firstName" class="form-input" name="firstName" value="${user.firstname}" readonly>

        <label for="lastName">Last name</label>
        <input type="text" id="lastName" class="form-input" name="lastName" value="${user.lastname}" readonly>

        <label for="email">Email address</label>
        <input type="email" id="email" class="form-input" name="email" value="${user.email}" readonly>

<%--        <label for="confirmEmail">Confirm email address *</label>--%>
<%--        <input type="email" id="confirmEmail" class="form-input" name="confirmEmail" value="${user.email}" readonly>--%>

        <div class="form-input">
            <strong>Username:</strong> ${user.username}
        </div>
        <div class="form-input">
            <strong>Courses Taken:</strong> ${Courses_taken}
        </div>
        <div class="form-input">
            <strong>Courses Completed:</strong> ${Completed}
        </div>
        <div class="form-input">
            <strong>Percentage Completed:</strong> ${Math.round(Percentage)} %
        </div>
        <div class="form-input">
            <strong>Rank:</strong> ${Rank}
        </div>
        <div class="form-input">
            <strong>Hours Left:</strong> ${Hours}
        </div>

<%--        <input type="submit" class="submit-button" value="Save My Information">--%>
    </form>
</div>
</body>
</html>
