package com.example.demo.controllers;

import com.example.demo.entities.Movie;
import com.example.demo.exceptions.EmptyMovieDataBaseException;
import com.example.demo.exceptions.MovieAlreadyPresentException;
import com.example.demo.exceptions.MovieWithThisIdIsNotExistException;
import com.example.demo.exceptions.MovieWithThisTitleNotFound;
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
    public List<Movie> getAllMovies() throws EmptyMovieDataBaseException {
        return this.movieService.getAllMovies();
    }

    @PostMapping("add")
    public Movie InsertNewMovie(@RequestBody Movie movie) throws MovieAlreadyPresentException {
        return this.movieService.InsertNewMovie(movie);
    }

    @GetMapping("get-by-title")
    public Movie GetMovieByTitle(@RequestParam String title) throws MovieWithThisTitleNotFound {
        return this.movieService.getByTitle(title);
    }

    @PutMapping("update-data")
    public Movie modifyMovieData(@RequestBody Movie movie) throws MovieWithThisIdIsNotExistException {
        return this.movieService.updateMovieData(movie);
    }

    @DeleteMapping("delete")
    public String deleteMovieById(@RequestParam Long id) throws MovieWithThisIdIsNotExistException {
        this.movieService.delete(id);
        return "Movie with id: " + id + " deleted";
    }

}
