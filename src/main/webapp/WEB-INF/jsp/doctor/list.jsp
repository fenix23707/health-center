<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ page import="by.vsu.kovzov.models.Doctor" %>


<c:if test="${(empty user) || (user.role != 'REGISTRATOR') || (empty specialization)}">
    <c:set var="hidden" value="hidden"></c:set>
</c:if>

<u:page title="Список врачей по специальности ${specialization.name}" errorMsg="${param.message}">
    <div class="scroll-table">
        <table>
            <thead>
            <tr>
                <th data-column="name surname patronymic">ФИО</th>
                <th data-column="dob">Дата рождения</th>
                <th data-column="employment_date">Дата приёма на работу</th>
                <th data-column="salary">Заработная плата</th>
                <th data-column="branch_id">Номер участка</th>
                <th ${hidden}>Изменить</th>
                <th ${hidden}>Удалить</th>
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
                            <a href="${editUrl}">Изменить</a>
                        </td>
                        <td ${hidden}>
                            <c:url var="deleteUrl" value="/doctor/delete.html">
                                <c:param name="id" value="${doctor.id}"/>
                                <c:param name="specializationId" value="${specialization.id}"/>
                            </c:url>
                            <a href="${deleteUrl}">Удалить</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div ${hidden}>
            <c:url var="editUrl" value="/doctor/edit.html">
                <c:param name="specializationId" value="${specialization.id}"/>
            </c:url>
            <a ${hidden} href="${editUrl}">Добавить</a>
        </div>

        <div ${hidden}>
            <c:url var="deleteUrl" value="/specialization/delete.html">
                <c:param name="specializationId" value="${specialization.id}"/>
            </c:url>
            <a href="${deleteUrl}">Удалить</a>
        </div>
    </div>
</u:page>