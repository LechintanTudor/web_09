<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Highest Voted Pictures</title>
</head>
<body>
<label for="number-input">Picture count:</label>
<input id="number-input" type="number">
<button id="submit-button">Submit query</button>
<div id="picture-container">

</div>
<script>
    const numberInput = document.getElementById("number-input");
    let submitButton = document.getElementById("submit-button");
    let pictureContainer = document.getElementById("picture-container");

    submitButton.onclick = _ => {
        const limit = parseInt(numberInput.value);

        let request = new XMLHttpRequest();
        request.open("GET", "http://localhost:8080/api/pictures/stats?limit=" + limit);
        request.onreadystatechange = function () {
            if (this.readyState !== XMLHttpRequest.DONE) {
                return;
            }

            pictureContainer.replaceChildren();
            const pictures = JSON.parse(this.response);

            for (const picture of pictures) {
                let image = new Image()
                image.height = 320;
                image.src = "http://localhost:8080/api/pictures?uuid=" + picture.filePath;

                let info = document.createElement("p");
                info.innerText = "Votes: " + picture.totalVotes + " | Author: " + picture.authorName;

                const lineBreak = document.createElement("br");

                pictureContainer.appendChild(image);
                pictureContainer.appendChild(info);
                pictureContainer.appendChild(lineBreak);
            }
        }
        request.send();
    };
</script>
</body>
</html>
