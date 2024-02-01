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
        <label>
            Username:
            <input type="text" name="username" class="login-input" placeholder="User Name" required/>
        </label>
        <br/>
        <br/>
        <label>
            Password:
            <input type="password" name="password" class="login-input" placeholder="Password" required/>
        </label>
        <br/>
        <br/>
        <c:if test="${not empty error}">
            <div class="error-message">
                    ${error}
            </div>
        </c:if>
        <br/>
        <br/>
        <input type="submit" value="Sign In" class="login-button" /> <br/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
    <p>If you are a new user, <a href="${pageContext.request.contextPath}/NewUser" class="link-style">register here</a>.</p>
</div>
</body>
</html>
