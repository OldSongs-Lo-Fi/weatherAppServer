package com.weather.app.weatherapp.security;

import com.weather.app.weatherapp.entity.User;
import com.weather.app.weatherapp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username + " try to find!");
        User user = userRepo.findUserByUsername(username).orElseThrow(() -> new RuntimeException("Pizda"));
        System.out.println(user.getPassword() + " his pass.. I found!");
        return user;
    }
}
