<%--
  Created by IntelliJ IDEA.
  User: Nataly
  Date: 19.05.2020
  Time: 23:31
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
<h2>Books</h2>
<table>
    <tr>
        <th>Author</th>
        <th>Name</th>
        <th>Description</th>
        <th>Action</th>
    </tr>
    <c:forEach var="book" items="${books}">
        <tr>
            <td>${book.author}</td>
            <td>${book.name}</td>
            <td>${book.description}</td>
            <td>
                <a href="books/edit/${book.id}">edit</a>
                <a href="books/delete/${book.id}">delete</a>
            </td>
        </tr>
    </c:forEach>

    <tr>
        <c:url value="/books/add" var="add"/>
        <td colspan="4"><a href="${add}">Add new book</a></td>
    </tr>
</table>

</body>
</html>

