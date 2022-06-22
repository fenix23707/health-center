<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ page import="by.vsu.kovzov.models.User.Role" %>

<u:page title="Список специализаций" errorMsg="${param.message}">

    <div class="scroll-table">
        <table>
            <thead>
            <tr>
                <th>Имя</th>
                <th>Узкий специалист</th>
                <th>Количество врачей</th>
                <th>Ставка заработной платы</th>
                <th>Общие затраты на оплату труда</th>
                <th>Подробнее</th>
                <c:if test="${(not empty user) && (user.role eq 'REGISTRATOR')}">
                    <th>Изменить</th>
                </c:if>
            </tr>
            </thead>
        </table>

        <div class="scroll-table-body">
            <table>
                <tbody>
                <c:forEach var="specialization" items="${specializations}">

                    <tr>
                        <td>${specialization.name}</td>
                        <td>
                            <c:choose>
                                <c:when test="${specialization.narrow}">
                                    <c:set var="result" value="да"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="result" value="нет"/>
                                </c:otherwise>
                            </c:choose>
                                ${result}
                        </td>
                        <td>
                                ${specialization.doctorsNumber}
                        </td>
                        <td>
                                ${specialization.wageRate}
                        </td>
                        <td>
                                ${specialization.totalCost}
                        </td>
                        <td>
                            <c:url var="doctorListUrl" value="/doctor/list.html"/>
                            <form action="${doctorListUrl}" method="get">
                                <input type="hidden" name="id" value="${specialization.id}">
                                <button type="submit" ${disabled}>Подробнее</button>
                            </form>
                        </td>
                        <c:if test="${not empty user && user.role eq 'REGISTRATOR'}">
                            <td>
                                <c:url var="editUrl" value="/specialization/edit.html">
                                    <c:param name="id" value="${specialization.id}"/>
                                </c:url>
                                <a href="${editUrl}">Изменить</a>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <c:if test="${(not empty user) && (user.role eq 'REGISTRATOR')}">
            <c:url var="createUrl" value="/specialization/edit.html"/>
            <a href="${createUrl}">Добавить</a>
        </c:if>
    </div>
</u:page>
