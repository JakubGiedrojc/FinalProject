package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cover;
    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private LocalDate dateOfPremiere;
    private String description;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Copy> copies;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Review> reviews;
}
