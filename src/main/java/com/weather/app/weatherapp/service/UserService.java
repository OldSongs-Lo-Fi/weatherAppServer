package com.weather.app.weatherapp.service;

import com.weather.app.weatherapp.DTO.request.UserRegistrationDTO;
import com.weather.app.weatherapp.DTO.request.UserUpdateDTO;
import com.weather.app.weatherapp.entity.User;
import com.weather.app.weatherapp.enumerator.UserRole;
import com.weather.app.weatherapp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;


    public User registration(UserRegistrationDTO userRegistrationDTO) {
        User user = new User(null, userRegistrationDTO.getUsername(),
                userRegistrationDTO.getPassword(),
                userRegistrationDTO.getEmail(),
                true,
                UserRole.USER,
                userRegistrationDTO.getLatitude(),
                userRegistrationDTO.getLongitude());
        return userRepo.save(user);
    }

    public User updateUser(User user, UserUpdateDTO userUpdateDTO) {
        user.setUsername(userUpdateDTO.getUsername());
        user.setLatitude(userUpdateDTO.getLatitude());
        user.setLongitude(userUpdateDTO.getLongitude());
        return userRepo.save(user);
    }

    public User updatePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        return userRepo.save(user);
    }

    public User updateEmail(User user, String email) {
        user.setEmail(email);
        return userRepo.save(user);
    }

    public User banUser(User user) {
        user.setEnabled(false);
        return userRepo.save(user);
    }
}
