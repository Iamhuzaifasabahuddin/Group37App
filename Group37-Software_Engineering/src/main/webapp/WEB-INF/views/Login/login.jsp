<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link href="static/project.css" rel="stylesheet" type="text/css">
    <script src="static/script.js" defer></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>

<body class="login-background">
<div class="page">
<header class="login-small">
    <div class="ibm-logo">
        <img class="ibm" src="https://www.pngall.com/wp-content/uploads/2016/03/IBM-White-Logo-PNG.png" alt="IBM Logo">
        <p>SkillsBuild</p>
    </div>
    <h1>Start learning with IBM.</h1>
</header>

<div class="slant">

   <div class="ibm-logo">
        <div class="ibm-sb">
       <img class="ibm" src="https://www.pngall.com/wp-content/uploads/2016/03/IBM-White-Logo-PNG.png">
            <p>SkillsBuild </p> </div>
       <div class="ibm-text">
       <p style="font-size: 60px">Start</p>
       <p style="font-size: 60px">Learning</p>
       <p style="font-size: 60px">With</p>
       <p style="font-size: 60px">IBM.</p>
       </div></div>
   </div>
    


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
        <input type="submit" value="Sign In" class="login-button"/> <br/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
    <p style="text-align: center">New user? <a style="font-size: large" href="${pageContext.request.contextPath}/NewUser" class="link-style">Register here</a>.</p>
        </div>

</div>
</div>
</body>
</html>
