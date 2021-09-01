package com.example.demo.services;

import com.example.demo.entities.Genre;
import com.example.demo.entities.Movie;
import com.example.demo.repositories.CopyRepository;
import com.example.demo.repositories.MovieRepository;
import com.example.demo.repositories.OrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CartTest {
    @MockBean
    MovieRepository movieRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CopyRepository copyRepository;
    @MockBean
    Cart cart;


    @Test
    void shouldAddMovie(){
        //given
        Movie m = new Movie();
        m.setTitle("Ostatni Mohikanin");
        m.setGenre(Genre.BIOGRAFICZNY);
        m.setDescription("Dobry film");
        ArgumentCaptor<Movie> captor = ArgumentCaptor.forClass(Movie.class);
        this.movieRepository.save(m);
        List<Movie> movies =cart.getMoviesInCart();
        //when
        this.cart.addMovie(m);
        //then
        Mockito.verify(cart).addMovie(captor.capture());
        Movie value = captor.getValue();
        assertAll(
                ()->assertEquals(cart.getMoviesInCart().contains(m),value),
                ()->assertEquals("Ostatni Mohikanin",value.getTitle()),
                ()->assertEquals("Dobry film",value.getDescription()),
                ()->assertEquals(Genre.BIOGRAFICZNY,value.getGenre())
        );

    }
    @Test
    void shouldRemoveMovie(){
        //given
        //when
        //then
    }
    @Test
    void shouldGetMoviesInCart(){
        //given
        //when
        //then
    }
    @Test
    void shouldCheckOut(){
        //given
        //when
        //then
    }

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