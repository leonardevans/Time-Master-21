// Dara 24.05.21



// LANGUAGE TRANSLATION

var language = {

	de: {
		// "EDIT WORKING HOURS"
		firstHeader_AddEmp: "MA HINZUFÜGEN",

		// "TYPE IN INFORMATION"
		secondHeader_AddEmp: "INFORMATIONEN EINTIPPEN",

		// CHOOSE ROLE
		chooseRole_AddEmp: "Funktion auswählen",
		empRole_AddEmp: "Mitarbeiter",
		adminRole_AddEmp: "Admin",

        // BUTTONS
        createEmpBtn_AddEmp: "MA ERSTELLEN",
		backToDash_AddEmp: "ZURÜCK ZUM DASHBOARD",

	}
}

// DEFINE LANGUAGE VIA WINDOW HASH

if(window.location.hash){
	if(window.location.hash === "#de") {
		// "Start / Pause your Work Day"
		addEmpHeader.textContent = language.de.firstHeader_AddEmp;

		// "TYPE IN INFORMATION"
		addEmpHeader2.textContent = language.de.secondHeader_AddEmp;

		// CHOOSE ROLE
		chooseRole.textContent = language.de.chooseRole_AddEmp;
		employeeRole.textContent = language.de.empRole_AddEmp;
        adminRole.textContent = language.de.adminRole_AddEmp;

        // BUTTONS
        createEmpStoreReportBtn.textContent = language.de.createEmpBtn_AddEmp;
		backToDashBtn.textContent = language.de.backToDash_AddEmp;
	}
}

// CHANGE LANGUAGE RELOAD ONCLICK

function langReload() {
	setTimeout(function(){ history.go(0); }, 200);
  }
  