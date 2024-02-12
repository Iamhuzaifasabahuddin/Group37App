<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form action="/completeQuiz">
    <c:forEach items="${questions}" var="question">
        <p>${question.getPrompt()}</p>
            <c:forEach items="${question.getOptions()}" var="option">
                <input type="radio" name="${question.getId()}" value="${option}">${option}
            </c:forEach>
    </c:forEach>
    <input type="submit" value="Submit">
</form:form>
${score}
