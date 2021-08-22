package com.example.demo.entities;

import com.example.demo.repositories.CopyRepository;
import com.example.demo.repositories.MovieRepository;
import net.bytebuddy.pool.TypePool;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CopyRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CopyRepository copyRepository;

    @Test
    public void savesAndLoadsCopies(){
        //given
        Copy copy1 = new Copy();
        Copy copy2 = new Copy();
        Movie movie = new Movie();
        List<Copy> copies = new ArrayList<>();
        copies.add(copy1);
        copies.add(copy2);
        movie.setCopies(copies);
        copy1.setMovie(movie);
        copy2.setMovie(movie);
        movie.setCopies(copies);
        //then
        copyRepository.save(copy2);
        copyRepository.save(copy1);
        List<Copy> foundCopies = copyRepository.findAllByMovie(movie);

        //then
        Assertions.assertThat(foundCopies).size().isEqualTo(2);





    }

}