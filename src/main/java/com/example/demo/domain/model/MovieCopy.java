package com.example.demo.domain.model;

import lombok.Data;


import javax.persistence.*;

@Entity
@Data
public class MovieCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long copyId;
    @JoinColumn(name="movie_fk")
    private Movie movie;
    @JoinColumn(name="order_fk")
    private Order order;






}
