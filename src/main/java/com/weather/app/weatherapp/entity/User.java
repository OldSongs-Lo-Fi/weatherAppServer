package com.weather.app.weatherapp.entity;

import com.weather.app.weatherapp.enumerator.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true,
            nullable = false,
            columnDefinition = "VARCHAR(30)")
    String username;
    @Column(nullable = false,
            columnDefinition = "VARCHAR(40)")
    String password;
    @Column(unique = true,
            nullable = false,
            columnDefinition = "VARCHAR(347)")
    String email;
    boolean isEnabled;

    UserRole userRole;

    //Широта
    @Column(columnDefinition = "DOUBLE")
    public double latitude;

    //Долгота
    @Column(columnDefinition = "DOUBLE")
    public double longitude;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(userRole);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
