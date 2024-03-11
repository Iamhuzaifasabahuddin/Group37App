<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<c:forEach items="${achievements}" var="achievement">--%>
<%--    <div class="achievement">--%>
<%--        <h3>${achievement.title}</h3>--%>
<%--        <p>${achievement.description}</p>--%>
<%--        <img src="${achievement.imageUrl}" class="card-img-top" alt="${achievement.title}">--%>
<%--    </div>--%>
<%--</c:forEach>--%>

<c:forEach items="${notachieved}" var="achievement">
    <div class="achievement">
        <h3>${achievement.title}</h3>
        <p>${achievement.description}</p>
        <img src="${achievement.imageUrl}" class="card-img-top" alt="${achievement.title}">
    </div>
</c:forEach>