<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ page import="by.vsu.kovzov.models.User.Role" %>

<u:page title="Список пользовотелей" errorMsg="${param.message}">

    <div class="scroll-table">
        <table>
            <thead>
            <tr>
                <th>Логин</th>
                <th>Роль</th>
                <th>Изменить</th>
                <th>Удалить</th>
            </tr>
            </thead>
        </table>

        <div class="scroll-table-body">
            <table>
                <tbody>
                <c:forEach var="user" items="${users}">

                    <tr>
                        <td>${user.login}</td>
                        <td>${user.role.name}</td>
                        <td>
                            <c:url var="userEditUrl" value="/user/edit.html">
                                <c:param name="id" value="${user.id}"/>
                            </c:url>
                            <a href="${userEditUrl}">Изменить</a>
                        </td>
                        <td>
                            <c:url var="deleteUrl" value="/user/delete.html">
                                <c:param name="id" value="${user.id}"/>
                            </c:url>
                            <a href="${deleteUrl}">Удалить</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <c:url var="userEditUrl" value="/user/edit.html"/>
    <form action="${userEditUrl}" method="get">
        <div>
            <button type="submit">Добавить</button>
        </div>

    </form>

</u:page>
