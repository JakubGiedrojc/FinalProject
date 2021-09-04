package com.example.demo.exceptions;

public class MovieWithThisTitleNotFound extends Exception {

    public MovieWithThisTitleNotFound(String title) {
        super("Movie with this title: " + title + " not found!");
    }

}
