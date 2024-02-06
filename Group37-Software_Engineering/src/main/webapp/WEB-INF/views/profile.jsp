<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Profile Information</title>

    <link href="static/project.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
    <h1>My personal information</h1>
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
