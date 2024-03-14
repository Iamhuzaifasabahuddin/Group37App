<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Profile</title>
    <%@include file="includes/head.jsp"%>
</head>
<%@include file="includes/navbar.jsp"%>
<body>
<section>
    <div class="container-fluid" style="max-width: 80%;">

        <h1 class="text-center p-4">Profile Details</h1>

        <div class="card mb-3 mx-auto" style="max-width: 540px; background-color: var(--primary-lightest); border: 0;">
            <div class="row g-0">
                <div class="col-md-4">
                    <img src="https://eu.ui-avatars.com/api/?name=${(user.firstname)}+${(user.lastname)}&size=200"
                         alt="User Initials Image" class="rounded-circle" style="height: 7rem; width: 7rem; object-fit: cover; border: 0;"/>
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h2 class="mb-1 fs-5">${user.firstname} ${user.lastname}</h2>
                        <h2 class="mb-1 fs-5">${user.email}</h2>
                        <h2 class="mb-1 fs-5">@${user.username}</h2>
                    </div>
                </div>
            </div>
        </div>

    </div>
</section>

<section>
    <div class="container-fluid">
        <div class="card mb-3 mx-auto" style="max-width: 35rem;">
            <div class="row g-0">
                <div class="col d-flex align-items-center justify-content-center">
                    <h5 class="card-title fs-1 mt-2 fw-bolder">Statistics</h5>
                </div>
                <div class="col">
                    <div class="card-body">
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item fs-6 border-dark">
                                <i class="bi bi-backpack3-fill"></i>
                                <strong>Courses Taken:</strong>
                                ${Courses_taken}</li>
                            <li class="list-group-item fs-6 border-dark">
                                <i class="bi bi-check2-circle"></i>
                                <strong>Courses Completed:</strong>
                                ${Completed}</li>
                            <li class="list-group-item fs-6 border-dark">
                                <i class="bi bi-percent"></i>
                                <strong>Percent Completed:</strong>
                                ${Percentage}</li>
                            <li class="list-group-item fs-6 border-dark">
                                <i class="bi bi-hourglass-split"></i>
                                <strong>Hours Left:</strong>
                                ${Hours}</li>
                            <li class="list-group-item fs-6 border-dark">
                                <i class="bi bi-bar-chart-fill"></i>
                                <strong>Rank:</strong>
                                ${Rank}</li>
                            <li class="list-group-item fs-6 border-dark">
                                <i class="bi bi-award-fill"></i>
                                <strong>Points:</strong>
                                ${user.points}</li>
                            <li class="list-group-item fs-6 border-dark">
                                <i class="bi bi-trophy-fill"></i>
                                <strong>League:</strong>
                                <div class="league">
                                    <a data-toggle="tooltip" title="<img src='${user.league.imageUrl}' class='img-fluid' alt='League' style='height: 9em; width: 9em; object-fit: cover;'/>">${user.getLeague().getTitle()}</a>
                                </div>
<%--                                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#league">--%>
<%--                                    ${user.getLeague().getTitle()}--%>
<%--                                </button>--%>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<%--<div class="modal" id="league">--%>
<%--    <div class="modal-dialog modal-dialog-centered">--%>
<%--    <div class="modal-content" style="background-color: white">--%>
<%--    <div class="modal-header mx-auto">--%>
<%--        <h4 class="modal-title">${user.getLeague().getTitle()}</h4>--%>
<%--    </div>--%>
<%--    <div class="modal-body mx-auto">--%>
<%--        <img src="${user.league.imageUrl}" class="img-fluid" alt="League" style="height: 9em; width: 9em; object-fit: cover;">--%>
<%--    </div>--%>
<%--    </div>--%>
<%--    </div>--%>
<%--</div>--%>

<section>
    <div class="container-fluid">
        <div class="row justify-content-center align-items-center">
            <div class="col-lg-10">
                <h1 class="mt-3 text-center">Achievements</h1>
                <div class="row">
                    <c:forEach items="${Achieved}" var="achieved">
                        <div class="col-lg-4 col-md-6 mb-3">
                            <div class="card custom-card border-success" style="background: whitesmoke;">
                                <div class="card-body" style="height: 400px">
                                    <h4 class="card-title"><strong>${achieved.getAchievement().title}</strong></h4>
                                    <div class="d-flex justify-content-center align-items-center" style="height: 60%;">
                                        <img src="${achieved.getAchievement().getImageUrl()}" alt="${achieved.getAchievement().title}" class="img-fluid" style="max-width: 100%; max-height: 100%;" width="200px" height="200px">
                                    </div>
                                    <div class="d-flex justify-content-between p-2">
                                        <div>
                                            <p class="card-text mt-2">${achieved.getAchievement().description}</p>
                                        </div>
                                        <div class="mt-2">
                                            <p><i class="bi bi-award"></i> ${achieved.getAchievement().points}</p>
                                        </div>
                                    </div>
                                    <div class="card-footer border-dark" style="color: black">
                                            <h6><i class="bi bi-unlock"></i> Achieved at ${achieved.timeAchieved}, ${achieved.dateAchieved}</h6>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</section>

<div class="text-center">
<button class="btn btn-primary m-2 col-4 mx-auto" type="button" data-bs-toggle="collapse" data-bs-target="#locked-achievements" aria-expanded="false" aria-controls="collapseExample">
    View more <br/>
    <i class="bi bi-plus-circle-fill"></i>
</button>
</div>

<div id="locked-achievements" class="collapse">
    <div class="container-fluid">
        <div class="row justify-content-center align-items-center">
            <div class="col-lg-10">
                <h1 class="mt-3 text-center">Locked Achievements</h1>
                <div class="row">
                    <c:forEach items="${notachieved}" var="notachieved">
                        <div class="col-lg-4 col-md-6 mb-3">
                            <div class="card custom-card border-danger" style="background: lightgray;">
                                <div class="card-body" style="height: 400px;">
                                    <h4 class="card-title"><strong>${notachieved.title}</strong></h4>
                                    <div class="d-flex justify-content-center align-items-center" style="height: 60%;">
                                        <img src="https://images.vexels.com/media/users/3/132074/isolated/preview/0117cb0129593faa02646a8277ca80e3-security-lock-icon-by-vexels.png" alt="Locked" class="img-fluid" style="height: 100%; max-width: 100%; object-fit: cover;">
<%--                                        <img src="${notachieved.imageUrl}" alt="Locked" class="img-fluid" style="height: 100%; max-width: 100%; object-fit: cover;">--%>
                                    </div>
                                    <div class="d-flex justify-content-between p-2">
                                        <div>
                                            <p class="card-text mt-2">${notachieved.description}</p>
                                        </div>
                                        <div class="mt-2">
                                            <p><i class="bi bi-award"></i> ${notachieved.points}</p>
                                        </div>
                                    </div>
                                    <div class="card-footer border-dark">
                                        <h6 class="mt-2"><i class="bi bi-lock"></i> Locked</h6>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>


<section>
<div class="card mx-auto m-2" style="width: 24rem;">
    <div class="card-header text-center">
        Quote of the day <i class="bi bi-chat-quote"></i>
    </div>
    <div class="card-body">
        <blockquote class="blockquote mb-0 text-center">
            <p>${Quote}</p>
        </blockquote>
    </div>
</div>
</section>

</body>
</html>