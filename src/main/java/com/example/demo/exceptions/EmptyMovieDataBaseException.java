package com.example.demo.exceptions;

public class EmptyMovieDataBaseException extends Exception {

    public EmptyMovieDataBaseException() {
        super("There is no movies in database");

    }
}


