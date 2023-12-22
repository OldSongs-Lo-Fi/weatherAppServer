package com.weather.app.weatherapp.controller;


import com.weather.app.weatherapp.exception.WebRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(404)
                .body(e.getMessage());
    }

    @ExceptionHandler(WebRuntimeException.class)
    public ResponseEntity<String> handleWebException(WebRuntimeException e){
        return ResponseEntity.status(e.getStatus())
                .body(e.getMessage());
    }
}
