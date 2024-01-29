<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
<h2>Welcome to Our Website!</h2>

<form action="/myLogin" method="post">
    User Name: <input type="text" name="username" /> <br/>
    Password: <input type="password" name="password" /> <br/>
    <input type="submit" value="Sign In" /> <br/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
</form>
<p>If you are a new user, <a href="${pageContext.request.contextPath}/NewUser">register here</a>.</p>
</body>
</html>