package com.example.demo.exceptions;

public class MovieAlreadyPresentException extends Exception {

    public MovieAlreadyPresentException(String title) {

        super("Movie With title: " + title + " is already in database.");
    }
}
