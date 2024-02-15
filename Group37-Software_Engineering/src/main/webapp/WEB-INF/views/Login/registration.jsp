<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
        <title>User Registration</title>
        <link href="static/project.css" rel="stylesheet" type="text/css">
</head>
<body class="registration-background">
<header class="login-small">
        <div class="ibm-logo">
                <img class="ibm" src="https://www.pngall.com/wp-content/uploads/2016/03/IBM-White-Logo-PNG.png" alt="IBM Logo">
                <p>SkillsBuild</p>
        </div>
        <h1>Start learning with IBM.</h1>
</header>
<div class="page">
        <div class="slant">

                <div class="ibm-logo">
                        <div class="ibm-sb">
                                <img class="ibm" src="https://www.pngall.com/wp-content/uploads/2016/03/IBM-White-Logo-PNG.png">
                                <p>SkillsBuild </p> </div>
                        </br> </br> </br> </br> </br> </br>
                        <div class="ibm-text">
                                <p style="font-size: 60px">Start</p>
                                <p style="font-size: 60px">Learning With</p>
                                <p style="font-size: 60px">IBM.</p>
                        </div></div>
        </div>
        <div class="registration">
        <form:form modelAttribute="user" action="/AddUser" method="post">

                <p style="font-family: DM Sans, sans-serif">Register</p>

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
        </form:form>
</div>
</div>
<br/>
<br/>
<br/>
</body>
</html>
