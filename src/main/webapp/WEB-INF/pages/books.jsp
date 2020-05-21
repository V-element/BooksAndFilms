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
<a href="/">Back to homepage</a>
<h2>Books</h2>
<form action="/books/search" method="GET">
    <p><input type="search" name="name" placeholder="Name">
        <input type="search" name="author" placeholder="Author">
    <input type="submit" value="Search!"></p>
</form>
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
                <a href="books/edit?id=${book.id}">edit</a>
                <a href="books/delete?id=${book.id}">delete</a>
            </td>
        </tr>
    </c:forEach>

    <c:if test="${booksCount == 0}">
        <tr>
            <td colspan="4">
                The list is empty but you can add a new Book!
            </td>
        </tr>
    </c:if>

    <tr>
        <c:url value="/books/add" var="add"/>
        <td colspan="4"><a href="${add}">Add new book</a></td>
    </tr>
</table>

</body>
</html>

