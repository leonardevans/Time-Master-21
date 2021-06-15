// Neven 25.05.21

// LANGUAGE TRANSLATION

var language = {

	de: {
		// BOOK VACATION
		firstBookHeader: "FERIENANTRAG STELLEN",

		// CHOOSE A START DATE AND END DATE
		secondBookHeader: "WÄHLE EIN START- UND END-DATUM",

		// Starting Date
		start_date: "START-DATUM",
		end_date: "END-DATUM",

		// BUTTON "BOOK"
		book_btn: "ANTRAG STELLEN",

		// "BACK TO DASHBOARD"
		back_dash_btn: "ZURÜCK ZUM DASHBOARD"
	}
}

// DEFINE LANGUAGE VIA WINDOW HASH

if (window.location.hash) {
	if (window.location.hash === "#de") {
		// BOOK VACATION
		welcomeHeader.textContent = language.de.firstBookHeader;

		// CHOOSE A START DATE AND END DATE
		chooseVacDateHeader.textContent = language.de.secondBookHeader;

		// Starting Date
		startDate.textContent = language.de.start_date;
		endDate.textContent = language.de.end_date;

		// BUTTON "BOOK"
		editWorkingHrs.textContent = language.de.book_btn;

		// BACK TO DASHBOARD
		backToDashBtn.textContent = language.de.back_dash_btn;

	}
}

// CHANGE LANGUAGE RELOAD ONCLICK

function langReload() {
	setTimeout(function() { history.go(0); }, 200);
}