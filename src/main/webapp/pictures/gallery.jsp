<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Picture Gallery</title>
</head>
<body>
    <h1>Picture Gallery</h1>
    <div id="picture-container"></div>
    <script>
        let pictureContainer = document.getElementById("picture-container");

        let request = new XMLHttpRequest();
        request.open("GET", "http://localhost:8080/api/pictures/data");
        request.onreadystatechange = function() {
            if (this.readyState === XMLHttpRequest.DONE) {
                console.log(this.response);
                const response = JSON.parse(this.response);

                if (response === null) {
                    window.location.assign("http://localhost:8080/login.jsp");
                    return;
                }

                for (const picture of response) {
                    let image = new Image();
                    image.height = 320;
                    image.src = "http://localhost:8080/api/pictures?uuid=" + picture.filePath;
                    pictureContainer.appendChild(image);

                    const lineBreak = document.createElement("br");
                    pictureContainer.appendChild(lineBreak);
                }
            }
        };
        request.send();
    </script>
</body>
</html>
