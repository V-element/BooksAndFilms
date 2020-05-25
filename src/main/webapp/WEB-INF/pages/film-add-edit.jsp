<%--
  Created by IntelliJ IDEA.
  User: Nataly
  Date: 26.05.2020
  Time: 0:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${title}</title>
    <link href="<c:url value="/resources/style.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<c:if test="${empty film.name}">
    <c:url value="/films/add" var="var"/>
</c:if>
<c:if test="${!empty film.name}">
    <c:url value="/films/edit" var="var"/>
</c:if>
<form action="${var}" method="POST">
    <label for="name">Name</label>
    <input type="text" name="name" id="name" value=${film.name}>
    <label for="description">Description</label>
    <input type="text" name="description" id="description" value=${film.description}>
    <label for="producer">Producer</label>
    <input type="text" name="producer" id="producer" value=${film.producer}>
    <label for="year">Year</label>
    <input type="text" name="year" id="year" value=${film.year}>
    <c:if test="${empty film.name}">
        <input type="submit" value="Add new film">
    </c:if>
    <c:if test="${!empty film.name}">
        <input type="submit" value="Edit film">
    </c:if>
</form>
</body>
</html>
