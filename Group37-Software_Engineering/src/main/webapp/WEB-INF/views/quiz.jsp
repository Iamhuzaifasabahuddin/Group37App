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
    <script src="static/script.js" defer></script>
</head>
<body>
<br>
<h1 style="text-align: center">Quiz for ${name}</h1>
<br>
<div class="quiz-container">
    <form:form action="/completeQuiz" modelAttribute="quiz" id="quizForm">
        <form:hidden path="id" value="${courseQuiz.getId()}"></form:hidden>
        <ol><c:forEach var="question" items="${courseQuiz.getQuestions()}" varStatus="questionIndex">
            <li class="quiz-question">${question.getPrompt()}</li>
            <c:forEach var="option" items="${question.getOptions()}">
                <div class="quiz-option">
                    <form:radiobutton class="buttons-option" path="questions[${questionIndex.index}].answer"
                                      value="${option}" id="${questionIndex.index}${option}"/>
                    <label for="${questionIndex.index}${option}">${option}</label>
                </div>
            </c:forEach>

            <br>
        </c:forEach>
        </ol>
        <input type="hidden" name="courseId" value="${courseId}">
        <form:errors cssClass="error" path="questions"></form:errors>
        <input class="quiz-button" type="submit" value="Submit">
    </form:form>
    <br>
    <c:if test="${not empty score}">
        <div class="quiz-popup-background">
            <div class="quiz-popup-box">
                <h4>Quiz Score</h4>
                <div class="quiz-score" role="progressbar">
                    <p>${Math.round(score)}%</p>
                </div>
                <p class="quiz-result">Congratulations, you pass this course!</p>
                <a class="return-dashboard" href="/dashboard">Return to Dashboard</a>
            </div>
        </div>
    </c:if>
</div>
</body>
</html>
