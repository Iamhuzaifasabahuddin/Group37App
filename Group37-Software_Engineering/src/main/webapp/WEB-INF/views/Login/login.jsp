<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <title>Home</title>--%>
<%--    <link href="static/project.css" rel="stylesheet" type="text/css">--%>
<%--    <script src="static/script.js" defer></script>--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>

<%--</head>--%>

<%--<body class="login-background">--%>
<%--<div class="slant"></div>--%>
<%--<div class="page">--%>
<%--    <header class="login-small">--%>
<%--        <div class="ibm-logo">--%>
<%--            <img class="ibm" src="https://www.pngall.com/wp-content/uploads/2016/03/IBM-White-Logo-PNG.png"--%>
<%--                 alt="IBM Logo">--%>
<%--            <p>SkillsBuild</p>--%>
<%--        </div>--%>
<%--        <h1>Start learning with IBM.</h1>--%>
<%--    </header>--%>


<%--    <div class="ibm-sb">--%>
<%--        <div class="ibm-logo">--%>
<%--            <img class="ibm" src="https://www.pngall.com/wp-content/uploads/2016/03/IBM-White-Logo-PNG.png">--%>
<%--            <p>SkillsBuild </p></div>--%>
<%--        <div class="ibm-text">--%>
<%--            <p style="font-size: 60px">Start</p>--%>
<%--            <p style="font-size: 60px">Learning</p>--%>
<%--            <p style="font-size: 60px">With</p>--%>
<%--            <p style="font-size: 60px">IBM.</p>--%>
<%--        </div>--%>
<%--    </div>--%>


<%--    <div class="container">--%>

<%--        <div class="login-oauth">--%>
<%--            <form action="/myLogin" method="post">--%>
<%--                <p style="font-weight: bolder">Welcome to Our Website!</p>--%>
<%--                <br/>--%>
<%--                <label for="usernameOrEmail" style="font-family: DM Sans, sans-serif">Username/Email:</label>--%>
<%--                <input type="text" id="usernameOrEmail" name="usernameOrEmail" placeholder="Username or Email"--%>
<%--                       class="login-input" required>--%>
<%--                <br/>--%>
<%--                <label style="font-family: DM Sans, sans-serif">--%>
<%--                    Password:--%>
<%--                    <input type="password" name="password" class="login-input" placeholder="Password" required/>--%>
<%--                </label>--%>
<%--                <br/>--%>
<%--                <div class="error-message-login">--%>
<%--                    <c:if test="${not empty error}">--%>
<%--                        <h3 id="error-message">${error}</h3>--%>
<%--                    </c:if>--%>
<%--                </div>--%>
<%--                <input type="submit" value="Sign In" class="login-button"/> <br/>--%>
<%--                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">--%>
<%--            </form>--%>
<%--            <p style="text-align: center">New user? <a style="font-size: large"--%>
<%--                                                       href="${pageContext.request.contextPath}/NewUser"--%>
<%--                                                       class="link-style">Register here</a>.</p>--%>
<%--        </div>--%>

<%--    </div>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <%@include file="../includes/head.jsp"%>
</head>
<body>
<div class="slant"></div>
<div class="mb-5 p-4 top-title">
    <img class="ibm-logo d-inline" src="https://www.pngall.com/wp-content/uploads/2016/03/IBM-White-Logo-PNG.png"
         alt="IBM Logo">
    <h1 class="d-inline s-light mb-0">Start Learning With IBM</h1>
</div>
<div class="p-4">
    <div class="row mb-5 left-title">
        <div class="col">
            <img class="ibm-logo" src="https://www.pngall.com/wp-content/uploads/2016/03/IBM-White-Logo-PNG.png"
                 alt="IBM Logo">
            <p class="s-light">SkillsBuild</p>
        </div>
    </div>
    <div class="row">
        <div class="col s-light left-title">
            <h1>Start</h1>
            <h1>Learning</h1>
            <h1>With</h1>
            <h1>IBM.</h1>
        </div>
        <div class="col">
            <h1>Welcome to our website!</h1>
            <form action="/login" method="post" class="needs-validation mb-2" novalidate>
                <div class="form-floating mb-3">
                    <input class="form-control" id="username-email" name="username-email" placeholder="Username or email" required>
                    <label for="username-email" class="form-label">Username or email</label>
                    <div class="invalid-feedback">
                        Username/Email cannot be empty!
                    </div>
                </div>
                <div class="form-floating mb-3">
                    <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
                    <label for="password" class="form-label">Password</label>
                    <div class="invalid-feedback">
                        Password cannot be empty!
                    </div>
                </div>
                <c:if test="${not empty error}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <strong>${error}</strong>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>
                <c:if test="${not empty Message}">
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        <strong>${Message}</strong>
                    </div>
                </c:if>

                <c:if test="${not empty EmailError}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        <strong>${EmailError}</strong>
                    </div>
                </c:if>

        <button type="submit" class="btn btn-primary">
                    <i class="bi bi-box-arrow-in-right"></i>
                    Log In
                </button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            </form>
            <p>New user? <a href="${pageContext.request.contextPath}/NewUser">Register Here</a></p>
            <p>Forgot your password? <a href="${pageContext.request.contextPath}/reset-email">Reset it here</a>.</p>
        </div>
    </div>
</div>
</body>
</html>
