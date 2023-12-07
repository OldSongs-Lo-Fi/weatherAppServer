package com.weather.app.weatherapp.DTO.request;

import com.weather.app.weatherapp.enumerator.UserRole;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {

    String username;

    public double latitude;

    public double longitude;

}
