// Dara 24.05.21



// LANGUAGE TRANSLATION

var language = {

	de: {
		// "Start / Pause your Work Day"
		firstHeader_Admin: "Starte / Pausiere deinen Arbeitstag",

		// "WORKED TODAY:"
		secondHeader_Admin: "HEUTE GEARBEITET:",

		// ALL CARDS FROM ROW 4
		report_Admin: "BERICHT",
		workingHrs_Admin: "ARBEITSZEITEN",
		empReport_Admin: "MA-BERICHT",
		addEmp_Admin: "MA-HINZUFÜGEN",
		requests_Admin: "ANFRAGEN",
		// BUTTONS OF CARDS FROM ROW 4
		createRepBtn_Admin: "ERSTELLEN",
		workingHrsBtn_Admin: "BEARBEITEN",
		empReportBtn_Admin: "ERSTELLEN",
		addEmpBtn_Admin: "HINZUFÜGEN",
		requestsBtn_Admin: "BEARBEITEN",

		// "YOUR OVERVIEW:"
		overview_Admin: "DEINE ÜBERSICHT:",

		// TABLE LIST
		dateList_Admin: "Datum",
		actHrsList_Admin: "Gearbeitet",
		targHrsList_Admin: "Soll",
		overtimeList_Admin: "Überzeit",
		vacLeftList_Admin: "Ferientage übrig",

		// "SETTINGS"
		footer_Admin: "Einstellungen",

		// ALL CARDS FROM ROW 8
		accSett_Admin: "KONTO-EINSTELLUNGEN",
		// BUTTONS OF CARDS FROM ROW 8
		editAccSett_Admin: "BEARBEITEN",

        // LOGOUT MODAL
        modalHeader_Admin: "Bist Du sicher?",
        modalBody_Admin: "Wenn Du dich ausloggst, stoppt dein Arbeits-Timer sich automatisch. Falls du eine Pause einlegen willst, drücke bitte 'Pause', statt dich auszuloggen",
        modalBtn_Admin: "BIN MIR SICHER"
	}
};

// DEFINE LANGUAGE VIA WINDOW HASH

if(window.location.hash){
	if(window.location.hash === "#de") {
		// "Start / Pause your Work Day"
		welcomeHeader.textContent = language.de.firstHeader_Admin;

		// "WORKED TODAY:"
		workedToday.textContent = language.de.secondHeader_Admin;

		// ALL CARDS FROM ROW 4
		reportCard.textContent = language.de.report_Admin;
		workingHrsCard.textContent = language.de.workingHrs_Admin;
		empReportCard.textContent = language.de.empReport_Admin;
		addEmpCard.textContent = language.de.addEmp_Admin;
		requestsCard.textContent = language.de.requests_Admin;
		// BUTTONS OF CARDS FROM ROW 4
		reportBtn.textContent = language.de.createRepBtn_Admin;
		workingHoursBtn.textContent = language.de.workingHrsBtn_Admin;
		createRepBtn.textContent = language.de.empReportBtn_Admin;
		addEmpBtn.textContent = language.de.addEmpBtn_Admin;
		editReqBtn.textContent = language.de.requestsBtn_Admin;

		// "YOUR OVERVIEW:"
		overview.textContent = language.de.overview_Admin;

		// TABLE LIST
		dateList.textContent = language.de.dateList_Admin;
		actHrsList.textContent = language.de.actHrsList_Admin;
		targetHrsList.textContent = language.de.targHrsList_Admin;
		overtimeList.textContent = language.de.overtimeList_Admin;
		vacLeftList.textContent = language.de.vacLeftList_Admin;

		// "SETTINGS"
		footer.textContent = language.de.footer_Admin;

		// ALL CARDS FROM ROW 8
		accSettCard.textContent = language.de.accSett_Admin;
		// BUTTONS OF CARDS FROM ROW 8
		editAccSettBtn.textContent = language.de.editAccSett_Admin;

        // LOGOUT MODAL
        modalHeader.textContent = language.de.modalHeader_Admin;
        modalBody.textContent = language.de.modalBody_Admin;
        modalBtn.textContent = language.de.modalBtn_Admin;
	}
}

// CHANGE LANGUAGE RELOAD ONCLICK

function langReload(lang) {
	setTimeout(function(){ history.go(0); }, 200);

	//Save user settings
	$.ajax({
		method: "post",
		url: "/api/set-language",
		data: {
			lang: lang
		},
		dataType: "text",
		success: response => {
			console.log(response);
		},
		error: response => {
			console.log(response);
		}
	});
  }