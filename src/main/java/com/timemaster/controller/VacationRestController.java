package com.timemaster.controller;

import com.timemaster.model.User;
import com.timemaster.model.Vacation;
import com.timemaster.model.VacationRequest;
import com.timemaster.repo.UserRepo;
import com.timemaster.repo.VacationRepo;
import com.timemaster.repo.VacationRequestRepo;
import com.timemaster.security.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Calendar;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
public class VacationRestController {
    @Autowired
    AuthUtil authUtil;

    @Autowired
    VacationRepo vacationRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    VacationRequestRepo vacationRequestRepo;

    @ResponseBody
    @PostMapping("/api/bookVacation")
    public Object bookVacation(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate){
        //get the logged in user
        User user = authUtil.getLoggedInUser();
        //check if user is null
        if (user == null)
            return "unauthenticated";

        //get current date
        long now = System.currentTimeMillis();
        Date bookDate = new java.sql.Date(now);

        long days = getDifferenceDays(startDate, endDate);

        int weeks = (int) Math.floor(days/7);

        int totalWeekDays = (int) (days - (weeks * 2));

        VacationRequest vacationRequest = new VacationRequest(bookDate, startDate, endDate, user);
        vacationRequest.setDuration(totalWeekDays);

        vacationRequestRepo.save(vacationRequest);

        return "booked";
    }

    @ResponseBody
    @PostMapping("/api/update_vacation_request")
    public Object update_vacation_request(@RequestParam("requestId") long requestId){
        Optional<VacationRequest> optionalVacationRequest = vacationRequestRepo.findById(requestId);

        VacationRequest vacationRequest = optionalVacationRequest.orElse(null);

        if (vacationRequest == null){
            return "not_found";
        }

        //get current year
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);

        Optional<Vacation> optionalVacation = vacationRepo.findByUserAndYear(vacationRequest.getUser(), year);
        Vacation vacation = optionalVacation.orElse(new Vacation(25, year, vacationRequest.getUser()));

        if (vacationRequest.isAccepted()){
            vacationRequest.setAccepted(false);
            vacation.setDays(vacation.getDays() + vacationRequest.getDuration());

            vacationRepo.save(vacation);
            vacationRequestRepo.save(vacationRequest);
            return false;
        }

        if(vacation.getDays() >= vacationRequest.getDuration()){
            vacationRequest.setAccepted(true);
            vacation.setDays(vacation.getDays() - vacationRequest.getDuration());

            vacationRepo.save(vacation);
            vacationRequestRepo.save(vacationRequest);
            return true;
        }else{
            return "The length of this vacation request exceeds the available vacation";
        }
    }

    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}
