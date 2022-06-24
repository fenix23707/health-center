<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="u" tagdir="/WEB-INF/tags" %>

<u:page title="Вход" errorMsg="${param.message}">

    <c:url var="loginUrl" value="/login.html"/>
    <form action="${loginUrl}" method="post">
        <P>Имя пользователя:</P>
        <P><input type="text" name="login"></P>
        <P>Пароль:</P>
        <P><input type="password" name="password"></P>
        <P><button type="submit">Войти</button></P>
    </form>

</u:page>