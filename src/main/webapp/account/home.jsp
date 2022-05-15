<%@ page import="ro.ubb.imgur.model.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Imgur - Home</title>
</head>
<body>
    <%
        Account account = (Account)session.getAttribute("account");
        out.println(String.format("<h1>Welcome back, %s</h1>", account.username));
    %>
    <ul>
        <li><a href="${pageContext.request.contextPath}/pictures/gallery">Picture gallery</a></li>
        <li><a href="${pageContext.request.contextPath}/pictures/upload">Upload picture</a></li>
        <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
    </ul>
</body>
</html>
