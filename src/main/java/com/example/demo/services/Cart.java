package com.example.demo.services;

import com.example.demo.entities.Copy;
import com.example.demo.entities.Movie;
import com.example.demo.entities.Order;
import com.example.demo.exceptions.NotEnoughCopiesInResourcesException;
import com.example.demo.repositories.CopyRepository;
import com.example.demo.repositories.MovieRepository;
import com.example.demo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class Cart {

    private final MovieRepository movieRepository;
    private final OrderRepository orderRepository;
    private final CopyRepository copyRepository;
    private final List<Movie> movies = new ArrayList<>();// zamienić na List<Film>

    @Autowired
    public Cart(MovieRepository movieRepository, OrderRepository orderRepository,CopyRepository copyRepository) {
        this.movieRepository = movieRepository;
        this.orderRepository = orderRepository;
        this.copyRepository=copyRepository;

    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void removeMovie(Movie movie) {
        movies.remove(movie);
    }

    public List<Movie> getMoviesInCart() { //unmodifibleList
        return Collections.unmodifiableList(movies);
    }

    public void checkout() throws NotEnoughCopiesInResourcesException {
        List<Copy> copies = new ArrayList();
        List<Movie> impossibleToRent = new ArrayList();
        for(Movie m : movies) {
            copies.add(copyRepository.findNotRented(m));

            if(copyRepository.findNotRented(m) == null) {
                //uwzglednic wyjatek
                impossibleToRent.add(m);
                movies.remove(m);//usuwa z koszyka
            }
        }
        //jak przeiterujesz po wszystkich to wyswietlasz jeszcze raz strone koszyka ORAZ liste filmow ktorych nie da sie wypozyczyc na skutek braku kopii (impossibleToRent)
        //Nie mamy wolnych kopii nstp filmow:
        System.out.println("Nie mamy wolnych kopii nstp filmow");
        impossibleToRent.stream();
        System.out.println("Twój koszyk został zaaktualizowany poniżej");
        movies.stream();


    }
}

