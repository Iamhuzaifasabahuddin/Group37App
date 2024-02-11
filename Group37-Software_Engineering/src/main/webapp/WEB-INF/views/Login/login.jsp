<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link href="static/project.css" rel="stylesheet" type="text/css">
    <script src="static/script.js" defer></script>
</head>
<body class="login-background">

<div class="container">

        <div class="login-oauth">
    <form action="/myLogin" method="post">
        <p style="font-weight: bolder">Welcome to Our Website!</p>
        <br/>
        <label for="usernameOrEmail" style="font-family: DM Sans, sans-serif">Username/Email:</label>
        <input type="text" id="usernameOrEmail" name="usernameOrEmail" placeholder="Username or Email" class="login-input" required>
        <br/>
        <label style="font-family: DM Sans, sans-serif">
            Password:
            <input type="password" name="password" class="login-input" placeholder="Password" required/>
        </label>
        <br/>
        <div class="error-message-login">
        <c:if test="${not empty error}">
            <h3 id="error-message" >${error}</h3>
        </c:if>
        </div>
        <input type="submit" value="Sign In" class="login-button" style="border-radius: 18px" /> <br/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
    <p style="text-align: center">New user? <a style="font-size: large" href="${pageContext.request.contextPath}/NewUser" class="link-style">Register here</a>.</p>
        </div>

    <img class="logo"  src ="https://img1.wsimg.com/isteam/ip/6f5993eb-07fd-4e54-a10f-d1d3850f5f51/ibmskillsbuild-gmen.png/:/" alt="skills build logo">
</div>
</body>
</html>
