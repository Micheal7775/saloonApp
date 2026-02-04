package com.saloonBookingSystem.ExceptoinHandler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GolbalExceptoinHandler {


    @ExceptionHandler(UserNotFoundException.class)
    public String userNotfound(UserNotFoundException ex){
        return "user not found";
    }
}
