package com.example.demo.exceptions;

public class MovieNotFoundException extends Exception {
    public MovieNotFoundException(int id) {

        super("Movie With ID: " + id + " not exist in database");
    }
}
