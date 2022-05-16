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
    request.onreadystatechange = function () {
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

                const submitVote = (pictureId, voteValue) => {
                    let request = new XMLHttpRequest();
                    request.open("POST", "http://localhost:8080/api/pictures/data");
                    request.send(JSON.stringify({pictureId, voteValue}));
                    alert("You submitted a vote!");
                };

                let vote1Button = document.createElement("button");
                vote1Button.innerText = "Vote 1";
                vote1Button.onclick = _ => submitVote(picture.id, 1);

                let vote2Button = document.createElement("button");
                vote2Button.innerText = "Vote 2";
                vote2Button.onclick = _ => submitVote(picture.id, 2);

                let vote3Button = document.createElement("button");
                vote3Button.innerText = "Vote 3";
                vote3Button.onclick = _ => submitVote(picture.id, 3);

                let vote4Button = document.createElement("button");
                vote4Button.innerText = "Vote 4";
                vote4Button.onclick = _ => submitVote(picture.id, 4);

                let vote5Button = document.createElement("button");
                vote5Button.innerText = "Vote 5";
                vote5Button.onclick = _ => submitVote(picture.id, 5);

                let buttonContainer = document.createElement("div");
                buttonContainer.appendChild(vote1Button);
                buttonContainer.appendChild(vote2Button);
                buttonContainer.appendChild(vote3Button);
                buttonContainer.appendChild(vote4Button);
                buttonContainer.appendChild(vote5Button);

                const lineBreak = document.createElement("br");

                pictureContainer.appendChild(image);
                pictureContainer.appendChild(buttonContainer);
                pictureContainer.appendChild(lineBreak);
            }
        }
    };
    request.send();
</script>
</body>
</html>
