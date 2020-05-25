<%--
  Created by IntelliJ IDEA.
  User: Nataly
  Date: 21.05.2020
  Time: 22:28
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
<c:if test="${empty book.name}">
    <c:url value="/books/add" var="var"/>
</c:if>
<c:if test="${!empty book.name}">
    <c:url value="/books/edit" var="var"/>
</c:if>
<form action="${var}" method="POST">
    <label for="author">Author</label>
    <input type="text" name="author" id="author" value=${book.author}>
    <label for="name">Name</label>
    <input type="text" name="name" id="name" value=${book.name}>
    <label for="description">Description</label>
    <input type="text" name="description" id="description" value=${book.description}>
    <c:if test="${empty book.name}">
        <input type="submit" value="Add new book">
    </c:if>
    <c:if test="${!empty book.name}">
        <input type="submit" value="Edit book">
    </c:if>
</form>
</body>
</html>
