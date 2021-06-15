package com.timemaster.controller;

import com.timemaster.dto.AccountRequest;
import com.timemaster.model.Settings;
import com.timemaster.model.User;
import com.timemaster.model.Vacation;
import com.timemaster.repo.UserRepo;
import com.timemaster.security.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@RestController
public class AccountRestController {
    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthUtil authUtil;

    @PostMapping("/api/add-account")
    @ResponseBody
    public String addAccount( AccountRequest accountRequest){
        //check if email id is taken by another user
        if (userRepo.existsByEmail(accountRequest.getEmail()))
            return "email_exist";

        //create new user
        User user = new User();

        user.setRole(accountRequest.getRole());
        user.setSurname(accountRequest.getSurname());
        user.setEmail(accountRequest.getEmail());
        user.getAddress().setAddress(accountRequest.getAddress());
        user.setName(accountRequest.getName());
        user.getAddress().setState(accountRequest.getState());
        user.setTelNumber(accountRequest.getTelNumber());
        user.getAddress().setZip(accountRequest.getZip());
        user.setDayWorkHours(accountRequest.getDayWorkHours());

        //get current year
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);

        Vacation vacation = new Vacation(25, year, user);

        user.getVacations().add(vacation);

        user.setPassword(passwordEncoder.encode(user.getName() + user.getSurname()));

        userRepo.save(user);

        return "added";
    }

    @PostMapping("/api/get-settings")
    @ResponseBody
    public Settings getUserSettings(){
        //get the logged in user
        User user = authUtil.getLoggedInUser();

        //check if user is null
        if (user == null)
            return null;

        return user.getSettings();
    }

    @PostMapping("/api/update-account")
    @ResponseBody
    public String updateAccount( AccountRequest accountRequest){
        //get the logged in user
        User user = authUtil.getLoggedInUser();

        //check if user is null
        if (user == null)
            return "not_authenticated";

        user.setName(accountRequest.getName());
        user.setSurname(accountRequest.getSurname());
        user.setEmail(accountRequest.getEmail());
        user.getAddress().setAddress(accountRequest.getAddress());
        user.getAddress().setState(accountRequest.getState());
        user.setTelNumber(accountRequest.getTelNumber());
        user.getAddress().setZip(accountRequest.getZip());

        userRepo.save(user);

        return "updated";
    }

    @PostMapping("/api/update-password")
    @ResponseBody
    public String updatePassword(@RequestParam("password") String password){
        //get the logged in user
        User user = authUtil.getLoggedInUser();

        //check if user is null
        if (user == null)
            return "not_authenticated";

        user.setPassword(passwordEncoder.encode(password));

        userRepo.save(user);

        return "updated";
    }

    @PostMapping("/api/set-settings")
    @ResponseBody
    public Settings setUserSettings(@RequestParam("uiMode") String uiMode){
        //get the logged in user
        User user = authUtil.getLoggedInUser();

        //check if user is null
        if (user == null)
            return null;

        if (user.getSettings() == null){
            user.setSettings(new Settings());
        }

        user.getSettings().setUiMode(uiMode);

        return userRepo.save(user).getSettings();
    }

    @PostMapping("/api/set-language")
    @ResponseBody
    public Settings setUserLanguage(@RequestParam("lang") String lang){
        //get the logged in user
        User user = authUtil.getLoggedInUser();

        //check if user is null
        if (user == null)
            return null;

        if (user.getSettings() == null){
            user.setSettings(new Settings());
        }

        user.getSettings().setLanguage(lang);

        return userRepo.save(user).getSettings();
    }
}
