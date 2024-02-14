<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
        <title>User Registration</title>
        <link href="static/project.css" rel="stylesheet" type="text/css">
</head>
<body class="registration-background">
        <div class="registration">
                <img class="logo2"  src ="https://img1.wsimg.com/isteam/ip/6f5993eb-07fd-4e54-a10f-d1d3850f5f51/ibmskillsbuild-gmen.png/:/" alt="IBM logo">
                <h1 style="font-family: DM Sans, sans-serif">Registration Form</h1>
        <br/> <br>
        <form:form modelAttribute="user" action="/AddUser" method="post">


                <h2 style="font-family: DM Sans, sans-serif">First Name: </h2>
                <form:input path="firstname" cssClass="form-input"/>
                <form:errors path="firstname" cssClass="error"/>

                <br/>

                <h2 style="font-family: DM Sans, sans-serif">Last Name: </h2>
                <form:input path="lastname" cssClass="form-input"/>
                <form:errors path="lastname" cssClass="error"/>
                <br/>

                <h2 style="font-family: DM Sans, sans-serif">Username:</h2>
                <form:input path="username" cssClass="form-input"/>
                <form:errors path="username" cssClass="error"/>
                <br/>

                <h2 style="font-family: DM Sans, sans-serif">Email:</h2>
                <form:input path="email" cssClass="form-input"/>
                <form:errors path="email" cssClass="error"/>
                <br/>


                <h2 style="font-family: DM Sans, sans-serif">Password:</h2>
                <form:input path="password" type="password" cssClass="form-input"/>
                <form:errors path="password" cssClass="error"/>
                <br/>

                <h2 style="font-family: DM Sans, sans-serif">Confirm Password:</h2>
                <form:input path="confirmpassword" type="password" cssClass="form-input"/>
                <form:errors path="confirmpassword" cssClass="error"/>
                <br/>
                <br/>



            <input type="submit" value="Submit" class="submit-button" style="border-radius: 18px"/>
                <p style="text-align: center">Already have an account? <a style="font-size: large" href="${pageContext.request.contextPath}/login-form" class="link-style">Login here</a>.</p>
        </form:form>
</div>
<br/>
<br/>
<br/>
</body>
</html>
