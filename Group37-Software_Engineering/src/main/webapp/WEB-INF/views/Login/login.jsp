<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link href="static/project.css" rel="stylesheet" type="text/css">
</head>
<body class="login-background">

<div class="container">

        <div class="login-oauth">
    <form action="/myLogin" method="post">
        <h1 style="font-size: 175%">Welcome to Our Dashboard!</h1>
        <label for="usernameOrEmail">Username/Email:</label>
        <input type="text" id="usernameOrEmail" name="usernameOrEmail" placeholder="Username or Email" class="login-input" required>
        <br/>
        <br/>
        <label>
            Password:
            <input type="password" name="password" class="login-input" placeholder="Password" required/>
        </label>
        <br/>
        <c:if test="${not empty error}">
            <h2 id="error-message" class="error-message">${error}</h2>
        </c:if>
        <input type="submit" value="Sign In" class="login-button" /> <br/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>

    <a href="${pageContext.request.contextPath}/oauth2/authorization/google">Login with Google</a>
    <p>New user? <a style="font-size: large" href="${pageContext.request.contextPath}/NewUser" class="link-style">Register here</a>.</p>
        </div>

    <img class="logo"  src ="https://img1.wsimg.com/isteam/ip/6f5993eb-07fd-4e54-a10f-d1d3850f5f51/ibmskillsbuild-gmen.png/:/">

<%--    <a href="${pageContext.request.contextPath}/oauth2/authorization/google">Login with Google</a>--%>
    <p>If you are a new user, <a href="${pageContext.request.contextPath}/NewUser" class="link-style">register here</a>.</p>
</div>
</body>
<script>
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
