<form action="/myLogout" method="post">
    <input type="submit" value="Sign Out" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
</form>
