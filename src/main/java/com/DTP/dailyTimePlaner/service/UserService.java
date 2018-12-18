package com.DTP.dailyTimePlaner.service;

import com.DTP.dailyTimePlaner.domain.UserType;
import com.DTP.dailyTimePlaner.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserType user =  userRepo.findByEmail(s);
        if(user==null){
            throw new UsernameNotFoundException("wrong userName");
        }
        user.setActive(false);
        return user;
    }
}
