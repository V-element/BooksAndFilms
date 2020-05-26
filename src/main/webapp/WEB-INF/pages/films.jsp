<%--
  Created by IntelliJ IDEA.
  User: Nataly
  Date: 26.05.2020
  Time: 0:36
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
<h2>${title}</h2>
<form action="/films/search" method="GET">
    <p><input type="search" name="name" placeholder="Name" value="${name}">
        <input type="search" name="year" placeholder="Year" value="${year}">
        <input type="submit" value="Search!"></p>
</form>
<table>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Producer</th>
        <th>Year</th>
        <th>Action</th>
    </tr>
    <c:forEach var="film" items="${films}">
        <tr>
            <td>${film.name}</td>
            <td>${film.description}</td>
            <td>${film.producer}</td>
            <td>${film.year}</td>

            <td>
                <a href="/films/edit?id=${film.id}">edit</a>
                <a href="/films/delete?id=${film.id}">delete</a>
            </td>
        </tr>
    </c:forEach>

    <c:if test="${filmsCount == 0}">
        <tr>
            <td colspan="5">
                The list is empty but you can add a new Film!
            </td>
        </tr>
    </c:if>

    <tr>
        <c:url value="/films/add" var="add"/>
        <td colspan="5"><a href="${add}">Add new film</a></td>
    </tr>
</table>

</body>
</html>
