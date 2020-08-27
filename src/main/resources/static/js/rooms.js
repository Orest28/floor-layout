addEventListener('load', fillData);

async function fillData() {

    console.log("hello from FILL")

    let element = document.getElementById("content");

    let table = document.createElement('table');
    let thead = document.createElement('thead');

    let th1 = document.createElement('th');
    let th2 = document.createElement('th');
    let th3 = document.createElement('th');

    th1.innerText = "ID";
    th2.innerText = "Room`s name";
    th3.innerText = "Actions";

    thead.appendChild(th1);
    thead.appendChild(th2);
    thead.appendChild(th3);

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
        td2.setAttribute("data-label", "name");
        td2.innerText = element.name;

        let td3 = document.createElement("td");
        td3.setAttribute("data-label", "actions");

        let buttonShow = document.createElement("button");
        buttonShow.innerText = "Show";
        buttonShow.className = "ui inverted green button";
        buttonShow.addEventListener("click", function () {
            showRoom(element.id);
        })

        let buttonDelete = document.createElement("button");
        buttonDelete.innerText = "Delete";
        buttonDelete.className = "ui inverted red button";

        td3.appendChild(buttonShow);
        td3.appendChild(buttonDelete);

        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);

        tbody.appendChild(tr);
        table.appendChild(tbody);
    })
}

function showRoom(id) {
    window.location.replace("http://localhost:8080/room/" + id);
}