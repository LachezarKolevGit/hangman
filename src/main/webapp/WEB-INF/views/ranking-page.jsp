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
       <div class="ranking-divs-wrapper">
        <div class="ranking-all-time">
            <table>
            <tr><th>Players all time ranking</th></tr>
                
                <c:forEach items="${playersAllTime}" var="playerAllTime">
                    <tr>
                        <td>${playerAllTime.name}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="ranking-last-month">
            <table>
            <tr>  <th>Players last month ranking</th></tr>
              
                <c:forEach items="${playersLastMonth}" var="playersLastMonth">
                    <tr>
                        <td>${playersLastMonth.name}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
      </div>
     <div class="create-game"><a href="${pageContext.request.contextPath}/new-game"><button id=btn-new-game type="button">Click here to create new game</button></a>
       </div>  
    </main>
    <footer></footer>
</div>
</body>
</html>