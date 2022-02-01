package com.timemaster.controller;

import com.timemaster.model.*;
import com.timemaster.repo.*;
import com.timemaster.security.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    AuthUtil authUtil;

    @Autowired
    VacationRepo vacationRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    WorkHoursRepo workHoursRepo;

    @Autowired
    DayReportRepo dayReportRepo;

    @Autowired
    VacationRequestRepo vacationRequestRepo;

    @GetMapping("/")
    public String showIndexPage( Model model){
        //get the logged in user
        User user = authUtil.getLoggedInUser();

        //check if user is null
        if (user == null)
            return "redirect:/login";

        model.addAttribute("user", user);

        //get current year
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);

        Optional<Vacation> optionalVacation = vacationRepo.findByUserAndYear(user, year);
        model.addAttribute("vacation", optionalVacation.get());

        //get current date
        long now = System.currentTimeMillis();
        Date date = new java.sql.Date(now);

        List<WorkHours> workHours = workHoursRepo.findAllByDate(date);

        Optional<DayReport> optionalDayReport = dayReportRepo.findByDayAndUser(date, user);
        DayReport dayReport;
        if (optionalDayReport.isPresent()){
            dayReport = optionalDayReport.get();
        }else{
            dayReport = null;
        }

        model.addAttribute("dayReport", dayReport);
        model.addAttribute("workHours", workHours);

        return "index";
    }

    @GetMapping("/login")
    public String showLoginPage(){
        return "login";
    }

    @GetMapping("/account-settings")
    public String showAccountSettingsPage(Model model)
    {
        //get the logged in user
        User user = authUtil.getLoggedInUser();

        //check if user is null
        if (user == null)
            return "redirect:/login";

        model.addAttribute("user", user);
        return "AccountSettings";
    }

    @GetMapping("/AddEmployee")
    public String showAddEmployeePage(Model model){
        User user = new User();
        model.addAttribute("user", new User());
        return "AddEmployee";
    }

    @GetMapping("/WorkingHours")
    public String showWorkingHours(Model model){
        return "WorkingHours";
    }

    @GetMapping("/Report")
    public String showReport(Model model){
        return "Report";
    }

    @GetMapping("/EmployeeReport")
    public String showEmployeeReport(Model model){
        return "EmployeeReport";
    }

    @GetMapping("/Requests")
    public String showRequests(Model model){
        //get current date
        long now = System.currentTimeMillis();
        Date date = new java.sql.Date(now);
        System.out.println(date);

        List<VacationRequest> vacationRequests = vacationRequestRepo.findAllByStartDateGreaterThanEqual(date);
        System.out.println(vacationRequests.size());

        model.addAttribute("vacationRequests", vacationRequests);

        return "Requests";
    }

    @GetMapping("/BookVacation")
    public String bookVacation(Model model){
        return "BookVacation";
    }
}
