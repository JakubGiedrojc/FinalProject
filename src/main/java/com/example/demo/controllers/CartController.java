package com.example.demo.controllers;

import com.example.demo.entities.Movie;
import com.example.demo.exceptions.EmptyMovieDataBaseException;
import com.example.demo.repositories.MovieRepository;
import com.example.demo.services.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("cart")
@AllArgsConstructor
public class CartController {
    private Cart cart;
    private MovieRepository movieRepository;

    @PostMapping("add/movie")
    public Optional<Movie> addMovie(@RequestParam String movieTitle){
        Optional<Movie> movie =movieRepository.findByTitle(movieTitle);
        if (movie.isPresent()){
        cart.addMovie(movie.get());
        return movie;}else
            return null;}
    @DeleteMapping("remove/[title]")
    public void removeMovie(@PathVariable String title){
        Optional<Movie> movie=cart.getMoviesInCart().stream().filter(m -> m.getTitle().equals(title)).findFirst();
        if (movie.isPresent()){
        cart.getMoviesInCart().remove(movie.get());}

    }
    @GetMapping("getmovies")
    public List<Movie> getMovies(){
        return cart.getMoviesInCart();
    }
    //@PostMapping()
}
