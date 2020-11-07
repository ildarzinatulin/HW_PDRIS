<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h1>Login</h1>

<c:if test="${requestScope.isUserExist eq false}">
    <div>ERROR: User ${requestScope.lastName} not exist.</div>
</c:if>

<c:if test="${requestScope.isUserExist eq true}">
    <c:if test="${requestScope.isPasswordCorrect eq false}">
        <div>ERROR: Password isn't correct.</div>
    </c:if>
</c:if>

<form method="post" modelAttribute="user">
    username<br>
    <label><input type="text" name="name"></label><br>
    password<br>
    <label><input type="text" name="password"></label><br>
    <input type="submit" value="login" name="Ok"><br>
    <a href="/registration">registration</a>
</form>

</body>
</html>
