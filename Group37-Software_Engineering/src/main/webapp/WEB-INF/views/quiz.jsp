<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="static/project.css" rel="stylesheet" type="text/css">
    <title>Quiz</title>
</head>
<body>
</br>
<h1 style="text-align: center">Quiz for ${name}</h1>
</br>
<div class="quiz-container">
    <form:form action="/completeQuiz" modelAttribute="quiz" id="quizForm">
        <form:hidden path="id" value="${courseQuiz.getId()}"></form:hidden>
        <c:forEach var="question" items="${courseQuiz.getQuestions()}" varStatus="questionIndex">
            <p class="quiz-question">${question.getPrompt()}</p>
            <form:radiobuttons path="questions[${questionIndex.index}].answer" items="${question.getOptions()}"></form:radiobuttons>
            <br>
        </c:forEach>
        <input style="border-radius: 20px" class="quiz-button" type="submit" value="Submit">
    </form:form>
    <br>

        ${score}

</div>
</body>
</html>
