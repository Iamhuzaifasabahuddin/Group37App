<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Reset Password</title>
</head>
<body>
<h1>Reset Password</h1>
<form action="/reset" method="get">
    <input type="hidden" name="token" value="${param.token}">
    <label for="password">New Password:</label>
    <input type="password" id="password" name="password" required minlength="8">
    <button type="submit">Reset Password</button>
</form>
</body>
</html>
