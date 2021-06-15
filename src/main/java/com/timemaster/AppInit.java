package com.timemaster;

import com.timemaster.model.Address;
import com.timemaster.model.DayReport;
import com.timemaster.model.User;
import com.timemaster.model.Vacation;
import com.timemaster.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

@Component
public class AppInit implements CommandLineRunner {
    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;



    @Override
    public void run(String... args) throws Exception {
        if (!userRepo.existsByEmail("admin@timemaster.com")){
            User admin = new User();
            admin.setEmail("admin@timemaster.com");
            admin.setRole("ROLE_ADMIN");
            admin.setName("admin");
            admin.setSurname("surname");
            admin.setAddress(new Address());
            admin.setPassword(passwordEncoder.encode(admin.getName() + admin.getSurname()));
            admin.setDayWorkHours(Time.valueOf("08:00:00"));

            //get current year
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);

            Vacation vacation = new Vacation(25, year, admin);

            DayReport dayReport = new DayReport(Time.valueOf("09:00:23"), admin.getDayWorkHours(), Time.valueOf("01:00:23"), admin);
            dayReport.setDay(Date.valueOf("2021-05-12"));
            DayReport dayReport1 = new DayReport(Time.valueOf("12:00:23"), admin.getDayWorkHours(), Time.valueOf("03:00:23"), admin);
            dayReport1.setDay(Date.valueOf("2021-04-12"));
            DayReport dayReport2 = new DayReport(Time.valueOf("13:00:23"), admin.getDayWorkHours(), Time.valueOf("05:00:23"), admin);
            dayReport2.setDay(Date.valueOf("2021-05-18"));
            DayReport dayReport3 = new DayReport(Time.valueOf("06:00:23"), admin.getDayWorkHours(), Time.valueOf("0:00:00"), admin);
            dayReport3.setDay(Date.valueOf("2021-04-25"));

            admin.getDayReports().add(dayReport);
            admin.getDayReports().add(dayReport1);
            admin.getDayReports().add(dayReport2);
            admin.getDayReports().add(dayReport3);

            admin.getVacations().add(vacation);

            userRepo.save(admin);
        }
    }
}
