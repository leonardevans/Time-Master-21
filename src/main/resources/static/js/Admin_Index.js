//Dara 18.05.21

	 // GETTING THE POP UP MODAL FOR LOGOUT 

	 document.getElementById('logoutBtn').addEventListener('click', function(){
		 document.querySelector('.modal').style.display = 'flex';
	 });

	 document.querySelector('.modal-close').addEventListener('click', function(){
		 document.querySelector('.modal').style.display = 'none';
	 });

// POP UP CLOSES WHEN PRESSING OUTSIDE OF THE FIELD

window.onclick = function(e){
	if(e.target.className === "modal")
	e.target.style.display = "none";
}

// DARK MODE

/*function darkMode() {
	var element = document.body;
		document.documentElement.classList.toggle('dark-mode');
	}*/

// REMEMBER DARK MODE

let remDarkMode = localStorage.getItem("remDarkMode");
const darkModeToggle = document.querySelector('#themeSwitch');

// check if darkmode enabled 
// if enabled: turn off
// if disabled: turn on

const enableDarkMode = () => {
	// 1. add class 'darkMode' to body
	document.documentElement.classList.add('dark-mode');
	// 2. update darkMode in LocalStorage
	localStorage.setItem('remDarkMode', 'enabled');
};

const disableDarkMode = () => {
	// 1. remove class 'darkMode' from body
	document.documentElement.classList.remove('dark-mode');
	// 2. update darkMode in LocalStorage
	localStorage.setItem('remDarkMode', null);
};

if(remDarkMode === 'enabled') {
	enableDarkMode();
}

darkModeToggle.addEventListener("click", () => {
	remDarkMode = localStorage.getItem("remDarkMode");
	if (remDarkMode !== 'enabled') {
		enableDarkMode();
		console.log(remDarkMode)
	} else {
		disableDarkMode();
		console.log(remDarkMode)
	}
});

// TO INDEX SITE FROM LOGIN

function toIndex(){
	document.location.href="Admin_Index.html";
}

	// TO REPORT SITE FROM DASHBOARD

function toReport(){
	document.location.href="Report.html";
}

// TO WORKING HOURS SITE FROM DASHBOARD

function toWorkingHours(){
	document.location.href="WorkingHours.html";
}

// TO EMPLOYEE REPORT SITE FROM DASHBOARD

function toEmpReport(){
	document.location.href="EmployeeReport.html";
}

// TO ADD EMPLOYEE SITE FROM DASHBOARD

function toAddEmp(){
	document.location.href="AddEmployee.html";
}

// TO REQUESTS SITE FROM DASHBOARD

function toRequests(){
	document.location.href="Requests.html";
}

// TO REQUESTS SITE FROM DASHBOARD

function toAccSettings(){
	document.location.href="AccountSettings.html";
}
	
// BACK TO DASHBOARD FROM ANY SITE

function backToDash(){
	document.location.href="Admin_Index.html";
}

// SHOW PASSWORD TOGGLE

function showPSW() {
	var x = document.getElementById("changePSWInput");
	if (x.type === "password") {
	  x.type = "text";
	} else {
	  x.type = "password";
	}
}

// GET CURRENT DATE

n =  new Date();
y = n.getFullYear();
m = n.getMonth() + 1;
d = n.getDate();
document.getElementById("currDate").innerHTML = d + "." + m + "." + y;


	 
	


	