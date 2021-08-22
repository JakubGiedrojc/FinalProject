package com.example.demo.repositories;

import com.example.demo.entities.Copy;
import com.example.demo.entities.Movie;
import com.example.demo.entities.Order;
import com.example.demo.entities.Review;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class CopyRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CopyRepository copyRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    void saves_and_load_copy(){
        //given
        Movie movie = new Movie();
        Order order = new Order();
        movieRepository.save(movie);
        orderRepository.save(order);
        Copy copy = new Copy();
        copy.setMovie(movie);
        copy.setOrder(order);

        List<Optional<Copy>> foundCopyByMovie = copyRepository.findAllByMovie(movie);
        Assertions.assertThat(foundCopyByMovie.isEmpty()).isTrue();

        //when
        copyRepository.save(copy);
        foundCopyByMovie=copyRepository.findAllByMovie(movie);

        //then
        Assertions.assertThat(foundCopyByMovie.stream().findFirst().isPresent()).isTrue();
        Copy foundCopy = foundCopyByMovie.get(0).get();
        Assertions.assertThat(foundCopy.getMovie()).isEqualTo(movie);
        Assertions.assertThat(foundCopy.getOrder()).isEqualTo(order);


    }
}
