<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin hub</title>
</head>
<body>

<h2>Welcome to Your Dashboard Admin, ${username}!</h2>
<h2>Welcome to your Admin Dashboard, ${firstName}-${lastName}</h2>
<h2>AI:${AI}</h2>
<c:forEach items="${AIU}" var="user">
    <h2>${user.username}</h2>
</c:forEach>

<h2>CLOUD:${Cloud}</h2>
<c:forEach items="${CU}" var="user">
    <h2>${user.username}</h2>
</c:forEach>

<h2>DATA SCIENCE:${DataScience}</h2>
<c:forEach items="${DS}" var="user">
    <h2>${user.username}</h2>
</c:forEach>

<h2>CYBER SECURITY:${CyberSecurity}</h2>
<c:forEach items="${CS}" var="user">
    <h2>${user.username}</h2>
</c:forEach>

<h2>CYBER SECURITY:${Sustainability}</h2>

<c:forEach items="${SU}" var="user">
    <h2>${user.username}</h2>
</c:forEach>


<a href="/logout">Logout</a>

</body>
</html>
