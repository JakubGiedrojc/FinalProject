package com.example.demo.services;

import com.example.demo.entities.Copy;
import com.example.demo.entities.Movie;
import com.example.demo.exceptions.NotEnoughCopiesInResourcesException;
import com.example.demo.repositories.MovieRepository;
import com.example.demo.repositories.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.print.attribute.standard.Copies;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Transactional
class CartTest {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private OrderRepository orderRepository;
    private final List<Movie> movies = new ArrayList<>();

    Cart cart = new  Cart(movieRepository,orderRepository);


    @Test
    void shouldAddCopyNewNotEnoughCopiesException() throws NotEnoughCopiesInResourcesException {
        //given
        Movie movie = new Movie();
        movie.setTitle("Obywatel Kane");
        movieRepository.save(movie);
        //when &then
        Assertions.assertThrows(NotEnoughCopiesInResourcesException.class,()->cart.addCopy(movie));
    }

    @Test
    void shouldRemoveMovie() {
    }

    @Test
    void ShouldGetMoviesInCart() {
    }

    @Test
    void shouldCheckout() {
    }
}