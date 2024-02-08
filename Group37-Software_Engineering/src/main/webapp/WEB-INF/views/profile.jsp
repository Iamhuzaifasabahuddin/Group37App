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
            <li><a href="#">Leaderboard</a></li>
            <li><a href="/logout">Logout</a></li>
        </ul>
    </nav>
</header>

<div class="container-profile">

    <!-- The image is a random avatar i found on the web, currently working on the API -->

    <img src="https://www.pngall.com/wp-content/uploads/12/Avatar-Profile-PNG-Image-File.png" alt="Personal Information Image" class="personal-info-image"/>

    <h1>Personal Information</h1>

    <form class="registration">
        <label for="firstName">First name *</label>
        <input type="text" id="firstName" class="form-input" name="firstName" value="${user.firstname}" readonly>

        <label for="lastName">Last name *</label>
        <input type="text" id="lastName" class="form-input" name="lastName" value="${user.lastname}" readonly>

        <label for="email">Email address *</label>
        <input type="email" id="email" class="form-input" name="email" value="${user.email}" readonly>

        <label for="confirmEmail">Confirm email address *</label>
        <input type="email" id="confirmEmail" class="form-input" name="confirmEmail" value="${user.email}" readonly>

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
            <strong>Percentage Completed:</strong> ${Percentage} %
        </div>
        <div class="form-input">
            <strong>Hours Left:</strong> ${Hours}
        </div>

        <input type="submit" class="submit-button" value="Save My Information">
    </form>
</div>
</body>
</html>
