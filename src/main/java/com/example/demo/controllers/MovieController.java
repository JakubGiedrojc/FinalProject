package com.example.demo.controllers;

import com.example.demo.entities.Movie;
import com.example.demo.exceptions.EmptyMovieDataBaseException;
import com.example.demo.exceptions.MovieAlreadyPresentException;
import com.example.demo.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("get-all")
    public List<Movie> getAllMovies() throws EmptyMovieDataBaseException    {
        return movieService.getAllMovies();
    }

    @PostMapping("add-movie")
    public Movie InsertNewMovie(@RequestBody Movie movie) throws MovieAlreadyPresentException
    {
        return movieService.InsertNewMovie(movie);
    }

}
