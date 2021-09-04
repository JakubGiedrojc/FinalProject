package com.example.demo.datatransferobject;

import com.example.demo.entities.Movie;
import com.example.demo.entities.Order;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
@Getter
@Setter
public class CheckoutResult {
    private boolean isSuccess;
    private Order order;
    private List<Movie> movies;
    private List<Movie> missingMovies;

    public CheckoutResult(boolean isSuccess){
        if (isSuccess==true){
            this.order=getOrder();
        }else {
            this.movies=getMovies();
            this.missingMovies=getMissingMovies();
        }
    }


}
