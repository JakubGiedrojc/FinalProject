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
    private final List<Movie> movies = new ArrayList<>();// zamienić na List<Film>

    @Autowired
    public Cart(MovieRepository movieRepository, OrderRepository orderRepository) {
        this.movieRepository = movieRepository;
        this.orderRepository = orderRepository;
    }

    public void addCopy(Movie movie) throws NotEnoughCopiesInResourcesException {
        //check czy film ma dostępne kopie z orderId ==null
        if(movieRepository.findMovieWhichHasACopyAndOrderIDEqualsNull(movie)!=null){
        Copy selectedCopy=movieRepository.findMovieWhichHasACopyAndOrderIDEqualsNull(movie)
                .getCopies()
                .stream()
                .findFirst()
                .get();
        movies.add(movie);}else throw new NotEnoughCopiesInResourcesException();
    }

    public void removeMovie(Movie movie) {
        //remove movie
        if (movies.contains(movie)){
            movies.remove(movie);
        } else {
            System.out.println("You cannot do this operation");
        }
    }

    public List<Movie> getMoviesInCart() { //unmodifibleList
        return Collections.unmodifiableList(movies);
    }

    public Order checkout() throws NotEnoughCopiesInResourcesException {
        List<Movie> checkoutlist =getMoviesInCart();
        List<Copy> checkoutCopy = new ArrayList<>();
        for (int i = 0; i < checkoutlist.size()-1; i++) {
            checkoutCopy.add(checkoutlist.get(i).getCopies().stream().findFirst().get());

        }
        if (checkoutCopy.size()==checkoutlist.size()){
            Order myOrder=new Order();
            myOrder.setCopies(checkoutCopy);
            orderRepository.save(myOrder);
            return myOrder;
        } else {
            System.out.println("unfortunatelly, some movies got now free copy. You can check what's still available below");
            for (int i = 0; i < checkoutlist.size()-1; i++) {
                System.out.println(checkoutCopy.get(i).getMovie().getTitle());
            }
        }
        return null;


    }
}

