// Dara 24.05.21



// LANGUAGE TRANSLATION

var language = {

	de: {
		// "EDIT WORKING HOURS"
		firstHeader_WrkHrs: "ARBEITSZEITEN BEARBEITEN",

		// "CHOOSE A DATE TO EDIT"
		secondHeader_WrkHrs: "WÄHLE EIN DATUM",

		// BUTTONS
		editBtn_WrkHrs: "BEARBEITEN",
		saveBtn_WrkHrs: "SPEICHERN",
		backToDash_WrkHrs: "ZURÜCK ZUM DASHBOARD"
	}
}

// DEFINE LANGUAGE VIA WINDOW HASH

if(window.location.hash){
	if(window.location.hash === "#de") {
		// "EDIT WORKING HOURS"
		welcomeHeader.textContent = language.de.firstHeader_WrkHrs;

		// "CHOOSE A DATE TO EDIT"
		chooseDateHeader.textContent = language.de.secondHeader_WrkHrs;

		// BUTTONS
		editWorkingHrs.textContent = language.de.editBtn_WrkHrs;
		saveWorkingHrs.textContent = language.de.saveBtn_WrkHrs;
		backToDashBtn.textContent = language.de.backToDash_WrkHrs;
	}
}

// CHANGE LANGUAGE RELOAD ONCLICK

function langReload() {
	setTimeout(function(){ history.go(0); }, 200);
  }