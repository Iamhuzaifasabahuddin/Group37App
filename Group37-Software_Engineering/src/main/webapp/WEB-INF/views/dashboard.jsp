<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>Dashboard</title>
    <%@include file="includes/head.jsp"%>
    <script type="text/javascript" src="static/dashboard.js" defer></script>
</head>
<body>
<%@include file="includes/navbar.jsp"%>

<div class="container-fluid" style="padding-top: 20px;">
    <div class="row">
        <div class="col-md-9">
<%--            <div class="card mb-3 text-left" style="background-color: var(--primary-lightest); border: 0;">--%>
            <div class="card mb-3 text-left" style="border: 0;">
                <div class="row g-0 align-items-center">
                    <div class="col-md-4 d-flex justify-content-center">
                        <div class="league">
                            <h1>League:</h1>
                            <img src='${user.league.imageUrl}' class='img-fluid' alt='League' style='height: 9em; width: 9em; object-fit: cover;'/>
                            <h3>${user.getLeague().getTitle()}</h3>
                        </div>
                    </div>
                    <div class="col-md-8 ps-2">
                        <h1 class="text-left my-3">Welcome to Your Dashboard, ${user.username}!</h1>
                        <h2 class="text-start my-3"><strong>Rank:</strong> ${Rank}</h2>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <h3><i class="bi bi-fire" style="color: #FBB746;"></i>  Trending Courses:</h3>
            <div id="carouselExampleAutoplaying" class="carousel slide mx-auto" data-bs-ride="carousel">
                <div class="carousel-indicators">
                    <c:forEach items="${top5Courses}" var="course" varStatus="status">
                        <button type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide-to="${status.index}" class="${status.index == 0 ? 'active' : ''}" aria-label="Slide ${status.index + 1}"></button>
                    </c:forEach>
                </div>
                <c:if test="${not empty top5Courses}">
                    <div class="carousel-inner">
                        <c:forEach items="${top5Courses}" var="course" varStatus="status">
                            <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
                                <img src="${course.getImageUrl()}" class="d-block w-100" alt="${course.getTitle()}">
                                <h5>${course.getTitle()}</h5>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
        </div>
    </div>
</div>

<%--<h1 class="text-center my-3">Welcome to Your Dashboard, ${user.username}!</h1>--%>
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
                                <a class="ratings review-course-btn" href="#" id="ratingLink" data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" data-course-id="${course.getCourse().getId()}" data-course-title="${course.getCourse().getTitle()}" aria-controls="offcanvasExample" style="text-decoration-color: var(--secondary-dark)">
                                    <p class="card-subtitle d-inline px-2 text-uppercase fw-bold" style="color: var(--secondary-dark);">
                                        <i class="bi bi-star-fill" style="color: #fcc200"></i>
                                            ${course.getCourse().getAverageRating()}
                                    </p>
                                </a>
                            </div>
                            <div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">
                                <div class="offcanvas-header">
                                    <h5 class="offcanvas-title" id="offcanvasExampleLabel"></h5>
                                    <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                                </div>
                                <div class="offcanvas-body">
                                    <div id="commentsContainer">


                                    </div>

                                </div>
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
                                <a class="btn btn-primary container-fluid" data-bs-toggle="modal" data-bs-target="#exampleModal" data-course-id="${course.getCourse().getId()}" data-course-title="${course.getCourse().getTitle()}" href="#">Review Course</a>
                            </c:if>

                            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered">
                                    <form:form id="modalForm" class="modal-content" action="/addComment" method="GET" novalidate="true">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="exampleModalLabel">Review for:</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div id="stars" class="mb-2">
                                                <i class="star bi bi-star"></i>
                                                <i class="star bi bi-star"></i>
                                                <i class="star bi bi-star"></i>
                                                <i class="star bi bi-star"></i>
                                                <i class="star bi bi-star"></i>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input type="number" id="rating" name="rating" placeholder="Rating" required class="form-control" max="5" step=".5"></input>
                                                <label for="rating" class="form-label"><strong>Rating:</strong></label>
                                                <div class="invalid-feedback rating"></div>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input type="text" id="description" name="description" placeholder="Description" required class="form-control"></input>
                                                <label for="description" class="form-label"><strong>Description:</strong></label>
                                                <div class="invalid-feedback description"></div>
                                            </div>

                                            <input type="hidden" name="courseId" value="${courseId}">
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                            <button type="submit" class="btn btn-primary">Submit</button>
                                        </div>
                                    </form:form>
                                </div>
                            </div>

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
<style>
    .bi-star {
        color: grey;
    }

    .bi-star-fill {
        color: #fcc200;
    }

    .bi-star-half {
        color: gold;
    }

    .star {
        font-size: 1.5em;
    }
</style>
</html>