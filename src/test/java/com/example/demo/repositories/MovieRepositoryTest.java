package com.example.demo.repositories;

import com.example.demo.entities.Movie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void saves_and_loads_movie() {
        //given
        String title = "Ogniem i Mieczem";

        Movie m1 = new Movie();
        m1.setTitle(title);
        m1.setDateOfPremiere(LocalDate.of(1999, 2, 11));

        Optional<Movie> foundMovieOptional = movieRepository.findByTitle(title);
        Assertions.assertThat(foundMovieOptional.isEmpty()).isTrue();

        //when
        movieRepository.save(m1);
        foundMovieOptional = movieRepository.findByTitle(title);

        //then
        Assertions.assertThat(foundMovieOptional.isPresent()).isTrue();
        Movie foundMovie = foundMovieOptional.get();
        Assertions.assertThat(foundMovie.getTitle()).isEqualTo(m1.getTitle());
        Assertions.assertThat(foundMovie.getDateOfPremiere()).isEqualTo(m1.getDateOfPremiere());
    }

}