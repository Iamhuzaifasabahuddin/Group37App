<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Quiz</title>
    <%@include file="includes/head.jsp" %>
</head>
<body>
<br>
<h1 style="text-align: center">Quiz for ${name}</h1>
<br>
<div class="quiz-container">
    <form:form action="/completeQuiz" modelAttribute="quiz" id="quizForm">
        <form:errors cssClass="error" path="questions">
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                <i class="bi bi-exclamation-triangle-fill"></i>
                Please answer all the questions.
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </form:errors>
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
        <input class="btn btn-primary d-block" type="submit" value="Submit" data-bs-toggle="modal"
               data-bs-target="#staticBackdrop">
    </form:form>
    <br>
    <c:if test="${not empty score}">
        <div class="quiz-popup-background"></div>
        <div class="modal quiz-popup-background" tabindex="-1">
            <div class="modal-dialog quiz-popup-box">
                <div class="modal-content">
                    <div class="modal-header d-flex justify-content-center">
                        <h5 class="modal-title">Quiz Score</h5>
                    </div>
                    <div class="modal-body d-flex justify-content-center align-items-center flex-column">
                        <div class="quiz-score d-flex justify-content-center" role="progressbar">
                            <p>${Math.round(score)}%</p>
                        </div>
                        <p class="quiz-result">Congratulations, you pass this course!</p>
                    </div>
                    <div class="modal-footer">
                        <a class="return-dashboard" href="/dashboard">Return to Dashboard</a>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</div>
</body>
</html>
