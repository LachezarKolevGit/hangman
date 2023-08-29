<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Create new game</title>
</head>
<body>
<h1>Create new game</h1>
<div class=form-wrapper>
    <form:form method="POST"
               action="${pageContext.request.contextPath}/new-game"
               modelAttribute="gameCreationRequest" cssClass="form">
        <div class=form-main>
            <label>Word to be guessed</label>
            <input type="text" name="word"><br>
            <label>Lives (Min - 3, Max - 7)</label>
            <input type="range" name="lives" min="3" max="10">
            <br>
        </div>
        <div class=form-footer>
            <button id="btn-submit" type="submit" value="Submit"></button>
        </div>
    </form:form>
</div>
</body>
</html>