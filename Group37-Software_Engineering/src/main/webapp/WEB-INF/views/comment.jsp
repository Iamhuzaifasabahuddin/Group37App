<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<form:form method="Post" action="/addComment" modelAttribute="comment">
    <input type="hidden" name="courseId" value="${courseId}">
    <div>
        <label for="description">Description:</label>
        <form:textarea path="description" id="description" />
    </div>
    <div>
        <label for="review">Review:</label>
        <form:input path="review" id="review" />
    </div>
    <div>
        <input type="submit" value="Submit" />
    </div>
</form:form>
</html>