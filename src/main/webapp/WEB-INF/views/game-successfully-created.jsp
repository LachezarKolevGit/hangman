<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Game created successfully</title>
</head>
<body>
<h1>Game created successfully</h1>
<c:url var="newGameUrl" value="/${gameId}" context="/play"/>
<a href="${newGameUrl}">Copy link</a>

<%--<c:set var="newGameUrl" value="$/play/${gameId}" />--%>
<p>Share game: ${newGameUrl}</p>

</body>
</html>