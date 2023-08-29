<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Play</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/active-game.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/active-game.js"></script>

</head>
<body>
<div class="container">
    <header>
        <p>Game's state: <c:out value="${game.state}"/></p>
        <p>Game created by: <c:out value="${game.createdBy.name}"/></p>
        <p>Player: <c:out value="${game.playedBy.name}"/> with score: <c:out value="${currentPlayer.ranking.score}"/></p>
    </header>
    <main>
        <div class=form-wrapper>
            <div class="form-header">
                <div class="word-wrapper">
                    <h2><c:forEach items="${guessedChars}" var="guessedChar">
                        <c:choose>
                            <c:when test="${guessedChar == null}">
                                <div class="word-char">_</div>
                            </c:when>
                            <c:otherwise>
                                <div class="word-char">${guessedChar}</div>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach></h2>
                </div>
                <div class="lives-remaining">
                    Remaining lives:
                    <c:set var = "lives" value = "${game.stats.livesRemaining}"/>
                    <c:out value = "${lives}"/>
                </div>
            </div>
            <div class="image-container">

                <c:choose>
                    <c:when test="${game.state == 'FINISHED' && lives > 0}">
                        <img src="${pageContext.request.contextPath}/images/win.jpg" alt="Game won image">
                    </c:when>
                    <c:otherwise>
                        <c:if test="${lives <= 0}">
                            <img src="${pageContext.request.contextPath}/images/endGame.jpg" alt="Game over image">
                            <h3>You lose</h3>
                        </c:if>
                        <c:if test="${lives == 1}">
                            <img src="${pageContext.request.contextPath}/images/8.jpg" alt="One attempts remaining image">
                        </c:if>
                        <c:if test="${lives == 2}">
                            <img src="${pageContext.request.contextPath}/images/4.jpg" alt="Two attempts remaining image">
                        </c:if>
                        <c:if test="${lives == 3}">
                            <img src="${pageContext.request.contextPath}/images/1.jpg" alt="Three attempts remaining image">
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="form-main">
                <c:url var="gameUrl" value="/${game.id}" context="/play"/>
                <form:form method="POST"
                           action="${gameUrl}"
                           modelAttribute="playerInput" cssClass="form">
                    <div class=form-fields>
                        <label>Character</label>
                        <form:input path="character" id="form-input-character"/>
                    </div>
                    <div class=form-footer>
                        <button id="btn-submit" type="submit" value="Submit">Submit</button>
                    </div>
                </form:form>
            </div>
            <div class="alphabet-wrapper">
                <c:forEach items="${alphabet}" var="alphabetMap">
                    <c:choose>
                        <c:when test="${alphabetMap.value == true}">
                            <div class="alphabet-guessed-char">${alphabetMap.key}</div>
                        </c:when>
                        <c:otherwise>
                            <div class="alphabet-char">${alphabetMap.key}</div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
        </div>
    </main>
    <footer>
        <div class="footer-content">
        </div>
    </footer>
</div>
</body>
</html>