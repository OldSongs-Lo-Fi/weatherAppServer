package com.weather.app.weatherapp.service;

import com.weather.app.weatherapp.DTO.request.UserRegistrationDTO;
import com.weather.app.weatherapp.DTO.request.UserUpdateDTO;
import com.weather.app.weatherapp.entity.User;
import com.weather.app.weatherapp.enumerator.UserRole;
import com.weather.app.weatherapp.exception.WebRuntimeException;
import com.weather.app.weatherapp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                userRegistrationDTO.getCity(),
                userRegistrationDTO.getCountryCode());
        if (userRepo.existsByEmail(user.getEmail())){
            throw new WebRuntimeException("Email's already been taken", 409);
        }
        if (userRepo.existsByUsername(user.getUsername())){
            throw new WebRuntimeException("The username is already taken", 409);
        }

        return userRepo.save(user);
    }

    public User updateUser(User user, UserUpdateDTO userUpdateDTO) {
        user.setUsername(userUpdateDTO.getUsername());
        user.setCity(userUpdateDTO.getCity());
        user.setCountryCode(userUpdateDTO.getCountryCode());
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
