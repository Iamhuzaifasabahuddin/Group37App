<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link href="static/project.css" rel="stylesheet" type="text/css">
    <script src="static/script.js" defer></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>

<body class="login-background">
<div class="slant"></div>
<div class="page">
    <header class="login-small">
        <div class="ibm-logo">
            <img class="ibm" src="https://www.pngall.com/wp-content/uploads/2016/03/IBM-White-Logo-PNG.png"
                 alt="IBM Logo">
            <p>SkillsBuild</p>
        </div>
        <h1>Start learning with IBM.</h1>
    </header>


    <div class="ibm-sb">
        <div class="ibm-logo">
            <img class="ibm" src="https://www.pngall.com/wp-content/uploads/2016/03/IBM-White-Logo-PNG.png">
            <p>SkillsBuild </p></div>
        <div class="ibm-text">
            <p style="font-size: 60px">Start</p>
            <p style="font-size: 60px">Learning</p>
            <p style="font-size: 60px">With</p>
            <p style="font-size: 60px">IBM.</p>
        </div>
    </div>


    <div class="container">

        <div class="login-oauth">
            <form action="/myLogin" method="post">
                <p style="font-weight: bolder">Welcome to Our Website!</p>
                <br/>
                <label for="usernameOrEmail" style="font-family: DM Sans, sans-serif">Username/Email:</label>
                <input type="text" id="usernameOrEmail" name="usernameOrEmail" placeholder="Username or Email"
                       class="login-input" required>
                <br/>
                <label for="password" style="font-family: DM Sans, sans-serif">Password:</label>
                <input type="password" id="password" name="password" class="login-input" placeholder="Password" required/>
                <br/>
                <div class="error-message-login">
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            <h3><strong>${error}</strong></h3>
                        </div>
                    </c:if>
                </div>
                <%--                flash redirected message only needs box--%>

                <c:if test="${not empty Message}">
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        <h2><strong>${Message}</strong></h2>
                    </div>
                </c:if>

                <c:if test="${not empty EmailError}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        <h2><strong>${EmailError}</strong></h2>
                    </div>
                </c:if>

                <input type="submit" value="Sign In" class="login-button"/> <br/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            </form>
            <p style="text-align: center">New user? <a style="font-size: large"
                                                       href="${pageContext.request.contextPath}/NewUser"
                                                       class="link-style">Register here</a>.</p>
            <p style="text-align: center">Forgot your password? <a style="font-size: large" href="${pageContext.request.contextPath}/reset-email" class="link-style">Reset it here</a>.</p>

        </div>

    </div>
</div>
</body>
</html>
