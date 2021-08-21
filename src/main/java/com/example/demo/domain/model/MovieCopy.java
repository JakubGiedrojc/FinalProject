package com.example.demo.domain.model;

import lombok.Data;


import javax.persistence.*;

@Entity
@Data
public class MovieCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long copyId;
    @Column
    private long movieId;
    @Column
    private Long orderId;





}
