package com.example.demo.services;

import com.example.demo.entities.Movie;
import com.example.demo.exceptions.MovieAlreadyExistException;
import com.example.demo.exceptions.EmptyMovieDataBaseException;
import com.example.demo.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Transactional
    public List<Movie> getAllMovies() throws EmptyMovieDataBaseException {
        List<Movie> movieList = new ArrayList();

        movieRepository.findAll().forEach(movieList::add);

        if (!movieList.isEmpty()) {

            return movieList;
        } else {

            throw new EmptyMovieDataBaseException();
        }

    }

    @Transactional
    public Movie InsertNewMovie(Movie movie) throws MovieAlreadyExistException {
        if (!movieRepository.findByTitle(movie.getTitle()).isPresent()) {

            return movieRepository.save(movie);
        } else {
            throw new MovieAlreadyExistException(movie.getTitle());
        }


    }

}
