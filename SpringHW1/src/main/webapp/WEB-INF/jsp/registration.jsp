<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>

<h1>Registration</h1>

<c:if test="${requestScope.didTryToUseAdminName eq true}">
    <div>ERROR: You can't use "admin" as name.</div>
</c:if>

<c:if test="${requestScope.didTryToUseExistName eq true}">
    <div>ERROR: This name is already in use.</div>
</c:if>

<form method="post" modelAttribute="user">
    username<br>
    <label><input type="text" name="name"></label><br>
    password<br>
    <label><input type="text" name="password"></label><br>
    <input type="submit" value="submit" name="Ok"><br>
</form>

</body>
</html>