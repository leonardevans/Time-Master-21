package com.timemaster.controller;

import com.timemaster.model.DayReport;
import com.timemaster.model.User;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@RestController
public class ReportController {
    @Autowired
    DayReportRepo dayReportRepo;

    @Autowired
    AuthUtil authUtil;

    @Autowired
    UserRepo userRepo;

    @Autowired
    WorkHoursRepo workHoursRepo;

    @PostMapping("/api/getUserDayReport")
    @ResponseBody
    public Object createUserReport(@RequestParam("type") String type){
        //get logged in user
        User user = authUtil.getLoggedInUser();

        if (user == null)
            return "unauthenticated";

        //get current date
        long now = System.currentTimeMillis();
        Date date = new java.sql.Date(now);

        //get current Month
        Calendar calendar = Calendar.getInstance();
        //increment month by 1 since months starts counting from 0
        int month = calendar.get(Calendar.MONTH) + 1;

        //get current Year
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);

        String yearString = year + "-";

        String monthString = yearString + month + "-";
        if (month <= 9 ){
            monthString = yearString + "0" + month + "-";
        }
        else
        {
            monthString = yearString + month + "-";
        }


        List<DayReport> dayReports = new ArrayList<>();

        if (type.equals("DayReport")){
            dayReports.add(dayReportRepo.findByDayAndUser(date, user).get());
        }else if (type.equals("monthReport")){
            dayReports = dayReportRepo.findByMonthAndUser( monthString, user.getId());
        }
        else if (type.equals("yearReport")){
            dayReports = dayReportRepo.findByMonthAndUser(yearString, user.getId());
        }

        return dayReports;

    }

    @PostMapping("/api/getEmployeeReport")
    @ResponseBody
    public Object createEmployeeReport(@RequestParam("empEmail") String empEmail, @RequestParam("employee-store-selection") String selection, @RequestParam("reportCategory") String reportCategory)
    {
        //get current date
        long now = System.currentTimeMillis();
        Date date = new java.sql.Date(now);

        //get current Month
        Calendar calendar = Calendar.getInstance();
        //increment month by 1 since months starts counting from 0
        int month = calendar.get(Calendar.MONTH) + 1;

        //get current Year
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);

        String yearString = year + "-";

        String monthString = yearString + month + "-";
        if (month <= 9 ){
            monthString = yearString + "0" + month + "-";
        }
        else
        {
            monthString = yearString + month + "-";
        }


        List<DayReport> dayReports = new ArrayList<>();

        User employee = null;

        if (selection.equals("employeeReport")){
            //get employee report
            Optional<User> optionalEmployee =  userRepo.findByEmail(empEmail);
            if ( optionalEmployee.isEmpty()){
                return "employee_not_exist";
            }else{
                employee = optionalEmployee.get();


                if (reportCategory.equals("DayReport")){
                    dayReports.add(dayReportRepo.findByDayAndUser(date, employee).get());
                }else if (reportCategory.equals("monthReport")){
                    dayReports = dayReportRepo.findByMonthAndUser( monthString, employee.getId());
                }
                else if (reportCategory.equals("yearReport")){
                    dayReports = dayReportRepo.findByMonthAndUser(yearString, employee.getId());
                }
            }
        }else if(selection.equals("storeReport")){
            //get store report
            if (reportCategory.equals("DayReport")){
                dayReports = dayReportRepo.findByDay(date);
            }else if (reportCategory.equals("monthReport")){
                dayReports = dayReportRepo.findByMonth( monthString);
            }
            else if (reportCategory.equals("yearReport")){
                dayReports = dayReportRepo.findByYear(yearString);
            }
        }

        return dayReports;

    }

    @PostMapping("/api/getUserDayReportToEdit")
    @ResponseBody
    public Object getUserDayReportToEdit(@RequestParam("workingDayDate") Date workingDayDate)
    {
        //get logged in user
        User user = authUtil.getLoggedInUser();

        if (user == null)
            return "unauthenticated";

        return dayReportRepo.findByDayAndUser(workingDayDate, user).orElse(null);
    }

    @PostMapping("/api/updateUserDayReport")
    @ResponseBody
    public Object updateUserDayReport(@RequestParam("id") long id, @RequestParam("timeWorked")Time timeWorked, @RequestParam("dayWorkTime")Time dayWorkTime)
    {
        //get logged in user
        User user = authUtil.getLoggedInUser();

        if (user == null)
            return "unauthenticated";

        DayReport dayReport = dayReportRepo.findById(id).orElse( null);

        if (dayReport == null){
            return "day_not_found";
        }

        dayReport.setTimeWorked(timeWorked);
        dayReport.setDayWorkTime(dayWorkTime);

        //check if user has worked overtime
        if (workHoursRepo.checkTimeGreaterThan(timeWorked, dayWorkTime) == 1){
            //get and set overtime
            Time overtime = workHoursRepo.subtractTime(timeWorked, dayWorkTime);
            dayReport.setOverTime(overtime);
        }
        else
        {
            dayReport.setOverTime(Time.valueOf("00:00:00"));
        }



        return dayReportRepo.save(dayReport);
    }
}
