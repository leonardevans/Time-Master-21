// Neven 25.05.21



// LANGUAGE TRANSLATION

var language = {

	de: {
		// CREATE REPORT
		firstHeader_Report: "MA-BERICHT ERSTELLEN",

		// CHOOSE EMPLOYEE OR STORE REPORT
		secondHeader_Report: "AUSWAHL TREFFEN",

		// Option Dropdown
		choose_opt: "Bericht-Art wählen",
		one_Emp: "Einzelner Mitarbeiterbericht",
		all_Emp: "Alle Mitarbeiter",

		// "day Report"
		day_report: "Tagesbericht",
		
		// "Month Report"
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
		
		// CREATE REPORT
		welcomeHeader.textContent = language.de.firstHeader_Report;

		// CHOOSE EMPLOYEE OR STORE REPORT
		welcomeHeader2.textContent = language.de.secondHeader_Report;

		// Option Dropdown
		selectOption.textContent = language.de.choose_opt;
		optionEmp.textContent = language.de.one_Emp;
		optionRep.textContent = language.de.all_Emp;
		
		// Day Report
		dayReport.textContent = language.de.day_report;

		// Month Report
		monthReport.textContent = language.de.month_report;

		// Year Report
		yearReport.textContent = language.de.year_report;

		// CREATE REPORT
		createEmpStoreReportBtn.textContent = language.de.create_report_btn;

		// BACK TO DASHBOARD
		backToDashBtn.textContent = language.de.back_dash_btn;

	}
}

// CHANGE LANGUAGE RELOAD ONCLICK

function langReload() {
	setTimeout(function() { history.go(0); }, 200);
}