package com.example.demo.services;

import com.example.demo.datatransferobject.CheckoutResult;
import com.example.demo.entities.Copy;
import com.example.demo.entities.Movie;
import com.example.demo.entities.Order;
import com.example.demo.exceptions.NotEnoughCopiesInResourcesException;
import com.example.demo.repositories.CopyRepository;
import com.example.demo.repositories.MovieRepository;
import com.example.demo.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
@RequiredArgsConstructor
public class Cart {


    private final OrderRepository orderRepository;
    private final CopyRepository copyRepository;
    private  List<Movie> movies = new ArrayList<>();// zamienić na List<Film>



    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void removeMovie(Movie movie) {
        movies.remove(movie);
    }

    public List<Movie> getMoviesInCart() { //unmodifibleList
        return Collections.unmodifiableList(movies);
    }

    public CheckoutResult checkout() throws NotEnoughCopiesInResourcesException {
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
        CheckoutResult checkoutResult;
        if (impossibleToRent.isEmpty()){
            checkoutResult=new CheckoutResult(true);
            Order order = new Order();
            checkoutResult.setOrder(order);
            order.setStartDate(LocalDate.now());
            order.setCopies(copies);
            orderRepository.save(order);
        } else {
            checkoutResult=new CheckoutResult(false);
            checkoutResult.setMovies(movies);
            checkoutResult.setMissingMovies(impossibleToRent);
        }
        return checkoutResult;


    }


}

