<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="u" tagdir="/WEB-INF/tags" %>

<u:page title="Список специализаций" errorMsg="${param.message}">

    <div class="scroll-table">
        <table>
            <thead>
            <tr>
                <th>Имя</th>
                <th>Узкий специалист</th>
                <th>Количество врачей</th>
                <th>Заработная плата</th>
                <th>Общие затраты</th>
                <th>Подробнее</th>
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
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</u:page>
