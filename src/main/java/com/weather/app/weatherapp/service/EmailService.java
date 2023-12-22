package com.weather.app.weatherapp.service;

import com.weather.app.weatherapp.DTO.request.UserRegistrationDTO;
import com.weather.app.weatherapp.entity.User;
import com.weather.app.weatherapp.exception.WebRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    Map<String, UserRegistrationDTO> accountsToConfirm = new HashMap<>();

    public void sendEmail(UserRegistrationDTO userDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userDTO.getEmail());
        message.setSubject("Confirm mail");

        String activationCode = UUID.randomUUID().toString() + new Date().getTime();
        message.setText("Here's your confirmation URL: \n" +
                "http://localhost:8080/users/registration?email=" + userDTO.getEmail() + "&activationCode=" + activationCode);
        accountsToConfirm.put(activationCode, userDTO);
        javaMailSender.send(message);
    }

    public UserRegistrationDTO confirmUser(String email, String confirmCode){

        if (!accountsToConfirm.containsKey(confirmCode)){
            throw new WebRuntimeException("Wrong link", 404);
        }

        UserRegistrationDTO user = accountsToConfirm.get(confirmCode);

        if (!user.getEmail().equals(email)){
            throw new WebRuntimeException("Wrong link", 404);
        }

        accountsToConfirm.remove(confirmCode);
        return user;
    }
}