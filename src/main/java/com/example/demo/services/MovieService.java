package com.example.demo.services;

import com.example.demo.entities.Movie;
import com.example.demo.exceptions.MovieAlreadyPresentException;
import com.example.demo.exceptions.EmptyMovieDataBaseException;
import com.example.demo.exceptions.MovieWithThisIdIsNotExistException;
import com.example.demo.exceptions.MovieWithThisTitleNotFound;
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
    public Movie InsertNewMovie(Movie movie) throws MovieAlreadyPresentException {
        if (!movieRepository.findByTitle(movie.getTitle()).isPresent()) {

            return movieRepository.save(movie);
        } else {
            throw new MovieAlreadyPresentException(movie.getTitle());
        }
    }

    @Transactional
    public Movie getByTitle(String title) throws MovieWithThisTitleNotFound {
        if (movieRepository.findByTitle(title).isPresent()) {
            Movie movie = movieRepository.findByTitle(title).get();
            return movie;
        } else {
            throw new MovieWithThisTitleNotFound(title);
        }
    }

    @Transactional
    public Movie updateMovieData(Movie movie) throws MovieWithThisIdIsNotExistException {
        final Long id = movie.getId();
        if (!movieRepository.findById(id).isPresent()) {
            throw new MovieWithThisIdIsNotExistException(id);
        }

        Movie entity = movieRepository.findById(id).get();

        entity.setTitle(movie.getTitle());
        entity.setCover(movie.getCover());
        entity.setDescription(movie.getDescription());
        entity.setGenre(movie.getGenre());
        entity.setDateOfPremiere(movie.getDateOfPremiere());
        return entity;

    }

    public void delete(Long id) throws MovieWithThisIdIsNotExistException {

        if (movieRepository.findById(id).isPresent()) {
            this.movieRepository.deleteById(id);
        } else {
            throw new MovieWithThisIdIsNotExistException(id);
        }
    }
}
