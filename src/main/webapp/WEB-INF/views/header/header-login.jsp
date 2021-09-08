<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<ul class="nav--actions">
    <sec:authorize access="isAnonymous()">
    <li><a href="/user/login" class="btn btn--small btn--without-border">Zaloguj</a></li>
    <li><a href="/user/form" class="btn btn--small btn--highlighted">Załóż konto</a></li>
    </sec:authorize>
    <li><sec:authorize access="isAuthenticated()">
<%--        <a href="<c:url value="/logout" />" class="btn btn--small btn--highlighted">Wyloguj</a>--%>
        <form action="<c:url value="/logout"/>" method="post">
            <input type="submit" value="Wyloguj" class="btn btn--small btn--highlighted">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </sec:authorize></li>
</ul>