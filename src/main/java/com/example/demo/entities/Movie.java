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
    @Column(name = "movie_id")
    private Long id;
    private String cover;
    // TODO enums
    private Enum genre;
    private String title;
    private LocalDate dateOfPremiere;
    private String description;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Copy> copies;
}
