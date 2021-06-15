//Dara 18.05.21

//when the page loads
window.addEventListener("DOMContentLoaded", () => {

    //Get user settings
    $.ajax({
        method: "post",
        url: "/api/get-settings",
        dataType: "text",
        success: response => {
            let settings = JSON.parse(response);
            if (settings.uiMode == "dark-mode") {
                document.documentElement.classList.add('dark-mode');
            }

            if (settings.language == "DEUTSCH") {
                window.location.hash = "#de"
            }
        },
        error: response => {
            console.log(response);
        }
    });
})



// GETTING THE POP UP MODAL FOR LOGOUT

document.getElementById('logoutBtn').addEventListener('click', function () {
    document.querySelector('.modal').style.display = 'flex';
});

document.querySelector('.modal-close').addEventListener('click', function () {
    document.querySelector('.modal').style.display = 'none';
});

// POP UP CLOSES WHEN PRESSING OUTSIDE OF THE FIELD

window.onclick = function (e) {
    if (e.target.className === "modal")
        e.target.style.display = "none";
}

// DARK MODE

function darkMode() {
    var element = document.body;
    document.documentElement.classList.toggle('dark-mode');

    let uiMode = '';
    if (document.documentElement.classList.contains("dark-mode")) {
        uiMode = "dark-mode";
    }

    //Save user settings
    $.ajax({
        method: "post",
        url: "/api/set-settings",
        data: {
            uiMode: uiMode
        },
        dataType: "text",
        success: response => {

            if (response.uiMode == "dark-mode") {
                document.documentElement.classList.add('dark-mode');
            }
        },
        error: response => {
            console.log(response);
        }
    });
}


// TO REPORT SITE FROM DASHBOARD

function toReport() {
    document.location.href = "/Report";
}

// TO REPORT SITE FROM DASHBOARD

function toBookVacation() {
    document.location.href = "/BookVacation";
}

// TO WORKING HOURS SITE FROM DASHBOARD

function toWorkingHours() {
    document.location.href = "/WorkingHours";
}

// TO EMPLOYEE REPORT SITE FROM DASHBOARD

function toEmpReport() {
    document.location.href = "/EmployeeReport";
}

// TO ADD EMPLOYEE SITE FROM DASHBOARD

function toAddEmp() {
    document.location.href = "/AddEmployee";
}

// TO REQUESTS SITE FROM DASHBOARD

function toRequests() {
    document.location.href = "/Requests";
}

// TO REQUESTS SITE FROM DASHBOARD

function toAccSettings() {
    document.location.href = "/account-settings";
}

// BACK TO DASHBOARD FROM ANY SITE

function backToDash() {
    document.location.href = "/";
}

	 
	


	