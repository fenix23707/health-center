<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:choose>
    <c:when test="${not empty specialization}">
        <c:set var="title" value="Редактирование специальности ${specialization.name}"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Добавление новой специальности"/>
    </c:otherwise>
</c:choose>

<u:page title="${title}" errorMsg="${param.message}">
    <c:url var="specializationSaveUrl" value="/specialization/save.html"/>
    <form action="${specializationSaveUrl}" method="post">
        <c:if test="${not empty specialization}">
            <input type="hidden" name="id" value="${specialization.id}">
        </c:if>
        <div>
            название:
            <input type="text" name="name" value="${specialization.name}">
        </div>
        <div>
            ставка заработной платы:
            <input type="number" step="any" name="wageRate" value="${specialization.wageRate}">
        </div>
        <div>
            узкий специалист:
            <c:if test="${specialization.narrow}">
                <c:set var="checked" value="checked"></c:set>
            </c:if>
            <input type="checkbox" name="narrow"  ${checked}>
        </div>
        <button type="submit">Сохранить</button>
    </form>
</u:page>
