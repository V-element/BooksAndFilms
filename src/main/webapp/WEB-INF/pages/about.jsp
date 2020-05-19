<%--
  Created by IntelliJ IDEA.
  User: Nataly
  Date: 17.05.2020
  Time: 15:54
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
<p><a href="/">Back to homepage</a></p>
<h2>Here's some information about my application:</h2>
<p>It's a web-application witch contains lists of books and films.</p>
<p>Information keeps in 3 ways: DB, JSON, XML.</p>
<p>REST service used in this application.</p>
<p>Get requests used to obtain full list of books/films.</p>
<p>Post request used to find book/film by name and/or author and for adding new book/film.</p>
<p>Online storage of data in the DB, and scheduled synchronization of data from the database with json and xml files.</p>
<p>There are 2 ways to work with the DB: JDBC template and ORM, can be switched in the properties file.</p>
<p>Spring Framework used.</p>
<br>
<p>Made by Gnevanov Egor</p>
</body>
</html>
