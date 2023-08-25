<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
<h1>Ranking all time:</h1>
<c:forEach items="${playersAllTime}" var="playerAllTime">
<tr>
    <td>${playerAllTime.name}</td>
</tr>
</c:forEach>
<h1>Ranking last month:</h1>
<c:forEach items="${playersLastMonth}" var="playersLastMonth">
<tr>
    <td>${playersLastMonth.name}</td>
</tr>
</c:forEach>
</body>
</html>