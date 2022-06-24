<%@ page import="by.vsu.kovzov.models.Doctor" %>
<%@ page import="by.vsu.kovzov.models.Person" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:choose>
    <c:when test="${not empty doctor}">
        <c:set var="title" value="Редактирование доктора ${doctor.name}"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Добавление нового доктора"/>
    </c:otherwise>
</c:choose>

<u:page title="${title}" errorMsg="${param.message}">
    <c:url var="saveUrl" value="/doctor/save.html"/>
    <form action="${saveUrl}" method="post">
        <c:if test="${not empty doctor}">
            <input type="hidden" name="id" value="${doctor.id}">
        </c:if>
        <div>
            <label for="name">имя:</label>
            <input type="text" id="name" name="name" value="${doctor.name}">
        </div>

        <div>
            <label for="surname">фамилия:</label>
            <input type="text" id="surname" name="surname" value="${doctor.surname}">
        </div>

        <div>
            <label for="patronymic">отчество:</label>
            <input type="text" id="patronymic" name="patronymic" value="${doctor.patronymic}">
        </div>

        <div>
            <label for="sex">пол:</label>
            <select id="sex" name="sex">
                <c:forEach var="item" items="<%=Person.Sex.values()%>">
                    <option selected value="${item}">${item.name}</option>
                </c:forEach>
            </select>
        </div>

        <div>
            <label for="dob">дата рождения:</label>
            <input type="date" id="dob" name="dob" value="${doctor.dob}">
        </div>

        <div>
            <label for="employmentDate"> дата приёма на работу:</label>
            <input type="date" id="employmentDate" name="employmentDate" value="${doctor.employmentDate}">
        </div>

        <c:if test="${not specialization.narrow}">
            <div>
                <label for="branchNo">номер участка:</label>
                <input type="number" id="branchNo" name="branchNo" value="${doctor.branchNo}">
            </div>
        </c:if>

        <input type="text" hidden name="specializationId" value="${specialization.id}">

        <button type="submit">Сохранить</button>
    </form>
    <c:url var="doctorListUrl" value="/doctor/list.html">
        <c:param name="specializationId" value="${specialization.id}"/>
    </c:url>
    <a href="${doctorListUrl}">Отмена</a>
</u:page>
