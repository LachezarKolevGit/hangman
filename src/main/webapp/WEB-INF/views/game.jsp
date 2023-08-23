<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
<h1>Welcome</h1>
<p>Start new game</p>
<p>Continue game</p>
<p>See stats</p>

<c:forEach items="${list}" var="list">
<tr>
    <td>${list}</td>
</tr>
</c:forEach>
</body>
</html>