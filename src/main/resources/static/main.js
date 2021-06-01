const actionTableElement = `<div class="actions">
                                <button class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#editModal" onclick="loadCharAttributes(event)">Edit
                                </button>
                                <button class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal" onclick="loadCharAttributes(event)">Delete
                                </button>
                            </div>`;
const lotrInput = `<label id="extraLabel">Lieblingstabak</label>
                   <input type="text" name="favoriteTobacco" class="form-control lotrInput" required>`

const starWarsInput = `<label id="extraLabel">Raumschiff</label>
                   <input type="text" name="spaceship" class="form-control starWarsInput" required>`;


const APIURL = "http://localhost:8080/character/";

function loadChars() {
    fetch(APIURL)
        .then(response => response.json())
        .then(json => renderTable(json));
}

function renderTable(json) {
    let rowCounter = 1;
    let tableBody = document.querySelector('tbody')
    tableBody.innerHTML = "";
    json.forEach(element => {
        let row = tableBody.insertRow(-1);
        row.classList.add("charRow");
        row.setAttribute("uuid", element.id);
        row.setAttribute("type", element['@type']);
        row.insertCell(-1).innerHTML = rowCounter.toString();
        row.cells[0].className = "rowCount";
        row.insertCell(-1).className = "icon";
        row.cells[1].innerHTML = "<img src=/images/" + element['@type'] + ".jpg/>";
        row.insertCell(-1).innerHTML = element['firstname'];
        row.insertCell(-1).innerHTML = element['lastname'];
        row.insertCell(-1).innerHTML = element['age'];
        const actionCell = row.insertCell(-1);
        actionCell.innerHTML = actionTableElement;
        const buttons = actionCell.querySelectorAll("button");
        for (const button of buttons) {
            button.setAttribute('uuid', element.id);
        }
        row.setAttribute("data-bs-toggle", "modal");
        row.setAttribute("data-bs-target", "#detailModal");
        row.addEventListener("click", loadDetails);
        rowCounter++;
    });
}

function loadDetails(event) {
    loadCharAttributes(event);
    console.log(document.getElementsByClassName("form-group extraAttr editExtraAttr")[1]);
    console.log(document.getElementsByClassName("form-group extraAttr editExtraAttr")[1].childNodes);
}

function loadCharAttributes(event) {
    const uuid = event.currentTarget.getAttribute('uuid');
    fetch(APIURL + uuid)
        .then(response => response.json())
        .then(json => {
            for (let element of document.querySelectorAll(".vorname-edit")) {
                element.value = json.firstname;
            }
            for (let element of document.querySelectorAll(".nachname-edit")) {
                element.value = json.lastname;
            }
            for (let element of document.querySelectorAll(".alter-edit")) {
                element.value = json.age;
            }
            for (let element of document.querySelectorAll(".id-edit")) {
                element.value = json.id;
            }
            changeEditExtraAttributes(json);
        });
}

async function addChar(event) {
    event.preventDefault();
    let formElement = document.querySelector('#addCharForm');
    let json = JSON.stringify(createObjFromForm(formElement));
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
        loadChars();
        document.getElementById("closeAdd").click();
        formElement.reset();
        document.getElementById('universeLotR').checked = true;
        changeAddExtraAttributes(createObjFromForm(formElement));
    }
}

function radioButtonChange() {
    const formElement = document.querySelector('#addCharForm');
    const form = createObjFromForm(formElement);
    changeAddExtraAttributes(form);
}

function changeEditExtraAttributes(charModel) {
    const extraInputs = document.querySelectorAll(".editExtraAttr");
    for (const element of extraInputs) {
        if (charModel["@type"] === "StarWars") {
            element.innerHTML = starWarsInput;
            let edit = document.querySelectorAll(".editExtraAttr .starWarsInput")
            if (edit) {
                for (let element of edit) {
                    element.value = charModel["spaceship"]
                }
            }

        } else if (charModel["@type"] === "LotR") {
            element.innerHTML = lotrInput;
            let edit = document.querySelectorAll(".editExtraAttr .lotrInput")
            if (edit) {
                for (let element of edit) {
                    element.value = charModel["favoriteTobacco"]
                }
            }
        }
    }
}

function changeAddExtraAttributes(form) {
    const extraInputs = document.querySelectorAll(".extraAttrAdd");
    for (const element of extraInputs) {
        if (form["@type"] === "StarWars") {
            element.innerHTML = starWarsInput;
        } else if (form["@type"] === "LotR") {
            element.innerHTML = lotrInput;
        }
    }
}

async function editChar(event) {
    event.preventDefault();
    const formElement = document.querySelector('#editCharForm');
    const formData = createObjFromForm(formElement);
    formData["@type"] = document.querySelector(`tr[uuid="${formData.id}"]`).getAttribute('type');
    const json = JSON.stringify(formData);

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
        loadChars();
        document.getElementById("closeEdit").click();
        formElement.reset();
    }
}

async function deleteChar() {
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
        loadChars();
        document.getElementById("closeDelete").click();
        formElement.reset();
    }
}

async function search(event) {
    event.preventDefault();
    const searchTerm = document.getElementById("searchTerm").value;
    const response = await fetch(`${APIURL}search?term=${searchTerm}`);
    if (!response.ok) {
        const errorMessage = await response.text();
        throw  new Error(errorMessage);
    } else {
        const json = await response.json();
        renderTable(json);
    }
}

function createObjFromForm(formElement) {
    const formData = new FormData(formElement);
    return Object.fromEntries(formData.entries());
}
