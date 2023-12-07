package com.weather.app.weatherapp.controller;


import com.weather.app.weatherapp.DTO.request.UserRegistrationDTO;
import com.weather.app.weatherapp.DTO.request.UserUpdateDTO;
import com.weather.app.weatherapp.entity.User;
import com.weather.app.weatherapp.service.UserService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    UserService userService;

    @GetMapping("/")
    public ResponseEntity<User> getCurrentUser(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(user);
    }

    @PostMapping("/registration")
    public ResponseEntity<User> registration(@RequestBody UserRegistrationDTO userRegistrationDTO){
        System.out.println("registration");
        return ResponseEntity.status(201).body(userService.registration(userRegistrationDTO));
    }

    @PutMapping("/")
    public ResponseEntity<User> updateUser(@AuthenticationPrincipal User user, @RequestBody UserUpdateDTO userUpdateDTO){
        return ResponseEntity.status(200)
                .body(userService.updateUser(user, userUpdateDTO));
    }

    @PatchMapping("/changePassword")
    public ResponseEntity<User> updatePassword(@AuthenticationPrincipal User user, @RequestParam("password") String newPassword){
        return ResponseEntity.status(200)
                .body(userService.updatePassword(user, newPassword));
    }

    @PatchMapping("/changeEmail")
    public ResponseEntity<User> updateEmail(@AuthenticationPrincipal User user, @RequestParam("email") String email){
        return ResponseEntity.status(200)
                .body(userService.updateEmail(user, email));
    }
    @PatchMapping("/ban")
    public ResponseEntity<User> banUser(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200)
                .body(userService.banUser(user));
    }
}
