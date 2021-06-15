package com.timemaster.controller;


import com.timemaster.model.User;
import com.timemaster.model.WorkHours;
import com.timemaster.repo.UserRepo;
import com.timemaster.repo.WorkHoursRepo;
import com.timemaster.security.AuthUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

@RestController
public class AuthRestController {
    Logger logger = LoggerFactory.getLogger(AuthRestController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthUtil authUtil;

    @Autowired
    WorkHoursRepo workHoursRepo;

    @PostMapping("/api/auth/login")
    @ResponseBody
    public boolean login(@RequestParam("username") String username,
                             @RequestParam("password") String password) {
        try {
            UsernamePasswordAuthenticationToken authReq
                    = new UsernamePasswordAuthenticationToken(username, password);
            Authentication auth = authenticationManager.authenticate(authReq);
            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(auth);

            //get logged in user
            User user = authUtil.getLoggedInUser();


            //get current date
            long now =System.currentTimeMillis();
            Date date=new java.sql.Date(now);

            //get current time
            Time time = new Time(now);


            //get last hours that has not ended
            WorkHours lastWorkHours = workHoursRepo.findByDateAndEndTimeAndUser(date, null, user).orElse(null);

            if (lastWorkHours != null){
                lastWorkHours.setEndTime(time);

                long id = lastWorkHours.getId();

                //save last work hour
                workHoursRepo.save(lastWorkHours);

                //set Total Time Worked
                lastWorkHours.setTotalTime(workHoursRepo.getTotalTimeHours(id));

                //save Total Time Worked
                workHoursRepo.save(lastWorkHours);
            }

            WorkHours workHours = new WorkHours(date, time, user);
            workHoursRepo.save(workHours);

            logger.info("Logged in as :" + username);
            return true;
        }catch (Exception e){
            logger.info("Failed to log in as :" + username);
            return false;
        }
    }

    @PostMapping("/api/auth/reset-password")
    public String resetPassword(@RequestParam("username") String username,
                         @RequestParam("password") String password) {
        //first check if there's a user wit the provided username
        if (!userRepo.existsByEmail(username)){
            return "no_such_user";
        }

        //get the user
        Optional<User> optionalUser = userRepo.findByEmail(username);
        User user = optionalUser.get();

        //change the password to encoded new password
        user.setPassword(passwordEncoder.encode(user.getName() + user.getSurname()));

        userRepo.save(user);

        return "password_reset_successfully";
    }

    @PostMapping("/api/logout")
    public String processLogout(){
        //get logged in user
        User user = authUtil.getLoggedInUser();

        //get current date
        long now=System.currentTimeMillis();
        Date date=new java.sql.Date(now);

        //get current time
        Time time = new Time(now);


        //get last hours that has not ended
        WorkHours lastWorkHours = workHoursRepo.findByDateAndEndTimeAndUser(date, null, user).orElse(null);

        if (lastWorkHours != null){
            lastWorkHours.setEndTime(time);

            long id = lastWorkHours.getId();

            //save last work hour
            workHoursRepo.save(lastWorkHours);

            lastWorkHours.setTotalTime(workHoursRepo.getTotalTimeHours(id));

            //set Total Time Worked
            workHoursRepo.save(lastWorkHours);

        }

        return "continue_log_out";
    }
}
