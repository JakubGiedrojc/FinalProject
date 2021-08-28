package com.example.demo.repositories;

import com.example.demo.entities.Copy;
import com.example.demo.entities.Movie;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CopyRepository extends CrudRepository<Copy, Integer> {
    List<Optional<Copy>> findAllByMovie(Movie movie);
    Copy findFirstByMovie(Movie movie);
}
