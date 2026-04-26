//Checkbox & Radio functionality start
const checkboxes = document.getElementsByClassName('checkbox-enable');

for (let i = 0; i < checkboxes.length; i++) {
    checkboxes[i].addEventListener('change', function () {
        const inputIds = this.dataset.toggle.split(',');
        inputIds.forEach(inputId => {
            const input = document.getElementById(inputId);
            if (this.dataset.action === 'enable') {
                if (this.checked) {
                    input.disabled = false;
                } else {
                    input.disabled = true;
                }
            } else {
                if (this.checked) {
                    input.disabled = true;
                } else {
                    input.disabled = false;
                }
            }
        });
    });
}

const radioboxes = document.getElementsByClassName('radio-toggle');

for (let i = 0; i < radioboxes.length; i++) {
    radioboxes[i].addEventListener('change', function () {
        const inputIds = this.dataset.toggle.split(',');
        inputIds.forEach(inputId => {
            const inputField = document.getElementById(inputId);
            if (this.dataset.action === 'enable') {
                if (this.checked) {
                    inputField.disabled = false;
                } else {
                    inputField.disabled = true;
                }
            } else {
                if (this.checked) {
                    inputField.disabled = true;
                } else {
                    inputField.disabled = false;
                }
            }
        });
    });
}
//Checkbox & Radio functionality end

//Default select values start
function selectDefault(selectIds){
    let selects = [];
    selectIds.forEach(selectId => {
        selects.push(document.getElementById(selectId));
    });
    selects.forEach(select => {
        for(let i = 0; i < select.length; i++){
            if(select.options[i].value === select.dataset.default){
                select.selectedIndex = i;
                if (select.getAttribute("onchange")) {
                    select.onchange();
                }
                break;
            }
        }
    });
}

function selectClassDefault(selectClasses) {
    let selects;
    selectClasses.forEach(selectClass => {
        selects = document.getElementsByClassName(selectClass);
        for (let i = 0; i < selects.length; i++) {
            for (let j = 0; j < selects[i].length; j++) {
                if (selects[i].options[j].value === selects[i].dataset.default){
                    selects[i].selectedIndex = j;
                    if (selects[i].getAttribute("onchange")) {
                        selects[i].onchange();
                    }
                    break;
                }
            }
        }
    });
}

function checkDefault(checkIds) {
    let checks = [];
    checkIds.forEach(checkId => {
        checks.push(document.getElementById(checkId));
    });
    checks.forEach(check => {
        if (check.dataset.check) {
            check.checked = true;
            const event = new Event('change');
            check.dispatchEvent(event);
        }
    });
}

function radioDefault(radioIds) {
    let radios = [];
    radioIds.forEach(radioId => {
        radios.push(document.getElementById(radioId));
    });
    radios.forEach(radio => {
        if (((radio.value === '1' || radio.value === 'true') && radio.dataset.radio === 'true') ||
            ((radio.value === '0' || radio.value === 'false') && radio.dataset.radio === 'false')) {
            radio.checked = true;
            const event = new Event('change');
            radio.dispatchEvent(event);
        }
    });
}
//Default select values end

//Delete prompt start
let deleteForm = document.getElementById("delete-form");
if (deleteForm) {
    deleteForm.addEventListener('submit', function(e){
        e.preventDefault();
        if (confirm("Are you sure you want to delete the record?")) {
            deleteForm.submit();
        }
    });
}

let deleteForms = document.getElementsByClassName("delete-forms");
if (deleteForms) {
    for (let i = 0; i < deleteForms.length; i++) {
        deleteForms[i].addEventListener('submit', function (e) {
            e.preventDefault();
            if (confirm("Are you sure you want to delete the record?")) {
                deleteForms[i].submit();
            }
        });
    }
}
//Delete prompt end

//Assigned partner functionality start
function displayPartnerOptions(selectIds, index) {
    let selects = [];
    selectIds.forEach(selectId => {
        document.getElementById(selectId).options[index].selected = true;
    });
}
//Assigned partner functionality end