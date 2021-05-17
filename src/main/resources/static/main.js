const actionTableElement = `<div class="actions">
                                <button class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#editModal" onclick="loadCharAttributes(event)">Edit
                                </button>
                                <button class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal" onclick="loadCharAttributes(event)">Delete
                                </button>
                            </div>`;
const lotrInput = `<label id="extraLabel">Lieblingstabak</label>
                   <input type="text" name="favoriteTobacco" class="form-control" required>`

const starWarsInput = `<label id="extraLabel">Raumschiff</label>
                   <input type="text" name="spaceship" class="form-control" required>`;


const APIURL = "http://localhost:8080/character/";

function loadChars() {
    fetch(APIURL)
        .then(response => response.json())
        .then(data => renderTable(data));
}

function renderTable(json) {
    let rowCounter = 1;
    let tableBody = document.querySelector('tbody')
    tableBody.innerHTML = "";
    json.forEach(element => {
        let row = tableBody.insertRow(-1);
        row.setAttribute("uuid", element.id);
        row.setAttribute("type", element['@type']);
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

function loadCharAttributes(event) {
    let tdElements = event.target.parentNode.parentNode.parentNode.childNodes; // Contains all character attributs
    for (let element of document.querySelectorAll(".vorname-edit")) {
        element.value = tdElements[1].innerHTML;
    }
    for (let element of document.querySelectorAll(".nachname-edit")) {
        element.value = tdElements[2].innerHTML;
    }
    for (let element of document.querySelectorAll(".alter-edit")) {
        element.value = tdElements[3].innerHTML;
    }
    for (let element of document.querySelectorAll(".id-edit")) {
        element.value = tdElements[0].parentNode.getAttribute("uuid");
    }
}

async function editChar() {
    const formElement = document.querySelector('#editCharForm');
    const formData = createObjFromForm(formElement);
    const type = document.querySelector(`tr[uuid="${formData.id}"]`).getAttribute('type');
    formData["@type"] = type;
    const json = JSON.stringify(formData);

    console.log(json);
    const response = await fetch(APIURL, {
        method: "Put",
        headers: {
            "Content-Type": "application/json",
        },
        body: json
    });
    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    } else {
        console.log("Character edited!");
        loadChars();
        document.getElementById("closeEdit").click();
        formElement.reset();
    }
}

async function deleteChar(){
    const formElement = document.querySelector('#deleteCharForm');
    const formData = createObjFromForm(formElement);
    const id = formData.id;
    const response = await fetch(APIURL + id, {
        method: "Delete",
        headers: {
            "Content-Type": "application/json",
        },
    });
    if (!response.ok) {
        const errorMessage = await response.text();
        throw  new Error(errorMessage);
    } else {
        console.log("Character deleted");
        loadChars();
        document.getElementById("closeDelete").click();
        formElement.reset();
    }
}

function changeExtraAttribute() {
    const formElement = document.querySelector('#addCharForm');
    const form = createObjFromForm(formElement);
    const extraInput = document.getElementById("extraAttr");
    if (form["@type"] === "StarWars") {
        extraInput.innerHTML = starWarsInput;
    } else if (form["@type"] === "LotR") {
        extraInput.innerHTML = lotrInput;
    }
}

function createObjFromForm(formElement) {
    const formData = new FormData(formElement);
    return Object.fromEntries(formData.entries());
}