<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Profile</title>
    <%@include file="../includes/head.jsp"%>
</head>
<%@include file="../includes/navbar.jsp"%>
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

        <div class="card mb-3 mx-auto text-center" style="max-width: 540px; background-color: var(--primary-lightest); border: 0;">
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
                        <h2 class="mb-1 fs-5">Friends Since: ${since}</h2>
                    </div>
                </div>
            </div>
        </div>

    </div>
</section>

</body>
<script>
    document.getElementById('backButton').addEventListener('click', function () {
    window.location.href = "${pageContext.request.contextPath}/friends";
    });
</script>
</html>