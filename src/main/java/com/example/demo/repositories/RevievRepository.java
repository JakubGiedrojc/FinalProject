package com.example.demo.repositories;

import com.example.demo.entities.Review;
import org.springframework.data.repository.CrudRepository;

public interface RevievRepository extends CrudRepository<Review, Long> {
}
