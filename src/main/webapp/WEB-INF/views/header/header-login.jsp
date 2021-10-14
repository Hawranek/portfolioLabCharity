<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<ul class="nav--actions">
    <sec:authorize access="isAnonymous()">
        <li><a href="<c:url value="/user/login"/>" class="btn btn--small btn--without-border">Zaloguj</a></li>
        <li><a href="<c:url value="/user/register"/>" class="btn btn--small btn--highlighted">Załóż konto</a></li>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <li>
            <a href="<c:url value="/user/editprofile"/>" class="btn btn--small btn--without-border">Edytuj dane</a>
        </li>
        <li>
            <form action="<c:url value="/logout"/>" method="post">
                <input type="submit" value="Wyloguj" class="btn btn--small btn--highlighted">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </li>
    </sec:authorize>
</ul>