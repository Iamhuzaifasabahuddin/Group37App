<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Profile</title>
    <%@include file="includes/head.jsp"%>
</head>
<%@include file="includes/navbar.jsp"%>
<body>
<section>
    <div class="container-fluid">
        <div class="row">
            <h1 class="text-center p-4">Welcome to your profile page, ${user.username}!</h1>
            <div class="col-md-12 text-center">
<%--                <h1>Profile</h1>--%>
                <img src="https://eu.ui-avatars.com/api/?name=${(user.firstname)}+${(user.lastname)}&size=200"
                     alt="User Initials Image" class="img-thumbnail mb-3"/>
                <h2 class="mb-3">${user.firstname} ${user.lastname}</h2>
                <h2 class="mb-3">${user.email}</h2>
                <h2 class="mb-3">@${user.username}</h2>
            </div>
        </div>
    </div>
</section>

<section>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12 text-center">
                <h1 class="mt-3">Stats</h1>
                <div class="row">
                    <div class="col-md-4 mb-3">
                        <div class="card custom-card border-dark" style="background: whitesmoke;">
                            <div class="card-body">
                                <h4 class="card-title"><strong>Courses Taken:</strong></h4>
                                <h5 class="card-text">${Courses_taken}</h5>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <div class="card custom-card border-dark"  style="background: whitesmoke;">
                            <div class="card-body">
                                <h4 class="card-title"><strong>Courses Completed:</strong></h4>
                                <h5 class="card-text">${Completed}</h5>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <div class="card custom-card border-dark" style="background: whitesmoke;">
                            <div class="card-body" >
                                <h4 class="card-title"><strong>Percentage Completed:</strong></h4>
                                <h5 class="card-text">${Percentage} %</h5>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <div class="card custom-card border-dark" style="background: whitesmoke;">
                            <div class="card-body">
                                <h4 class="card-title"><strong>Hours Left:</strong></h4>
                                <h5 class="card-text">${Hours}</h5>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <div class="card custom-card border-dark" style="background: whitesmoke;">
                            <div class="card-body">
                                <h4 class="card-title"><strong>Rank:</strong></h4>
                                <h5 class="card-text">${Rank}</h5>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <div class="card custom-card border-dark" style="background: whitesmoke;">
                            <div class="card-body">
                                <h4 class="card-title"><strong>Points:</strong></h4>
                                <h5 class="card-text">${user.points}</h5>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <div class="card custom-card border-dark" style="background: whitesmoke;">
                            <div class="card-body">
                                <h4 class="card-title"><strong>League:</strong></h4>
                                <img src="${league}" class="card-img-top" alt="League" style="height: 200px; width: 200px; object-fit: cover;">
                                <h5 class="card-text">${user.league.title}</h5>
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
        <div class="row justify-content-center align-items-center">
            <div class="col-lg-10">
                <h1 class="mt-3 text-center">Achievements</h1>
                <div class="row">
                    <c:forEach items="${Achieved}" var="achieved">
                        <div class="col-lg-4 col-md-6 mb-3">
                            <div class="card custom-card border-success" style="background: whitesmoke;">
                                <div class="card-body" style="height: 350px">
                                    <h4 class="card-title"><strong>${achieved.getAchievement().title}</strong></h4>
                                    <div class="d-flex justify-content-center align-items-center" style="height: 60%;">
                                        <img src="${achieved.getAchievement().getImageUrl()}" alt="${achieved.getAchievement().title}" class="img-fluid" style="max-width: 100%; max-height: 100%;" width="200px" height="200px">
                                    </div>
                                    <div class="d-flex justify-content-between">
                                        <div>
                                            <p class="card-text mt-2">${achieved.getAchievement().description}</p>
                                        </div>
                                        <div class="mt-2">
                                            <p><i class="bi bi-award"></i> ${achieved.getAchievement().points}</p>
                                        </div>
                                    </div>
                                    <div class="card-footer border-dark" style="color: black">
                                        <div class="mt-2">
                                            <h6><i class="bi bi-unlock"></i> Achieved at ${achieved.timeAchieved}, ${achieved.dateAchieved}</h6>
                                        </div>
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

<section>
    <div class="container-fluid">
        <div class="row justify-content-center align-items-center">
            <div class="col-lg-10">
                <h1 class="mt-3 text-center">Locked Achievements</h1>
                <div class="row">
                    <c:forEach items="${notachieved}" var="notachieved">
                        <div class="col-lg-4 col-md-6 mb-3">
                            <div class="card custom-card border-danger" style="background: lightgray;">
                                <div class="card-body" style="height: 350px;">
                                    <h4 class="card-title"><strong>${notachieved.title}</strong></h4>
                                    <div class="d-flex justify-content-center align-items-center" style="height: 60%;">
                                        <img src="https://images.vexels.com/media/users/3/132074/isolated/preview/0117cb0129593faa02646a8277ca80e3-security-lock-icon-by-vexels.png" alt="Locked" class="img-fluid" style="height: 100%; max-width: 100%; object-fit: cover;">
<%--                                        <img src="${notachieved.imageUrl}" alt="Locked" class="img-fluid" style="height: 100%; max-width: 100%; object-fit: cover;">--%>
                                    </div>
                                    <div class="d-flex justify-content-between">
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
</section>




<section>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12 text-center mb-3">
                <h1 class="mt-3">Quote of the day <i class="bi bi-chat-quote"></i></h1>
                <h5><i class="bi bi-quote"></i>${Quote}<i class="bi bi-quote"></i></h5>
            </div>
        </div>
    </div>

</section>
</body>