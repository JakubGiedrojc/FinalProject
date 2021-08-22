package com.example.demo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cover;
    private String title;
    // TODO enums
    private Enum genre;
    private LocalDate dateOfPremiere;
    private String description;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Copy> copies;
    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private List<Review> reviews;
}
