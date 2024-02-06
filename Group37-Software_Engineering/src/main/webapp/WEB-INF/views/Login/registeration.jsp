<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
        <title>User Registration</title>
        <link href="static/project.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="registration">
        <h1>Registration Form:</h1>
        <br/> <br>
        <form:form modelAttribute="user" action="/AddUser" method="post">


                <h2>First Name: </h2>
                <br/>
                <form:input path="firstname" cssClass="form-input"/>
                <form:errors path="firstname" cssClass="error"/>

                <br/>

                <h2>Last Name: </h2>
                <br/>
                <form:input path="lastname" cssClass="form-input"/>
                <form:errors path="lastname" cssClass="error"/>
                <br/>

                <h2>Username:</h2>
                <br/>
                <form:input path="username" cssClass="form-input"/>
                <form:errors path="username" cssClass="error"/>
                <br/>

                <h2>Email:</h2>
                <br/>
                <form:input path="email" pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" cssClass="form-input"/>
                <form:errors path="email" cssClass="error"/>
                <br/>


                <h2>Password:</h2>
                <br/>
                <form:input path="password" type="password" cssClass="form-input"/>
                <form:errors path="password" cssClass="error"/>
                <br/>

                <h2>Confirm Password:</h2>
                <br/>
                <form:input path="confirmpassword" type="password" cssClass="form-input"/>
                <form:errors path="confirmpassword" cssClass="error"/>
                <br/>
                <br/>



            <input type="submit" value="Submit" class="submit-button"/>
        </form:form>
</div>
</body>
</html>
