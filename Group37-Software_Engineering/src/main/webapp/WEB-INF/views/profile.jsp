<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
    <h3>First name: ${user.firstname}</h3>
    <h3>Last name: ${user.lastname}</h3>
    <h3>Email: ${user.email}</h3>
    <h3>Username: ${user.username}</h3>
    <h3>Courses Taken: ${Courses_taken}</h3>
    <h3>Courses Completed: ${Completed}</h3>
    <h3>Percentage Completed: ${Percentage} %</h3>
    <h3>Hours Left: ${Hours}</h3>
</html>
