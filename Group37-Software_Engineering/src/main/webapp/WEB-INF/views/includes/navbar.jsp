<div class="d-md-none">
    <!-- Hamburger Menu Navigation Bar -->
    <nav class="navbar p-darker">
        <div class="container-fluid">
            <div class="d-flex align-items-center gap-2">
                <button class="navbar-toggler navbar-dark" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <span class="navbar-brand mb-0 h1 col-2 s-light">IBM SkillsBuild</span>
            </div>
            <ul class="navbar-nav d-flex flex-row align-items-center gap-3" id="hamburger-nav">
            </ul>
        </div>
    </nav>

    <div class="collapse" id="navbarToggleExternalContent" data-bs-theme="dark">
        <div class="p-darker p-4">
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
                <ul class="navbar-nav d-flex justify-content-end gap-md-3" id="standard-nav">
                    <li class="nav-item d-flex align-items-center">
                        <a class="nav-link s-light" href="/dashboard">Dashboard</a>
                    </li>
                    <li class="nav-item d-flex align-items-center">
                        <a class="nav-link s-light" href="/courses">Courses</a>
                    </li>
                    <li class="nav-item d-flex align-items-center">
                        <a class="nav-link s-light" href="/friends">Friends</a>
                    </li>
                    <li class="nav-item d-flex align-items-center">
                        <a class="nav-link s-light" href="/leaderboard">Leaderboard</a>
                    </li>
                    <li class="nav-item d-flex justify-content-center align-items-center dropdown py-3">
                        <i id="bell" class="bi bi-bell s-light" data-bs-toggle="dropdown" aria-expanded="false"
                           style="font-size: 1.5rem;"></i>
                        <span id="bell-badge"
                              class="position-absolute top-1 start-100 translate-middle badge rounded-pill bg-danger d-none">0</span>
                        <div class="dropdown-menu dropdown-menu-end mt-3">
                            <h6 class="px-3 mt-2">Notifications</h6>
                            <hr class="mt-2 mb-0 border border-black"/>
                            <a id="template" href="" class="s-dark text-decoration-none d-none">
                                <div class="card border border-top-0 d-flex flex-row gap-3 p-2 rounded-0 justify-content-between align-items-center">
                                    <div class="d-flex flex-row gap-3 align-items-center">
                                        <img src="" alt="" class="notification-icon border border-dark border-1">
                                        <div>
                                            <p class="notification-description card-title"></p>
                                            <p class="notification-time card-subtitle text-body-secondary"></p>
                                        </div>
                                    </div>
                                    <button type="button" class="btn-close" aria-label="Close"></button>
                                </div>
                            </a>
                            <div class="overflow-auto" id="notifications">
                                <div class="d-flex flex-column justify-content-center align-items-center mt-3 no-notifications">
                                    <i class="bi bi-inbox mb-1 fs-1"></i>
                                    <p class="fs-6 text-center">You have no notifications currently.</p>
                                </div>
                            </div>
                            <div id="notification-footer" class="d-flex flex-column">
                                <hr class="m-0 border border-black"/>
                                <div class="d-flex justify-content-between gap-2">
                                    <button class="py-0 px-3 mt-2 btn btn-link text-decoration-none" id="mark-read"><i
                                            class="bi bi-envelope-open text-black"></i>
                                        Mark all as read
                                    </button>
                                    <button class="py-0 px-3 mt-2 btn btn-link text-decoration-none"
                                            id="clear-notifications"><i class="bi bi-trash3 text-black"></i>
                                        Clear all
                                    </button>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li class="nav-item d-flex align-items-center ps-2" id="profile-circle">
                        <div class="btn-group">
                            <button style="background-color: var(--primary-darker);border: 0;" type="button"
                                    data-bs-toggle="dropdown" data-bs-auto-close="true" aria-expanded="false">
                                <img src="https://eu.ui-avatars.com/api/?name=${(user.firstname)}+${(user.lastname)}&size=200"
                                     alt="User Initials Image" class="rounded-circle p-0 m-0"
                                     style="height: 2rem; width: 2rem;"/>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-end mt-4 p-darker border-1 border-white">
                                <li><a class="dropdown-item s-light" href="/profile">Profile</a></li>
                                <li><a class="dropdown-item s-light" href="/logout">Logout</a></li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>
