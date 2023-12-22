package com.weather.app.weatherapp.DTO.request;

import com.weather.app.weatherapp.enumerator.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegistrationDTO {

    String username;
    String password;
    String email;


    //Широта
    public String city;

    //Долгота
    public String countryCode;
}
