package com.example.demo.exceptions;

import com.example.demo.entities.Copy;

public class NotEnoughCopiesInResourcesException extends Exception{
    private static final String DEFAULT_MESSAGE = "Not enough copies in resources";

    public NotEnoughCopiesInResourcesException(){
        super(DEFAULT_MESSAGE);
    }

    public NotEnoughCopiesInResourcesException(Copy copy){
        super(String.format("Not enough %s copies in resources",copy.getMovie()));
    }


}
