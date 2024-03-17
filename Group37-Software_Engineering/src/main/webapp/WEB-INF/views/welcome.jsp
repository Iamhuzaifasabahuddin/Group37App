<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>IBM SkillsBuild</title>
    <%@include file="includes/head.jsp"%>
</head>
<body>
<div class="slant"></div>

<div class="d-md-none mb-5">
    <!-- Hamburger Menu Navigation Bar -->
    <nav class="navbar p-darker">
        <div class="container-fluid p-0">
            <div class="mb-0 p-4 top-title">
                <img class="ibm-logo d-inline" src="https://www.pngall.com/wp-content/uploads/2016/03/IBM-White-Logo-PNG.png"
                     alt="IBM Logo">
                <h1 class="d-inline s-light mb-0">Start Learning With IBM</h1>
            </div>
            <button class="navbar-toggler navbar-dark me-4" type="button" data-bs-toggle="collapse" data-bs-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
    </nav>

    <div class="collapse" id="navbarToggleExternalContent" data-bs-theme="dark">
        <div class="p-darker p-4">
            <ul class="navbar-nav d-flex justify-content-end gap-md-3">
                <li class="nav-item">
                    <a class="nav-link s-light" href="/login">Login/Register</a>
                </li>
            </ul>
        </div>
    </div>
</div>

<div class="p-4">

    <div class="d-none d-md-block">
        <!-- Standard Navigation Bar -->
        <nav class="navbar navbar-expand-lg navbar-light p-0">
            <div class="container-fluid p-0">
                    <div class="row mb-5 left-title">
        <div class="col">
            <img class="ibm-logo" src="https://www.pngall.com/wp-content/uploads/2016/03/IBM-White-Logo-PNG.png"
                 alt="IBM Logo">
            <p class="s-light mb-0">SkillsBuild</p>
        </div>
    </div>
                <div class="col text-end">
                    <ul class="navbar-nav d-flex justify-content-end mb-5">
                        <li class="nav-item">
                            <a class="btn btn-outline-dark rounded-0" href="/login">Login/Register</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

    </div>

    <div class="row">
        <div class="col s-light left-title">
            <h1>Start</h1>
            <h1>Learning</h1>
            <h1>With</h1>
            <h1>IBM.</h1>
        </div>
        <div class="col text-center">

            <h3><i class="bi bi-fire" style="color: #FBB746;"></i>
                Trending Courses:</h3>
            <div id="carouselExampleAutoplaying" class="carousel slide mx-auto" data-bs-ride="carousel">
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide-to="1" aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide-to="2" aria-label="Slide 3"></button>
                    <button type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide-to="3" aria-label="Slide 4"></button>
                    <button type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide-to="4" aria-label="Slide 5"></button>
                </div>

                <c:if test="${not empty top5Courses}">
                    <div class="carousel-inner">
                        <c:forEach items="${top5Courses}" var="course" varStatus="status">
                            <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
                                <img src="${course.getImageUrl()}" class="d-block w-100" alt="${course.getTitle()}">
                                <h5>${course.getTitle()}</h5>
                                <p class="category" style="display: none">${course.getCategory()}</p>
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
<%--            <h3>User Testimonials:</h3>--%>
<%--            <div id="carouselExampleAutoplaying-comment" class="carousel slide mx-auto" data-bs-ride="carousel">--%>
<%--                <div class="carousel-indicators">--%>
<%--                    <button type="button" data-bs-target="#carouselExampleAutoplaying-comment" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>--%>
<%--                    <button type="button" data-bs-target="#carouselExampleAutoplaying-comment" data-bs-slide-to="1" aria-label="Slide 2"></button>--%>
<%--                    <button type="button" data-bs-target="#carouselExampleAutoplaying-comment" data-bs-slide-to="2" aria-label="Slide 3"></button>--%>
<%--                    <button type="button" data-bs-target="#carouselExampleAutoplaying-comment" data-bs-slide-to="3" aria-label="Slide 4"></button>--%>
<%--                    <button type="button" data-bs-target="#carouselExampleAutoplaying-comment" data-bs-slide-to="4" aria-label="Slide 5"></button>--%>
<%--                </div>--%>

<%--                <c:if test="${not empty top5Comments}">--%>
<%--                    <div class="carousel-inner">--%>
<%--                        <c:forEach items="${top5Comments}" var="comment" varStatus="status">--%>
<%--                            <div class="carousel-item ${status.index == 0 ? 'active' : ''}">--%>
<%--                                <img src="https://eu.ui-avatars.com/api/?name=${(comment.getUser() .firstname)}+${(comment.getUser().lastname)}&size=200" alt="User Initials Image"  class="d-block w-100"/>--%>
<%--&lt;%&ndash;                                <img src="${comment.getUser().}" class="d-block w-100" alt="User Image">&ndash;%&gt;--%>
<%--                                <h5>${comment.getComment().getDescription()}</h5>--%>
<%--                                <h5>${comment.getComment().getReview()}</h5>--%>
<%--                                <p class="category" style="display: none">${comment.getCourse().getTitle()}</p>--%>
<%--                            </div>--%>
<%--                        </c:forEach>--%>
<%--                    </div>--%>
<%--                </c:if>--%>
<%--                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying-comment" data-bs-slide="prev">--%>
<%--                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>--%>
<%--                    <span class="visually-hidden">Previous</span>--%>
<%--                </button>--%>
<%--                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying-comment" data-bs-slide="next">--%>
<%--                    <span class="carousel-control-next-icon" aria-hidden="true"></span>--%>
<%--                    <span class="visually-hidden">Next</span>--%>
<%--                </button>--%>
<%--            </div>--%>
        </div>
</div>
</body>
</html>