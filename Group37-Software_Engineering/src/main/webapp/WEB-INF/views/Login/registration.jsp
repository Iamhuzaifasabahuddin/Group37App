<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>User Registration</title>--%>
<%--    <link href="static/project.css" rel="stylesheet" type="text/css">--%>
<%--</head>--%>
<%--<body class="registration-background">--%>
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

<%--    <div class="registration">--%>
<%--        <form:form modelAttribute="user" action="/AddUser" method="post">--%>


<%--            <p style="font-family: DM Sans, sans-serif">Register</p>--%>

<%--            <h2 style="font-family: DM Sans, sans-serif">First Name: </h2>--%>
<%--            <form:input path="firstname" cssClass="form-input"/>--%>
<%--            <form:errors path="firstname" cssClass="error"/>--%>

<%--            <h2 style="font-family: DM Sans, sans-serif">Last Name: </h2>--%>
<%--            <form:input path="lastname" cssClass="form-input"/>--%>
<%--            <form:errors path="lastname" cssClass="error"/>--%>

<%--            <h2 style="font-family: DM Sans, sans-serif">Username:</h2>--%>
<%--            <form:input path="username" cssClass="form-input"/>--%>
<%--            <form:errors path="username" cssClass="error"/>--%>

<%--            <h2 style="font-family: DM Sans, sans-serif">Email:</h2>--%>
<%--            <form:input path="email" cssClass="form-input"/>--%>
<%--            <form:errors path="email" cssClass="error"/>--%>

<%--            <h2 style="font-family: DM Sans, sans-serif">Password:</h2>--%>
<%--            <form:input path="password" type="password" cssClass="form-input"/>--%>
<%--            <form:errors path="password" cssClass="error"/>--%>

<%--            <h2 style="font-family: DM Sans, sans-serif">Confirm Password:</h2>--%>

<%--            <form:input path="confirmPassword" type="password" cssClass="form-input"/>--%>
<%--            <form:errors path="confirmPassword" cssClass="error"/>--%>


<%--            </br>--%>

<%--            <input type="submit" value="Submit" class="submit-button"/>--%>
<%--            <p class="new" style="font-weight: normal; font-size: 18px">Already have an account? <a--%>
<%--                    style="font-size: large" href="${pageContext.request.contextPath}/login-form" class="link-style">Login--%>
<%--                here</a>.</p>--%>

<%--        </form:form>--%>
<%--    </div>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Registration</title>
    <script src="static/registration.js" defer></script>
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
            <h1>Create a new account</h1>
            <form:form modelAttribute="user" action="/AddUser" method="post" novalidate="true" class="mb-2">
                <div class="row">
                    <div class="col-sm">
                        <div class="form-floating mb-3">
                            <form:input class="form-control" path="firstname" placeholder="First name" required="true"/>
                            <form:label path="firstname" class="form-label">First name</form:label>
                            <form:errors path="firstname"/>
                            <div class="invalid-feedback firstname"></div>
                        </div>
                    </div>
                    <div class="col-sm">
                        <div class="form-floating mb-3">
                            <form:input class="form-control" path="lastname" placeholder="Last name" required="true"/>
                            <form:label path="lastname" class="form-label">Last name</form:label>
                            <form:errors path="lastname"/>
                            <div class="invalid-feedback lastname"></div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg">
                        <div class="input-group mb-3 has-validation">
                            <span class="input-group-text">@</span>
                            <div class="form-floating">
                                <form:input class="form-control" path="username" placeholder="Username" required="true"/>
                                <form:label path="username" class="form-label">Username</form:label>
                                <form:errors path="username"/>
                            </div>
                            <div class="invalid-feedback username"></div>
                        </div>
                    </div>
                    <div class="col-lg">
                        <div class="form-floating mb-3">
                            <form:input class="form-control" path="email" placeholder="Email" type="email" required="true"/>
                            <form:label path="email" class="form-label">Email</form:label>
                            <form:errors path="email"/>
                            <div class="invalid-feedback email"></div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm">
                        <div class="form-floating mb-3">
                            <form:input class="form-control" path="password" placeholder="Password" type="password" required="true"/>
                            <form:label path="password" class="form-label">Password</form:label>
                            <form:errors path="password"/>
                            <div class="invalid-feedback password"></div>
                        </div>
                    </div>
                    <div class="col-sm">
                        <div class="form-floating mb-3">
                            <form:input class="form-control" path="confirmPassword" placeholder="Confirm password" type="password" required="true"/>
                            <form:label path="confirmPassword" class="form-label">Confirm password</form:label>
                            <form:errors path="confirmPassword"/>
                            <div class="invalid-feedback confirmPassword"></div>
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">
                    <i class="bi bi-box-arrow-in-right"></i>
                    Register
                </button>
            </form:form>
            <p>Already have an account? <a href="${pageContext.request.contextPath}/login">Log In Here</a></p>
        </div>
    </div>
</div>
</body>
</html>
