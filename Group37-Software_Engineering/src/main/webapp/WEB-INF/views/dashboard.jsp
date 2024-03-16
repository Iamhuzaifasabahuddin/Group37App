<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Dashboard</title>
    <%@include file="includes/head.jsp"%>
</head>
<body>
<%@include file="includes/navbar.jsp"%>
<h1 class="text-center my-3">Welcome to Your Dashboard, ${user.username}!</h1>
<c:if test="${not empty error}">
    <div class="alert alert-info alert-dismissible fade show" role="alert">
        <i class="bi bi-info-circle-fill"></i>
        <strong>${error} <a href="/courses">Find courses here</a></strong>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>
<section>
    <div class="dashboard d-flex flex-column align-items-center p-4">
        <c:forEach items="${userCourses}" var="course">
            <div class="card mb-3" style="max-width: 48rem;">
                <div class="row g-0">
                    <div class="col-sm-4" style="background-color: var(--primary-lighter);">
                        <img src="${course.getCourse().getImageUrl()}" class="img-fluid rounded-start card-img" alt="${course.getCourse().getTitle()}">
                    </div>
                    <div class="col-sm-8">
                        <div class="card-body h-100 d-flex flex-column justify-content-between p-lighter">
                            <div>
                                <h4 class="card-title">${course.getCourse().getTitle()}</h4>
                                <p class="card-subtitle s-light p-darker rounded-pill d-inline px-2 text-uppercase category">${course.getCourse().getCategory()}</p>
                            </div>
                            <div class="d-flex">
                                <p class="card-subtitle text-body-secondary col-5 mb-2 mt-2"><i class="bi bi-hourglass-split"></i> ${Math.round(course.getCourse().getDuration())} hours</p>
                                <c:if test="${not empty course.startTime and not empty course.startDate}">
                                    <p class="card-subtitle text-body-secondary mt-2">Started at ${course.startTime}, ${course.startDate}</p>
                                </c:if>
                            </div>
                            <div class="d-flex">
                                <p class="card-subtitle text-body-secondary col-5 mb-2"><i class="bi bi-award"></i> ${Math.round(course.getCourse().getDuration()) * 100} points</p>
                                <c:if test="${not empty course.endTime and not empty course.endDate}">
                                    <p class="card-subtitle text-body-secondary">Ended at ${course.endTime}, ${course.endDate}</p>
                                </c:if>
                            </div>
                            <c:if test="${not empty course.startTime and not empty course.startDate}">
                                <c:if test="${empty course.endTime and empty course.endDate}">
                                    <div class="d-flex gap-3">
                                        <a class="btn btn-primary container-fluid" href="${course.getCourse().link}" target="_blank">Continue</a>
                                        <a class="btn btn-primary container-fluid" href="/quiz?courseId=${course.getCourse().getId()}">Quiz</a>
                                    </div>
                                </c:if>
                            </c:if>
                            <c:if test="${not empty course.endTime and not empty course.endDate and not course.commented}">
                                <a class="btn btn-primary container-fluid" href="/comment?courseId=${course.getCourse().getId()}"></a>
                            </c:if>
                            <c:if test="${course.commented}">
                                <button class="btn btn-primary container-fluid" disabled>Completed</button>
<%--                                <a class="btn btn-primary container-fluid" href="/comment?courseId=${course.getCourse().getId()}"></a>--%>
                            </c:if>
                            <c:if test="${empty course.startTime and empty course.startDate}">
                                <form id="getStartedForm" class="mb-0" action="/startTime">
                                    <input type="hidden" name="courseId" value="${course.getCourse().id}">
                                    <button class="btn btn-primary container-fluid" type="submit" onclick="openLinkAndSubmit('${course.getCourse().link}')">Get
                                        started
                                    </button>
                                </form>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</section>
</body>
</html>