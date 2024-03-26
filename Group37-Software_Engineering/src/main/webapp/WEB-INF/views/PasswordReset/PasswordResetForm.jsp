<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Reset Password</title>
    <script src="static/passwordreset.js" defer></script>
    <%@include file="../includes/head.jsp" %>

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
            <h1 class="text-center mb-3">Reset Password</h1>
            <form action="/reset" method="get" class="mb-2" novalidate>
                <input type="hidden" name="token" value="${param.token}">
                <div class="form-floating mb-3">
                    <input type="password" id="password" name="password" placeholder="password" required
                           class="form-control">
                    <label for="password" class="form-label">New Password:</label>
                    <div class="invalid-feedback password"></div>
                </div>

                <div class="form-floating mb-3">
                    <input type="password" id="confirmPassword" name="confirmPassword" placeholder="confirmPassword"
                           required class="form-control">
                    <label for="confirmPassword" class="form-label">Confirm Password:</label>
                    <div class="invalid-feedback confirmPassword"></div>
                </div>
                <button type="submit" class="btn btn-primary d-block mx-auto">Reset Password</button>
            </form>
        </div>
    </div>
</div>
</body>

</html>
