<%@ page import="by.vsu.kovzov.models.User" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:choose>
    <c:when test="${not empty user_edit}">
        <c:set var="title" value="Редактирование пользователя ${user_edit.login}"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Добавление нового пользователя"/>
    </c:otherwise>
</c:choose>

<u:page title="${title}" errorMsg="${param.message}">
    <c:url var="saveUrl" value="/user/save.html"/>
    <form action="${saveUrl}" method="post">
        <c:if test="${not empty user_edit}">
            <input type="hidden" name="id" value="${user_edit.id}">
        </c:if>
        <div>
            <label for="login">Логин: </label>
            <input type="text" id="login" name="login" value="${user_edit.login}">
        </div>
        <div>
            <label for="password">Пароль: </label>
            <input type="password" id="password" name="password">
        </div>
        <div>
            <label for="role">Роль: </label>
            <select id="role" name="role">
                <c:forEach var="item" items="<%=User.Role.values()%>">
                    <c:choose>

                        <c:when test="${user_edit.role eq item}">
                            <c:set var="selected" value="selected"/>
                        </c:when>
                        <c:otherwise>
                            <c:remove var="selected"/>
                        </c:otherwise>
                    </c:choose>
                    <option ${selected} value="${item}">${item.name}</option>
                </c:forEach>
            </select>

        </div>

        <button type="submit">Сохранить</button>
    </form>
    <c:url var="listUrl" value="/user/list.html"/>
    <a href="${listUrl}">Отмена</a>
</u:page>
