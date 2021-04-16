const actionTableElement = `<div class="actions">
                                <button class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#editModal">Edit
                                </button>
                                <button class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">Delete
                                </button>
                            </div>`;
const APIURL = "http://localhost:8080/character/"

function loadChars() {
    let json;
    fetch(APIURL)
        .then(response => response.json())
        .then(data => renderTable(data));
}

function renderTable(json) {
    let rowCounter = 1;
    let tableBody = document.querySelector('tbody')
    tableBody.innerHTML = "";
    console.log(json)
    json.forEach(element => {
        let row = tableBody.insertRow(-1);
        row.setAttribute("uuid", element.id);
        row.insertCell(-1).innerHTML = rowCounter.toString();
        row.insertCell(-1).innerHTML = element['firstname'];
        row.insertCell(-1).innerHTML = element['lastname'];
        row.insertCell(-1).innerHTML = element['age'];
        row.insertCell(-1).innerHTML = actionTableElement;
        rowCounter++;
    });
}

async function addChar() {
    let form = document.querySelector('#addCharForm');
    let formData = new FormData(form);
    let json = JSON.stringify(Object.fromEntries(formData.entries()));
    console.log(json);
    const response = await fetch(APIURL, {
        method: "Post",
        headers: {
            "Content-Type": "application/json",
        },
        body: json
    });
    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    } else {
        console.log("new Character added!");
        loadChars();
        document.getElementById("closeAdd").click();
        form.reset();
    }
}
