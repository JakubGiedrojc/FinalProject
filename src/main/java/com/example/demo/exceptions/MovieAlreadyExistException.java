package com.example.demo.exceptions;

public class MovieAlreadyExistException extends Exception {

    public MovieAlreadyExistException(String title) {

        super("Movie With title: " + title + " is already in database.");
    }
}
