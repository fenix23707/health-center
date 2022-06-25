<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ page import="by.vsu.kovzov.models.User.Role" %>

<c:if test="${(empty user) || (user.role != 'REGISTRATOR')}">
    <c:set var="hidden" value="hidden"></c:set>
</c:if>

<u:page title="Список специализаций" errorMsg="${param.message}">

    <div class="scroll-table">
        <table>
            <thead>
            <tr>
                <th data-column="name">Имя</th>
                <th>Узкий специалист</th>
                <th data-column="doctorsNumber">Количество врачей</th>
                <th data-column="wageRate">Ставка заработной платы</th>
                <th data-column="totalCost">Общие затраты на оплату труда</th>
                <th>Подробнее</th>
                <th ${hidden}>Изменить</th>
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
                            <c:url var="doctorListUrl" value="/doctor/list.html">
                                <c:param name="specializationId" value="${specialization.id}"/>
                            </c:url>
                            <a href="${doctorListUrl}">Подробнее</a>
                        </td>
                        <td ${hidden}>
                            <c:url var="editUrl" value="/specialization/edit.html">
                                <c:param name="id" value="${specialization.id}"/>
                            </c:url>
                            <a href="${editUrl}">Изменить</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <c:url var="createUrl" value="/specialization/edit.html"/>
        <a ${hidden} href="${createUrl}">Добавить</a>
    </div>
</u:page>
