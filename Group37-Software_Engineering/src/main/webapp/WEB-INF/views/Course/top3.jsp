<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Top 3 Courses</title>
</head>
<body>
<h1>Top 3 Courses</h1>
<ul>
    <c:forEach items="${top3Courses}" var="course">
        <li>${course.title}</li>
    </c:forEach>
</ul>
</body>
</html>
