<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link href="static/project.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="container">
    <h1>Welcome to Our Website!</h1>
    <br/><br>
    <form action="/myLogin" method="post">
        <br/>
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
        <br/>
        <input type="submit" value="Sign In" class="login-button" /> <br/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
    <a href="${pageContext.request.contextPath}/oauth2/authorization/google">Login with Google</a>
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
