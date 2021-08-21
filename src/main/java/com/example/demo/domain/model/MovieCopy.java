package com.example.demo.domain.model;

import lombok.Data;


import javax.persistence.*;

@Entity
@Data
public class MovieCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long copyId;

    private long movieId;

    private Long orderId;





}
