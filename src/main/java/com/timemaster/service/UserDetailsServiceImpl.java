package com.timemaster.service;

import com.timemaster.repo.UserRepo;
import com.timemaster.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.timemaster.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //we use email since it ius unique
        User user = userRepo.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("No user with email: " + email));
        return new UserDetailsImpl(user);
    }
}
