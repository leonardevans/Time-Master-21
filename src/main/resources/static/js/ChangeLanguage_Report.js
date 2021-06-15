// Neven 25.05.21



// LANGUAGE TRANSLATION

var language = {

	de: {
		// "CREATE WORK HOURS REPORT"
		firstHeader_Report: "ARBEITSBERICHT ERSTELLEN",

		// "CHOOSE A CATEGORY:"
		secondHeader_Report: "KATEGORIE AUSWÄHLEN:",

		// Day Report
		day_report: "Tagesbericht",
		
		// Month Report
		month_report: "Monatsbericht",

		// "Year Report"
		year_report: "Jahresbericht",

		// CREATE REPORT
		create_report_btn: "ERSTELLE ARBEITSBERICHT",

		// "BACK TO DASHBOARD"
		back_dash_btn: "ZURÜCK ZUM DASHBOARD"
	}
}

// DEFINE LANGUAGE VIA WINDOW HASH

if (window.location.hash) {
	if (window.location.hash === "#de") {
		// "CREATE WORK HOURS REPORT"
		welcomeHeader.textContent = language.de.firstHeader_Report;

		// "CHOOSE A CATEGORY:"
		welcomeHeader2.textContent = language.de.secondHeader_Report;

		// Day Report
		dayReport.textContent = language.de.day_report;

		// Month Report
		monthReport.textContent = language.de.month_report;

		// "Year Report
		yearReport.textContent = language.de.year_report;

		// CREATE REPORT
		reportBtn2.textContent = language.de.create_report_btn;

		// "BACK TO DASHBOARD"
		backToDashBtn.textContent = language.de.back_dash_btn;

	}
}

// CHANGE LANGUAGE RELOAD ONCLICK

function langReload() {
	setTimeout(function() { history.go(0); }, 200);
}