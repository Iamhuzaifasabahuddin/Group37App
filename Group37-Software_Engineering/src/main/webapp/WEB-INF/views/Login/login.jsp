<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <script src="static/modal.js" defer></script>
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
                    <div class="alert alert-danger alert-dismissible fade show mb-3" role="alert">
                        <strong>${error}</strong>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>
                <c:if test="${not empty Message}">
                    <div class="alert alert-success alert-dismissible fade show mb-3" role="alert">
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        <strong>${Message}</strong>
                    </div>
                </c:if>

                <c:if test="${not empty EmailError}">
                    <div class="alert alert-danger alert-dismissible fade show mb-3" role="alert">
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        <strong>${EmailError}</strong>
                    </div>
                </c:if>
                <div class="form-check mb-3">
                    <input class="form-check-input" type="checkbox" name="remember-me" id="remember-me">
                    <label class="form-check-label" for="remember-me">
                        Remember Me
                    </label>
                </div>
                <button type="submit" class="btn btn-primary">
                    <i class="bi bi-box-arrow-in-right"></i>
                    Log In
                </button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            </form>
            <p>New user? <a href="${pageContext.request.contextPath}/NewUser">Register Here</a></p>
            <p>Forgot your password? <a href="#" data-bs-toggle="modal" data-bs-target="#exampleModal">Reset it here</a>.</p>

        </div>
    </div>
</div>

<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <form id="modalForm" class="modal-content" action="/request" method="GET" novalidate>
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Enter your email:</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="form-floating mb-3">
                    <input type="email" id="resetEmail" name="email" placeholder="Email" required class="form-control">
                    <label for="resetEmail" class="form-label">Email:</label>
                    <div class="invalid-feedback resetEmail"></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>
