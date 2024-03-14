<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Courses</title>
    <%@include file="../includes/head.jsp"%>
    <script type="text/javascript" src="static/Courses.js" defer></script>
</head>
<body>
<%@include file="../includes/navbar.jsp"%>

<div class="p-4">
    <h1 class="text-center my-3">Welcome, ${user.username}!</h1>
    <div class="row mb-3">
        <form id="filterForm" action="/filter" method="get" class="col-md d-flex gap-2 mb-3">
            <select id="category" name="category" class="form-select" aria-label="Select category">
                <option selected disabled value="Select Category">Select Category</option>
                <option value="Artificial Intelligence">AI</option>
                <option value="Cloud">Cloud</option>
                <option value="Data Science">Data Science</option>
                <option value="CyberSecurity">CyberSecurity</option>
            </select>
            <button type="submit" class="btn btn-primary col-3">Filter</button>
        </form>

        <form id="searchForm" action="/search" method="get" class="col-md d-flex gap-2 mb-3">
            <input type="text" class="form-control" name="searchTerm" id="searchTerm" placeholder="Search Course">
            <button type="submit" class="btn btn-primary col-3">Search</button>
        </form>

        <form id="durationForm" action="/duration" method="get" class="col-md d-flex gap-2 mb-3">
            <select id="duration" name="duration" class="form-select" aria-label="Select category">
                <option selected disabled>Select Duration</option>
                <option value="5.0">5+ hours</option>
                <option value="10.0">10+ hours</option>
                <option value="20.0">20 hours</option>
            </select>
            <button type="submit" class="btn btn-primary col-3">Filter</button>
        </form>
    </div>

    <div class="alert alert-danger alert-dismissible fade show custom-alert" role="alert">
        <strong><i class="bi bi-exclamation-triangle-fill"></i>
        </strong>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>

    <h2 class="list_courses">Available courses: </h2>
    <c:if test="${not empty error}">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            <strong><i class="bi bi-exclamation-triangle-fill"></i>
                    ${error}
            </strong>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </c:if>
    <section>
        <div class="course-message-container">
            <c:if test="${not empty CourseError}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <strong><i class="bi bi-exclamation-triangle-fill"></i>
                            ${CourseError}
                    </strong>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>
        </div>
        <div class="courses">
            <c:forEach items="${courseList}" var="course">
                <div class="card p-lighter">
                    <img src="${course.getImageUrl()}" class="card-img-top" alt="${course.getTitle()}">
                    <div class="card-body">
                        <h4 class="card-title pb-1">${course.getTitle()}</h4>
                        <p class="card-subtitle s-light p-darker rounded-pill d-inline px-2 text-uppercase category">${course.getCategory()}</p>
                        <div class="mt-3 d-flex gap-3">
                            <p class="card-subtitle text-body-secondary"><i class="bi bi-hourglass-split"></i> ${Math.round(course.getDuration())} hours</p>
                            <p class="card-subtitle text-body-secondary"><i class="bi bi-award"></i> ${Math.round(course.getDuration()) * 100} points</p>
                        </div>
                    </div>
                    <button class="btn btn-primary rounded-0 rounded-bottom" type="button" data-bs-toggle="modal" data-bs-target="#staticBackdrop" onclick="showConfirmationBox(${course.id}, `${course.getTitle()}`)">
                        Enroll
                    </button>
                </div>
            </c:forEach>
        </div>
    </section>
    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="staticBackdropLabel">Confirm Enrolment</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body"></div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <form action="${pageContext.request.contextPath}/enroll">
                        <input class="input-course-id" type="hidden" name="course" value="">
                        <button class="btn btn-primary">Confirm</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>