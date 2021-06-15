// Neven 25.05.21



// LANGUAGE TRANSLATION

var language = {

	de: {
		// ACCOUNT SETTINGS
		firstHeader: "KONTOEINSTELLUNGEN",

		// EDIT INFORMATION | CHANGE PASSWORD
		secondHeader: "INFORMATIONEN BEARBEITEN | PASSWORT ÄNDERN",

		// SAVE CHANGES
		save_changes: "ÄNDERUNGEN SPEICHERN",

		// Show Password
		show_pw: "Passwort anzeigen",
		
		// CHANGE PASSWORD
		change_pw: "PASSWORT ÄNDERN",

		// "BACK TO DASHBOARD"a
		back_dash_btn: "ZURÜCK ZUM DASHBOARD"
	}
}

// DEFINE LANGUAGE VIA WINDOW HASH

if (window.location.hash) {
	if (window.location.hash === "#de") {
		
		// ACCOUNT SETTINGS
		welcomeHeader.textContent = language.de.firstHeader;

		// EDIT INFORMATION | CHANGE PASSWORD
		welcomeHeader2.textContent = language.de.secondHeader;

		// SAVE CHANGES
		saveAccSettBtn.textContent = language.de.save_changes;

		// Show Password
		showPSWBtn.textContent = language.de.show_pw;

		// CHANGE PASSWORD
		changePSW.textContent = language.de.change_pw;

		// BACK TO DASHBOARD
		backToDashBtn.textContent = language.de.back_dash_btn;

	}
}

// CHANGE LANGUAGE RELOAD ONCLICK

function langReload() {
	setTimeout(function() { history.go(0); }, 200);
}