package com.weather.app.weatherapp.exception;


import lombok.Getter;

@Getter
public class WebRuntimeException extends RuntimeException{

    private int status;

    public WebRuntimeException(String message, int status) {
        super(message);
        validateStatus(status);
        this.status = status;
    }

    private void validateStatus(int status) {
        if (status < 100 || status > 599) {
            throw new IllegalArgumentException("Status must be in the range of 100 to 599.");
        }
    }
}
