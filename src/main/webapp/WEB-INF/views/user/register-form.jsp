<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ include file="../header-form.jsp"%>
<section class="login-page">
    <h2>Załóż konto</h2>
    <form:form modelAttribute="user" method="post" action="/user/register">
        <div class="form-group">
            <form:input path="firstName" name="firstName" placeholder="Imię" />
            <form:errors path="firstName" cssClass="error"/>
        </div>
        <div class="form-group">
            <form:input path="lastName" name="lastName" placeholder="Nazwisko" />
            <form:errors path="lastName" cssClass="error"/>
        </div>

        <div class="form-group">
            <form:input path="email" name="email" placeholder="Email" />
            <form:errors path="email" cssClass="error"/>
        </div>
        <div class="form-group">
            <form:password path="password" name="password" placeholder="Hasło" />
            <form:errors path="password" cssClass="error"/>
        </div>
        <div class="form-group">
            <input type="password" name="password2" placeholder="Powtórz hasło" />
        </div>

        <div class="form-group form-group--buttons">
            <a href="login.html" class="btn btn--without-border">Zaloguj się</a>
            <sec:authorize access="isAuthenticated()">
                <button class="btn" type="submit">Zapisz</button>
            </sec:authorize>
            <sec:authorize access="isAnonymous()">
                <button class="btn" type="submit">Załóż konto</button>
            </sec:authorize>
        </div>
    </form:form>
</section>

<%@ include file="../footer.jsp"%>