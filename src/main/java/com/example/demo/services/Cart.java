package com.example.demo.services;

import com.example.demo.entities.Copy;
import com.example.demo.entities.Movie;
import com.example.demo.repositories.CopyRepository;
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
    private final Map<Copy, Integer> copies = new HashMap<>();
    @Autowired
    public Cart(CopyRepository copyRepository) {
        this.copyRepository = copyRepository;
    }

    public void addCopy(Copy copy){
        if (copies.containsKey(copy)) {
            copies.replace(copy, copies.get(copy) + 1);
        } else {
            copies.put(copy, 1);
        }
    }
    public void removeCopy(Copy copy) {
        if (copies.containsKey(copy)) {
            if (copies.get(copy) > 1)
                copies.replace(copy, copies.get(copy) - 1);
            else if (copies.get(copy) == 1) {
                copies.remove(copy);
            }
        }
    }
    public Map<Copy,Integer> getCopiesInCart(){
        return Collections.unmodifiableMap(copies);
    }
    public void checkout(){

    }
    public BigDecimal getTotal(){
        return null;
    }
}
