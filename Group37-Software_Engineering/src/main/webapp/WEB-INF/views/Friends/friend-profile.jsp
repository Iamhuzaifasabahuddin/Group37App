<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Profile</title>
    <%@include file="../includes/head.jsp" %>
</head>
<%@include file="../includes/navbar.jsp" %>
<body>
<div class="p-2">
    <button id="backButton" type="button" class="btn btn-primary mb-3">
        <i class="bi bi-arrow-return-left"></i>
        Friends
    </button>
</div>


<section>
    <div class="container-fluid" style="max-width: 80%;">
        <h1 class="text-center p-4">Profile Details</h1>
        <div class="d-flex justify-content-center">
            <div class="card mb-3 text-center"
                 style="max-width: 600px; background-color: var(--primary-lightest); border: 0;">
                <div class="row g-0 align-items-center">
                    <div class="col-md-4 d-flex justify-content-center">
                        <img src="https://eu.ui-avatars.com/api/?name=${(friend.firstname)}+${(friend.lastname)}&size=200"
                             alt="User Initials Image" class="rounded-circle"
                             style="height: 8.5rem; width: 8.5rem; object-fit: cover; border: 2px solid var(--secondary-dark);"/>
                    </div>
                    <div class="col-md-8 ps-2">
                        <div class="card-body card-details">
                            <p class="mb-1 fs-5"><strong>Full Name: </strong>${friend.firstname} ${friend.lastname}</p>
                            <p class="mb-1 fs-5"><strong>Email: </strong>${friend.email}</p>
                            <p class="mb-1 fs-5"><strong>Username: </strong>@${friend.username}</p>
                            <hr>
                            <div class="d-flex justify-content-between">
                                <p class="mb-1 fs-6"><strong>Joined: </strong> ${DateJoined} </p>
                                <%--                                <p class="mb-1 fs-6"><strong>Friends: </strong>${friends}</p>--%>
                                <p class="mb-1 fs-6"><strong>Friends Since: </strong>${since} </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section>
    <div class="container-fluid">
        <div class="card mb-3 mx-auto border-dark" style="max-width: 45rem; background-color: whitesmoke;">
            <div class="row g-0">
                <div class="col d-flex align-items-center justify-content-center">
                    <h5 class="card-title fs-1 mt-2 fw-bolder text-center" style="width: 100%;">
                        ${friend.username}'s
                        Statistics
                    </h5>
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
                                <i class="bi bi-bar-chart-fill"></i>
                                <strong>Rank:</strong>
                                ${Rank}</li>
                            <li class="list-group-item fs-6 border-dark">
                                <i class="bi bi-award-fill"></i>
                                <strong>Points:</strong>
                                ${friend.points}</li>
                            <li class="list-group-item fs-6 border-dark">
                                <i class="bi bi-controller"></i>
                                <strong>Achievements:</strong>
                                ${Achievements} / ${TotalAchievements}</li>
                            <li class="list-group-item fs-6 border-dark">
                                <i class="bi bi-trophy-fill"></i>
                                <strong>League:</strong>
                                <div class="league">
                                    <a data-toggle="tooltip"
                                       title="<img src='${friend.league.imageUrl}' class='img-fluid' alt='League' style='height: 9em; width: 9em; object-fit: cover;'/>">${friend.getLeague().getTitle()}</a>
                                </div>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="text-center mt-3">
            <button id="compareButton" type="button" class="btn btn-info">Compare Stats</button>
        </div>
        <div id="comparisonStats" class="row" style="display:none;"></div>
    </div>
</section>

<section>
    <div class="container-fluid-LoggedInUser d-none mt-3">
        <div class="card mb-3 mx-auto border-dark" style="max-width: 45rem; background-color: whitesmoke;">
            <div class="row g-0">
                <div class="col d-flex align-items-center justify-content-center">
                    <h5 class="card-title fs-1 mt-2 fw-bolder">My Stats</h5>
                </div>
                <div class="col">
                    <div class="card-body">
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item fs-6 border-dark">
                                <i class="bi bi-backpack3-fill"></i>
                                <strong>Courses Taken:</strong>
                                ${LoggedInUser_Courses_taken}</li>
                            <li class="list-group-item fs-6 border-dark">
                                <i class="bi bi-check2-circle"></i>
                                <strong>Courses Completed:</strong>
                                ${LoggedInUser_Completed}</li>
                            <li class="list-group-item fs-6 border-dark">
                                <i class="bi bi-percent"></i>
                                <strong>Percent Completed:</strong>
                                ${LoggedInUser_Percentage}</li>
                            <li class="list-group-item fs-6 border-dark">
                                <i class="bi bi-bar-chart-fill"></i>
                                <strong>Rank:</strong>
                                ${LoggedInUser_Rank}</li>
                            <li class="list-group-item fs-6 border-dark">
                                <i class="bi bi-award-fill"></i>
                                <strong>Points:</strong>
                                ${LoggedInUser_Points}</li>
                            <li class="list-group-item fs-6 border-dark">
                                <i class="bi bi-trophy-fill"></i>
                                <strong>League:</strong>
                                <div class="league">
                                    <a data-toggle="tooltip"
                                       title="<img src='${LoggedInUser_League.getImageUrl()}' class='img-fluid' alt='League' style='height: 9em; width: 9em; object-fit: cover;'/>">${LoggedInUser_League.getTitle()}</a>
                                </div>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

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
                                        <img src="${achieved.getAchievement().getImageUrl()}"
                                             alt="${achieved.getAchievement().title}" class="img-fluid"
                                             style="max-width: 100%; max-height: 100%;" width="200px" height="200px">
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
                                        <h6><i class="bi bi-unlock"></i> Achieved
                                            at ${achieved.timeAchieved}, ${achieved.dateAchieved}</h6>
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

<script>
    document.addEventListener('DOMContentLoaded', function () {
        console.log("DOM fully loaded and parsed");

        document.getElementById('backButton').addEventListener('click', function () {
            window.location.href = "${pageContext.request.contextPath}/friends";
        });

        var compareButton = document.getElementById('compareButton');
        compareButton.addEventListener('click', function () {
            var statsDiv = document.querySelector('.container-fluid-LoggedInUser');
            if (statsDiv.classList.contains('d-none')) {
                statsDiv.classList.remove('d-none');
                compareButton.textContent = 'Back';
                console.log("Stats shown");
            } else {
                statsDiv.classList.add('d-none');
                compareButton.textContent = 'Compare Stats';
                console.log("Stats hidden");
            }
        });
    });
</script>

<style>
    .card-details {
        text-align: left;
    }

    @media (max-width: 768px) {
        .card-body {
            padding-left: 0 !important;
        }

        .card-details {
            text-align: center;
        }
    }
</style>
</body>
</html>

