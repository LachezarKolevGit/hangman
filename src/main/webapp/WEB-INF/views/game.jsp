<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Play</title>
</head>
<body>
<p>Enter game id</p>

<div class=form-wrapper>
    <form:form method="POST"
               action="${pageContext.request.contextPath}/game"
               modelAttribute="playerInput" cssClass="form">
        <div class=form-main>
        	<label>Game id</label> <input type="number" id="gameId" name="gameId"> <br>
            <hr>
            <label>Position</label> <input type="number" id="position" name="position"> <br>
            <form:errors path="position" element="div" cssClass="error-div"/>
            <hr>
            <label>Character</label>
            <form:input path="character"/>
            <form:errors path="character" element="div" cssClass="error-div"/>
            <label>Player id</label>
			<input type="text" name="playerId"> <br>
        </div>
        <div class=form-footer>
            <button id="btn-submit" type="submit" value="Submit"></button>
        </div>
    </form:form>
</div>
</body>
</html>