package com.example.demo.repositories;

import com.example.demo.entities.Movie;
import com.example.demo.entities.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RevievRepository extends CrudRepository<Review, Long> {
    List<Optional<Review>> findAllByMovie(Movie movie);
}
