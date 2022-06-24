<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag language="java" pageEncoding="UTF-8" %>

<%@attribute name="title" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@attribute name="errorMsg" required="false" rtexprvalue="true" type="java.lang.String" %>

<c:url var="cssUrl" value="/css/style.css"/>
<c:url var="logoutUrl" value="/logout.html"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${cssUrl}">
    <title>${title}</title>

</head>
<body>
<header class="rightSide">
    <ul>
        <li>
            <c:url var="specListUrl" value="/specialization/list.html"/>
            <a href="${specListUrl}">Специализации</a>
        </li>
        <li>
            <c:url var="doctorListUrl" value="/doctor/list.html">
                <c:param name="specializationId" value="${specialization.id}"/>
            </c:url>
            <a href="${doctorListUrl}">Врачи</a>
        </li>
        <c:if test="${(not empty user) and (user.role == 'ADMIN')}">
            <li>
                <c:url var="userListUrl" value="/user/list.html"/>
                <a href="${userListUrl}">Пользователи</a>
            </li>
        </c:if>
    </ul>
    <c:choose>
        <c:when test="${not empty user}">
            <c:url var="logoutUrl" value="/logout.html"/>
            ${user.login}&nbsp;&mdash; <a href="${logoutUrl}">выйти</a>
        </c:when>
        <c:otherwise>
            <c:url var="loginFormUrl" value="/login-form.html"/>
            <a href="${loginFormUrl}">войти</a>
        </c:otherwise>
    </c:choose>
</header>

<div class="content">
    <h1>${title}</h1>


    <c:if test="${not empty errorMsg}">
        <p class="errorMsg">
                ${errorMsg}
        </p>
    </c:if>

    <jsp:doBody/>

</div>
</body>
</html>