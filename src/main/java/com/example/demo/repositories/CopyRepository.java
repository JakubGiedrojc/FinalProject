package com.example.demo.repositories;

import com.example.demo.entities.Copy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopyRepository extends CrudRepository<Copy, Integer> {
}
