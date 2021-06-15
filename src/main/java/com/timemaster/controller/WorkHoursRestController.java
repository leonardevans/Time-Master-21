package com.timemaster.controller;

import com.timemaster.dto.HoursWorkedResponse;
import com.timemaster.model.DayReport;
import com.timemaster.model.User;
import com.timemaster.model.WorkHours;
import com.timemaster.repo.DayReportRepo;
import com.timemaster.repo.UserRepo;
import com.timemaster.repo.WorkHoursRepo;
import com.timemaster.security.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

@RestController
public class WorkHoursRestController {
    @Autowired
    UserRepo userRepo;

    @Autowired
    AuthUtil authUtil;

    @Autowired
    WorkHoursRepo workHoursRepo;

    @Autowired
    DayReportRepo dayReportRepo;

    @PostMapping("/api/getHoursWorked")
    @ResponseBody
    public Object getDayHoursWorked() {
        //get logged in user
        User user = authUtil.getLoggedInUser();

        if (user == null)
            return "unauthenticated";

        //get current date
        long now = System.currentTimeMillis();
        Date date = new java.sql.Date(now);

        //get current time
        Time time = new Time(now);

        //get last hours that has not ended
        WorkHours lastWorkHours = workHoursRepo.findByDateAndEndTimeAndUser(date, null, user).orElse(null);

        Time totalDayTimeWorked = workHoursRepo.getDayTotalTimeWorked(date, user.getId());

        HoursWorkedResponse hoursWorkedResponse = new HoursWorkedResponse();
        hoursWorkedResponse.setTimeWorked(totalDayTimeWorked);

        //if the user is currently working
        if (lastWorkHours != null) {
            hoursWorkedResponse.setWorking(true);
            Time currentWorkingTime = workHoursRepo.getTimeDifference(time, lastWorkHours.getStartTime());
            hoursWorkedResponse.setCurrentWorkingTime(currentWorkingTime);
            hoursWorkedResponse.setTotalTimeWorked(workHoursRepo.addTime(totalDayTimeWorked, currentWorkingTime));

        } else {
            hoursWorkedResponse.setTotalTimeWorked(totalDayTimeWorked);
            hoursWorkedResponse.setTotalTimeWorked(totalDayTimeWorked);
        }

        //get and set overtime
        Time overtime = null;

        if (hoursWorkedResponse.getTotalTimeWorked() != null){
            //check if user has worked overtime
            if (workHoursRepo.checkTimeGreaterThan(hoursWorkedResponse.getTotalTimeWorked(),  user.getDayWorkHours()) == 1){
                //get and set overtime
                overtime = workHoursRepo.subtractTime(hoursWorkedResponse.getTotalTimeWorked(),  user.getDayWorkHours());
                hoursWorkedResponse.setOvertimeTimeWorked(overtime);
            }
        }

        //get today's report
        Optional<DayReport> optionalDayReport = dayReportRepo.findByDayAndUser(date, user);
        DayReport dayReport = null;
        if (optionalDayReport.isPresent()){
            dayReport = optionalDayReport.get();
            dayReport.setTimeWorked(hoursWorkedResponse.getTotalTimeWorked());
            dayReport.setDayWorkTime(user.getDayWorkHours());
            dayReport.setOverTime(hoursWorkedResponse.getOvertimeTimeWorked());
        }else{
            dayReport = new DayReport(hoursWorkedResponse.getTotalTimeWorked(), user.getDayWorkHours(),  hoursWorkedResponse.getOvertimeTimeWorked(), user);
        }

        dayReport.setDay(date);
        dayReportRepo.save(dayReport);

        return hoursWorkedResponse;
    }

    @PostMapping("/api/startPauseWork")
    @ResponseBody
    public Object startPauseWork(@RequestParam("action") String action) {
        //get logged in user
        User user = authUtil.getLoggedInUser();

        if (user == null)
            return "unauthenticated";

        //get current date
        long now = System.currentTimeMillis();
        Date date = new java.sql.Date(now);

        //get current time
        Time time = new Time(now);

        boolean working = false;

        switch (action) {
            case "start":
                WorkHours workHours = new WorkHours(date, time, user);
                workHoursRepo.save(workHours);
                working = true;
                break;
            case "pause":
                //get last hours that has not ended
                WorkHours lastWorkHours = workHoursRepo.findByDateAndEndTimeAndUser(date, null, user).orElse(null);
                if (lastWorkHours != null) {
                    lastWorkHours.setEndTime(time);

                    long id = lastWorkHours.getId();

                    //save last work hour
                    workHoursRepo.save(lastWorkHours);

                    lastWorkHours.setTotalTime(workHoursRepo.getTotalTimeHours(id));

                    //set Total Time Worked
                    workHoursRepo.save(lastWorkHours);
                }
                working = false;
                break;
        }

        return working;
    }
}
