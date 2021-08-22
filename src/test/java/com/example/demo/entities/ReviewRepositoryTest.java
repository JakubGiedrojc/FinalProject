package com.example.demo.entities;

import com.example.demo.repositories.RevievRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ReviewRepositoryTest {

    @Autowired
    private RevievRepository revievRepository;

    @Test
    void shouldReviewRepositorySaveAndFindReview(){
        //given

    }



}