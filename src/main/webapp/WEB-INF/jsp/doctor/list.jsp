<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ page import="by.vsu.kovzov.models.Doctor" %>


<c:if test="${(empty user) || (user.role != 'REGISTRATOR') || (empty specialization)}">
    <c:set var="hidden" value="hidden"></c:set>
</c:if>

<u:page title="Список врачей по специальности ${specialization.name}" errorMsg="${message}">
    <div class="scroll-table">
        <table>
            <thead>
            <tr>
                <th>ФИО</th>
                <th>Дата рождения</th>
                <th>Дата приёма на работу</th>
                <th>Заработная плата</th>
                <th>Номер участка</th>
                <th ${hidden}>Изменить</th>
            </tr>
            </thead>
        </table>

        <div class="scroll-table-body">
            <table>
                <tbody>
                <c:forEach var="doctor" items="${doctors}">

                    <tr>
                        <td>
                            <span>${doctor.name} </span>
                            <span>${doctor.surname} </span>
                            <span>${doctor.patronymic} </span>
                        </td>
                        <td>${doctor.dob}</td>
                        <td>${doctor.employmentDate}</td>
                        <td>${doctor.salary}</td>
                        <td>${doctor.branchNo}</td>
                        <td ${hidden}>
                            <c:url var="editUrl" value="/doctor/edit.html">
                                <c:param name="id" value="${doctor.id}"/>
                                <c:param name="specializationId" value="${specialization.id}"/>
                            </c:url>
                            <a ${hidden} href="${editUrl}">Изменить</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <td ${hidden}>
            <c:url var="editUrl" value="/doctor/edit.html">
                <c:param name="specializationId" value="${specialization.id}"/>
            </c:url>
            <a ${hidden} href="${editUrl}">Добавить</a>
        </td>
    </div>
</u:page>