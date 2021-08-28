package com.example.demo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;
    private Integer rating;
    private String review;
}
