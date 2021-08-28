package com.example.demo.services;

import com.example.demo.entities.Copy;
import com.example.demo.entities.Movie;
import com.example.demo.exceptions.NotEnoughCopiesInResourcesException;
import com.example.demo.repositories.CopyRepository;
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

    private final CopyRepository copyRepository;
    private final OrderRepository orderRepository;
    private final Map<Copy, Movie> copies = new HashMap<>();
    @Autowired
    public Cart(CopyRepository copyRepository,OrderRepository orderRepository) {
        this.copyRepository = copyRepository;
        this.orderRepository=orderRepository;
    }

    public void addCopy(Movie movie) throws NotEnoughCopiesInResourcesException {
        if (copyRepository.findFirstByMovie(movie)!=null){
            copies.put(movie.getCopies().stream().findFirst().get(),movie);
        }else throw new NotEnoughCopiesInResourcesException();
    }
    public void removeCopy(Copy copy) {
        if (copies.containsKey(copy)) {
                copies.remove(copy);}
            else {
                System.out.println("You cannot do this operation");
        }
    }
    public Map<Copy,Movie> getCopiesInCart(){
        return Collections.unmodifiableMap(copies);
    }
    public void checkout() throws NotEnoughCopiesInResourcesException {
            Copy copy;
            for (Map.Entry<Copy, Movie> entry : copies.entrySet()) {
                // Refresh quantity for every product before checking
                copy = copyRepository.findFirstByMovie(entry.getKey().getMovie());
                System.out.println();
            }
            /*orderRepository.save(copies.keySet());
            productRepository.flush();
            copies.clear();*/
    }
    public BigDecimal getTotal(){
        return null;
    }
}
