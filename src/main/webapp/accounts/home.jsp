<%@ page import="ro.ubb.imgur.model.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Imgur - Home</title>
</head>
<body>
    <%
        Account account = (Account)session.getAttribute("account");
        out.println(String.format("Hello, %s", account.username));
    %>
</body>
</html>
