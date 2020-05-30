<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${title}</title>
    <link href="<c:url value="/resources/style.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<table>
    <tr>
        <th>Books</th>
        <th>Films</th>
    </tr>
    <tr>
        <td><a href="/books"><img src="/resources/books.png" alt=""></a></td>
        <td><a href="/films"><img src="/resources/films.png" alt=""></a></td>
    </tr>
</table>
<p><a href="/about">About application</a></p>
</body>
</html>
