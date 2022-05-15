<%@ page import="ro.ubb.imgur.model.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Upload Picture</title>
</head>
<body>
    <h1>Upload Picture</h1>
    <form action="/pictures/upload-result" method="post" enctype="multipart/form-data">
        <label for="file-input">Image:</label>
        <input id="file-input" type="file" name="file" accept=".jpg, .jpeg">
        <input type="submit" value="Upload">
    </form>
</body>
</html>
