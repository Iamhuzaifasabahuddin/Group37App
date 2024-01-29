<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Registration</title>
    <link href="static/Css/Project.css" rel="stylesheet" type="text/css">
</head>
<body>

<h2>New User:</h2>

<form:form modelAttribute="user" action="/AddUser" method="post">
        <h2>Username:</h2>
        <form:input path="username"/>
        <form:errors path="username" cssClass="error"/>
        <br/>

        <h2>Email:</h2>
        <form:input path="email" pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$"/>
        <form:errors path="email" cssClass="error"/>
        <br/>

        <h2>Password:</h2>
        <form:input path="password" type="password" />
        <form:errors path="password" cssClass="error"/>
        <br/>
        <br/>

    <input type="submit" value="Submit" class="submit-button"/>
</form:form>

</body>
</html>
