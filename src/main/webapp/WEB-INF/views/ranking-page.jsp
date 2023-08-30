<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ranking.css">
</head>
<body>
<div class="container">
    <header>Hangman game</header>
    <main>
        <div class="ranking-all-time">
            <table>
                <th>Players all time ranking</th>
                <c:forEach items="${playersAllTime}" var="playerAllTime">
                    <tr>
                        <td>${playerAllTime.name}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="ranking-last-month">
            <table>
                <th>Players last month ranking</th>
                <c:forEach items="${playersLastMonth}" var="playersLastMonth">
                    <tr>
                        <td>${playersLastMonth.name}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <%--<div class="invite-link">Link you were trying to access: <c:out value="${link}"/></div>
        <br>--%>
        <div class="create-game"><a href="${pageContext.request.contextPath}/new-game">Click here to create new game</a>
        </div>
    </main>
    <footer></footer>
</div>
</body>
</html>