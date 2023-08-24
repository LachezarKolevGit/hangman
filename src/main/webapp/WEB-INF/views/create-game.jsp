<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create new game</title>
</head>
<body>
<h1>Create new game</h1>
<div class=form-wrapper>
    <form method="POST" action="${pageContext.request.contextPath}/new-game">
        <div class=form-main>
            <label>Player Name</label>
            <input type="text" name="playerName"><br>
            <label>Word to be guessed</label>
            <input type="text" name="word"><br>
        </div>
        <div class=form-footer>
            <button id="btn-submit" type="submit" value="Submit"></button>
        </div>
    </form>
</div>
</body>
</html>