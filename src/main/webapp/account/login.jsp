<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Imgur - Login</title>
</head>
<body>
<h1>Imgur Login</h1>
<h2>Java is a boilerplate-driven language designed for writing verbose object-oriented instant-legacy code</h2>
<form action="${pageContext.request.contextPath}/home" method="post">
    <label for="username-input">Username:</label>
    <input id="username-input" type="text" name="username">
    <label for="password-input">Password:</label>
    <input id="password-input" type="password" name="password">
    <input type="submit" value="Login">
</form>
</body>
</html>