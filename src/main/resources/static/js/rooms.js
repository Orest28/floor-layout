addEventListener('load', fillData);

async function fillData() {

    console.log("hello from FILL")

    let element = document.getElementById("content");

    let table = document.createElement('table');
    let thead = document.createElement('thead');

    let th1 = document.createElement('th');
    let th2 = document.createElement('th');

    th1.innerText = "ID";
    th2.innerText = "Actions";

    thead.appendChild(th1);
    thead.appendChild(th2);

    table.appendChild(thead);
    table.className = "ui celled table";

    element.appendChild(table);

    let response = await fetch("http://localhost:8080/api/rooms/getAllRooms", {
        method: 'GET',
    });

    let jsonResponse = await response.json();

    jsonResponse.forEach(element => {

        let tbody = document.createElement("tbody");
        let tr = document.createElement("tr");

        let td1 = document.createElement("td");
        td1.setAttribute("data-label", "id");
        td1.innerText = element.id;

        let td2 = document.createElement("td");
        td2.setAttribute("data-label", "actions");

        let buttonShow = document.createElement("button");
        buttonShow.innerText = "Show";
        buttonShow.className = "ui inverted green button";
        buttonShow.addEventListener("click", function () {
            showRoom(element.id);
        })

        let buttonDelete = document.createElement("button");
        buttonDelete.innerText = "Delete";
        buttonDelete.className = "ui inverted red button";
        buttonDelete.addEventListener("click", function () {
            deleteRoom(element.id);
        })

        let buttonUpdate = document.createElement("button");
        buttonUpdate.innerHTML = "Update";
        buttonUpdate.className = "ui inverted yellow button";
        buttonUpdate.addEventListener("click", function () {
            updateRoom(element.id);
        })

        td2.appendChild(buttonShow);
        td2.appendChild(buttonDelete);
        td2.appendChild(buttonUpdate);

        tr.appendChild(td1);
        tr.appendChild(td2);

        tbody.appendChild(tr);
        table.appendChild(tbody);
    })
}

function showRoom(id) {
    window.location.replace("http://localhost:8080/room/" + id);
}

async function deleteRoom(id) {
    let response = await fetch("http://localhost:8080/api/rooms/delete/" + id, {
        method: 'DELETE'
    });

    if(response.status === 200) {
        window.location.replace("http://localhost:8080/rooms");
    }
}

function updateRoom(id) {
    window.location.replace("http://localhost:8080/room/update/" + id);
}