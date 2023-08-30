<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register user</title>
</head>
<body>

<div class=form-wrapper>
<div class="form-header">
<h2>Register, below</h2>
</div>
    <form:form method="POST"
               action="${pageContext.request.contextPath}/register"
               modelAttribute="playerDto" cssClass="form">
        <div class=form-main>
            <label>Enter username</label>
            <form:input path="name"/>
            <form:errors path="name" element="div" cssClass="error-div"/>
        </div>
        <div class=form-footer>
            <button id="btn-submit" type="submit" value="Submit">Register</button>
        </div>
        <a href="${pageContext.request.contextPath}/login">Click here to login
			</a>
    </form:form>
</div>
</body>
</html>