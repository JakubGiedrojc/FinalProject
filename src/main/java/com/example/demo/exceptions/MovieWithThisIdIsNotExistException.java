package com.example.demo.exceptions;

public class MovieWithThisIdIsNotExistException extends Exception {
    public MovieWithThisIdIsNotExistException(Long id) {
        super("Movie with this" + id + "is not exists!");
    }
}
