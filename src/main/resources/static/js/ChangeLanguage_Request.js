// Neven 25.05.21

// LANGUAGE TRANSLATION

var language = {

	de: {

		// REQUESTS
		firstReqHeader: "ÄNTRÄGE",

		// CHOOSE A START DATE AND END DATE
		secondReqHeader: "ANTRÄGE ANNEHMEN ODER ABLEHNEN",

		// BACK TO DASHBOARD
		back_dash_btn: "ZURÜCK ZUM DASHBOARD"
	}
}

// DEFINE LANGUAGE VIA WINDOW HASH

if (window.location.hash) {
	if (window.location.hash === "#de") {

		// REQUESTS
		reqHeader.textContent = language.de.firstReqHeader;

		// CHOOSE A START DATE AND END DATE
		reqHeader2.textContent = language.de.secondReqHeader;

		// "BACK TO DASHBOARD"
		backToDashBtn.textContent = language.de.back_dash_btn;

	}
}

// CHANGE LANGUAGE RELOAD ONCLICK

function langReload() {
	setTimeout(function() { history.go(0); }, 200);
}