<%--<nav class="navbar navbar-expand-md p-darker">--%>
<%--    <div class="container-fluid mx-3">--%>
<%--        <span class="navbar-brand mb-0 h1 col-2 s-light">IBM SkillsBuild</span>--%>
<%--        <div class="col-10">--%>
<%--            <ul class="navbar-nav d-flex justify-content-end gap-md-3">--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link s-light" href="/profile">Profile</a>--%>
<%--                </li>--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link s-light" href="/dashboard">Dashboard</a>--%>
<%--                </li>--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link s-light" href="/courses">Courses</a>--%>
<%--                </li>--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link s-light" href="/leaderboard">Leaderboard</a>--%>
<%--                </li>--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link s-light" href="/logout">Logout</a>--%>
<%--                </li>--%>
<%--            </ul>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</nav>--%>

<div class="d-md-none">
    <!-- Hamburger Menu Navigation Bar -->
    <nav class="navbar p-darker">
        <div class="container-fluid">
            <span class="navbar-brand mb-0 h1 col-2 s-light">IBM SkillsBuild</span>
            <button class="navbar-toggler navbar-dark" type="button" data-bs-toggle="collapse" data-bs-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
    </nav>

    <div class="collapse" id="navbarToggleExternalContent" data-bs-theme="dark">
        <div class="p-darker p-4">
            <ul class="navbar-nav d-flex justify-content-end gap-md-3">
                <li class="nav-item">
                    <a class="nav-link s-light" href="/profile">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link s-light" href="/dashboard">Dashboard</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link s-light" href="/courses">Courses</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link s-light" href="/friends">Friends</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link s-light" href="/leaderboard">Leaderboard</a>
                </li>
                <li class="nav-link">
                    <a class="nav-link s-light" href="/">Notifications</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link s-light" href="/logout">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</div>

<div class="d-none d-md-block">
    <!-- Standard Navigation Bar -->
    <nav class="navbar navbar-expand-md p-darker">
        <div class="container-fluid mx-3">
            <span class="navbar-brand mb-0 h1 col-2 s-light">IBM SkillsBuild</span>
            <div class="col-10">
                <ul class="navbar-nav d-flex justify-content-end gap-md-3">
                    <li class="nav-item">
                        <a class="nav-link s-light" href="/dashboard">Dashboard</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link s-light" href="/courses">Courses</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link s-light" href="/friends">Friends</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link s-light" href="/leaderboard">Leaderboard</a>
                    </li>
                    <li class="nav-item">
                        <div class="dropdown-center">
                            <button style="background-color: var(--primary-darker);border: 0;" type="button" data-bs-toggle="dropdown" data-bs-auto-close="true" aria-expanded="false">
                                <img src="https://eu.ui-avatars.com/api/?name=${(user.firstname)}+${(user.lastname)}&size=200"
                                     alt="User Initials Image" class="rounded-circle p-0 m-0" style="height: 2rem; width: 2rem; position:relative;top:0.3rem"/>
                            </button>
                            <ul class="dropdown-menu" style="background-color: var(--primary-darker);">
                                <li><a class="nav-link s-light" href="/profile">Profile</a></li>
                                <li><a class="nav-link s-light" href="/">Notifications</a></li>
                                <li><a class="nav-link s-light" href="/logout">Logout</a></li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>