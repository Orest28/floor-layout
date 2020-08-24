async function sendData() {

    let countElement = document.getElementById("roomsCount");

    let points = [];

    for(let i = 0; i < countElement.value; i++) {
        let pointObj = {
            x:parseInt(document.getElementsByClassName("inputX")[i].value),
            y:parseInt(document.getElementsByClassName("inputY")[i].value)
        }

        points.push(pointObj);
    }

    let response = await fetch("http://localhost:8080/api/rooms/validateRoom", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(points)
    });

    if(response.status === 400) {
        let json = await response.json();
        errorDisplay(json.error);
    }else if(response.status === 200) {
        window.location.replace("http://localhost:8080/rooms");
    }
}

function errorDisplay(message) {
    let coordinatesBlock = document.getElementById("point-list");
    while(coordinatesBlock.firstChild) {
        coordinatesBlock.removeChild(coordinatesBlock.firstChild);
    }

    let blockError = document.createElement('div');
    blockError.innerText = message;
    blockError.style.color = 'red';
    blockError.style.fontWeight = 'bold';

    coordinatesBlock.appendChild(blockError)

}


function createForm() {
    let countElement = document.getElementById("roomsCount");

    let coordinatesBlock = document.getElementById("point-list");
    while(coordinatesBlock.firstChild) {
        coordinatesBlock.removeChild(coordinatesBlock.firstChild);
    }

    for(let i = 0; i < countElement.value; i++) {
        let node = document.createElement("div");
        let inputX = document.createElement("input");
        let inputY = document.createElement("input");
        let label = document.createElement("label");

        label.innerText = "enter x".concat(i).concat(" and y").concat(i).concat(" coordinates");
        inputX.placeholder = "enter X coordinate";
        inputY.placeholder = "enter Y coordinate";
        inputX.className = "inputX";
        inputY.className = "inputY";
        node.className = "field";
        node.id = "field";

        node.appendChild(label);
        node.appendChild(inputX);
        node.appendChild(inputY);
        coordinatesBlock.appendChild(node);
    }

    let buttonSend = document.createElement("button");
    buttonSend.className = "ui button primary";
    buttonSend.innerText = "Send";
    buttonSend.addEventListener('click', sendData);
    buttonSend.type = "button";
    coordinatesBlock.appendChild(buttonSend);
}


/*
.then(response => response.json())
        .then((data) => {
            console.log(data);
        })
 */