async function updateData(countElement, id) {

    let updatedPoints = [];

    for(let i = 0; i < countElement; i++) {

        let updatedPointObj = {
            x:parseInt(document.getElementsByClassName("inputX")[i].value),
            y:parseInt(document.getElementsByClassName("inputY")[i].value)
        }

        updatedPoints.push(updatedPointObj);
    }

    let response = await fetch("http://localhost:8080/api/rooms/update/" + id, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedPoints)
    });

    if(response.status === 400) {
        let json = await response.json();
        errorUpdatedDisplay(json.error);
    }else if(response.status === 200) {
        window.location.replace("http://localhost:8080/rooms");
    }
}

function errorUpdatedDisplay(message) {

    let coordinatesBlock = document.getElementById("point-list");

    if(document.getElementById("error") !== null) document.getElementById("error").remove();

    let blockError = document.createElement('div');
    blockError.innerText = message;
    blockError.style.color = 'red';
    blockError.style.fontWeight = 'bold';
    blockError.id = "error";

    coordinatesBlock.appendChild(blockError)
}