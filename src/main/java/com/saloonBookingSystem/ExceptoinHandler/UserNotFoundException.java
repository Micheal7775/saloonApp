package com.saloonBookingSystem.ExceptoinHandler;

public class UserNotFoundException extends  Exception{

    public UserNotFoundException(String message){
        super(message);

    }
}
