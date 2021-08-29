package com.example.demo.exceptions;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }


}
