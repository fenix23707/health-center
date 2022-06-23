<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ page import="by.vsu.kovzov.models.Doctor" %>


<c:if test="${(empty user) && (user.role != 'REGISTRATOR')}">
    <c:set var="hidden" value="hidden"></c:set>
</c:if>

<u:page title="Список врачей по специальности ${specialization.name} ">
    <div class="scroll-table">
        <table>
            <thead>
            <tr>
                <th>ФИО</th>
                <th>Дата рождения</th>
                <th>Дата приёма на работу</th>
                <th>Номер участка</th>
                <th>Заработная плата</th>
                <th ${hidden}>Изменить</th>
            </tr>
            </thead>
        </table>

        <div class="scroll-table-body">
            <table>
                <tbody>
                <c:forEach var="doctor" items="${doctors}">

                    <tr>
                        <td>${doctor.name + " " + doctor.surname + " " + doctor.patronymic}</td>
                        <td>
                                ${doctor.dob}
                        </td>
                        <td>
                                ${doctor.employmentDate}
                        </td>
                        <td>
                                ${doctor.salary}
                        </td>
                        <td>
                                ${doctor.branchNo}
                        </td>

                        <td ${hidden}>
                            <c:url var="editUrl" value="/doctor/edit.html">
                                <c:param name="id" value="${doctor.id}"/>
                            </c:url>
                            <a ${hidden} href="${editUrl}">Изменить</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <c:url var="createUrl" value="/doctor/edit.html"/>
        <a ${hidden} href="${createUrl}">Добавить</a>
    </div>
</u:page>