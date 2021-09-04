package com.example.demo.repositories;

import com.example.demo.entities.Genre;
import com.example.demo.entities.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends CrudRepository <Movie, Long> {
    Optional<Movie> findByTitle(String title);
    @Query(value = " select movie from movie_copy where order_id is null",nativeQuery = true)
    Movie findMovieWhichHasACopyAndOrderIDEqualsNull(Movie movie);
    List<Movie> findAllByGenre(Genre genre);

}
