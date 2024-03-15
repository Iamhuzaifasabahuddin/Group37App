<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <%@include file="includes/head.jsp"%>
    <script type="text/javascript" src="static/leaderboard.js" defer></script>
    <meta charset="ISO-8859-1">
    <title>Leaderboard</title>
</head>
<%@include file="includes/navbar.jsp"%>
<body>
<div class="tab-navigation">
    <ul class="nav nav-tabs justify-content-center p-2 border-dark">
        <li class="nav-item rounded">
            <a id="bronze-tab" class="nav-link" href="#bronze" onclick="openTab('bronze', 'bronze-tab')">Bronze</a>
        </li>
        <li class="nav-item rounded">
            <a id="silver-tab" class="nav-link" href="#silver" onclick="openTab('silver', 'silver-tab')">Silver</a>
        </li>
        <li class="nav-item rounded">
            <a id="gold-tab" class="nav-link" href="#gold" onclick="openTab('gold', 'gold-tab')">Gold</a>
        </li>
        <li class="nav-item rounded">
            <a id="platinum-tab" class="nav-link" href="#platinum" onclick="openTab('platinum', 'platinum-tab')">Platinum</a>
        </li>
        <li class="nav-item rounded">
            <a id="titanium-tab" class="nav-link" href="#titanium" onclick="openTab('titanium', 'titanium-tab')">Titanium</a>
        </li>
        <li class="nav-item rounded">
            <a id="elysium-tab" class="nav-link" href="#elysium" onclick="openTab('elysium', 'elysium-tab')">Elysium</a>
        </li>
        <li class="nav-item rounded">
            <a id="global-tab" class="nav-link" href="#global" onclick="openTab('global', 'global-tab')">Global</a>
        </li>
        <li class="nav-item rounded">
            <a id="friends-tab" class="nav-link" href="#friends" onclick="openTab('friends', 'friends-tab')">Friends</a>
        </li>
    </ul>
</div>


<div id="bronze" class="tab pb-4">
    <h1 class="text-center my-3">Bronze League</h1>
    <div class="table-container">
        <table>
            <thead>
            <tr>
                <th>Rank</th>
                <th>Username</th>
                <th>Points</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${bronze}" varStatus="status">
                <tr>
                    <td>
                        <c:out value="${status.index + 1}"/><c:out value="${suffix[status.index]}"/>.
                    </td>
                    <td>${user.username}</td>
                    <td>${user.points}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div id="silver" class="tab pb-4">
    <h1 class="text-center my-3">Silver League</h1>
    <div class="table-container">
        <table>
            <thead>
            <tr>
                <th>Rank</th>
                <th>Username</th>
                <th>Points</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${silver}" varStatus="status">
                <tr>
                    <td>
                        <c:out value="${status.index + 1}"/><c:out value="${suffix[status.index]}"/>.
                    </td>
                    <td>${user.username}</td>
                    <td>${user.points}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div id="gold" class="tab pb-4">
    <h1 class="text-center my-3">Gold League</h1>
    <div class="table-container">
        <table>
            <thead>
            <tr>
                <th>Rank</th>
                <th>Username</th>
                <th>Points</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${gold}" varStatus="status">
                <tr>
                    <td>
                        <c:out value="${status.index + 1}"/><c:out value="${suffix[status.index]}"/>.
                    </td>
                    <td>${user.username}</td>
                    <td>${user.points}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>


<div id="platinum" class="tab pb-4">
    <h1 class="text-center my-3">Platinum League</h1>
    <div class="table-container">
        <table>
            <thead>
            <tr>
                <th>Rank</th>
                <th>Username</th>
                <th>Points</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${platinum}" varStatus="status">
                <tr>
                    <td>
                        <c:out value="${status.index + 1}"/><c:out value="${suffix[status.index]}"/>.
                    </td>
                    <td>${user.username}</td>
                    <td>${user.points}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div id="titanium" class="tab pb-4">
    <h1 class="text-center my-3">Titanium League</h1>
    <div class="table-container">
        <table>
            <thead>
            <tr>
                <th>Rank</th>
                <th>Username</th>
                <th>Points</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${titanium}" varStatus="status">
                <tr>
                    <td>
                        <c:out value="${status.index + 1}"/><c:out value="${suffix[status.index]}"/>.
                    </td>
                    <td>${user.username}</td>
                    <td>${user.points}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div id="elysium" class="tab pb-4">
    <h1 class="text-center my-3">Elysium League</h1>
    <div class="table-container">
        <table>
            <thead>
            <tr>
                <th>Rank</th>
                <th>Username</th>
                <th>Points</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${elysium}" varStatus="status">
                <tr>
                    <td>
                        <c:out value="${status.index + 1}"/><c:out value="${suffix[status.index]}"/>.
                    </td>
                    <td>${user.username}</td>
                    <td>${user.points}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div id="global" class="tab pb-4">
    <h1 class="text-center my-3">Global Leaderboard</h1>
    <div class="table-container">
        <table>
            <thead>
            <tr>
                <th>Rank</th>
                <th>Username</th>
                <th>Points</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${global}" varStatus="status">
                <tr>
                    <td>
                        <c:out value="${status.index + 1}"/><c:out value="${suffix[status.index]}"/>.
                    </td>
                    <td>${user.username}</td>
                    <td>${user.points}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div id="friends" class="tab pb-4">
    <h1 class="text-center my-3">Friends Leaderboard</h1>
    <div class="table-container">
        <table>
            <thead>
            <tr>
                <th>Rank</th>
                <th>Username</th>
                <th>Points</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${friends}" varStatus="status">
                <tr>
                    <td>
                        <c:out value="${status.index + 1}"/><c:out value="${suffix[status.index]}"/>.
                    </td>
                    <td>${user.username}</td>
                    <td>${user.points}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script>
    var userLeague = "${userLeague}";
    setDefaultTab(userLeague);
</script>
</body>
</html>