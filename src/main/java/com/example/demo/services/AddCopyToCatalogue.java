package com.example.demo.services;

import com.example.demo.entities.Copy;
import com.example.demo.entities.Movie;
import com.example.demo.exceptions.MovieWithThisIdIsNotExistException;
import com.example.demo.repositories.CopyRepository;
import com.example.demo.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddCopyToCatalogue {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    CopyRepository copyRepository;
    Movie movie;

    @Transactional
    public Copy add (Copy copy) throws MovieWithThisIdIsNotExistException {
        if(movieRepository.findById(movie.getId()).isEmpty()){
            throw new MovieWithThisIdIsNotExistException(movie.getId());

        }
        return copyRepository.save(copy);
    }
}
