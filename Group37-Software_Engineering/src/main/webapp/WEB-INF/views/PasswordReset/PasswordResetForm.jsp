<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Reset Password</title>
    <script src="static/passwordreset.js" defer></script>
    <%@include file="../includes/head.jsp"%>

</head>
<body>
<h1>Reset Password</h1>
<form action="/reset" method="get" class="w-50 mx-auto needs-validation" novalidate>
    <input type="hidden" name="token" value="${param.token}">
    <div class="form-floating mb-3">
        <input type="password" id="password" name="password" placeholder="password" required class="form-control">
        <label for="password" class="form-label">New Password:</label>
        <div class="invalid-feedback password"></div>
    </div>

    <div class="form-floating mb-3">
        <input type="password" id="confirmPassword" name="confirmPassword" placeholder="confirmPassword" required class="form-control">
        <label for="confirmPassword" class="form-label">Confirm Password:</label>
        <div class="invalid-feedback confirmPassword">
        </div>
    </div>
    <button type="submit" class="btn btn-primary">Reset Password</button>
</form>
</body>

</html>
