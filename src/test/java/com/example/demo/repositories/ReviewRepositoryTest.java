package com.example.demo.repositories;

import com.example.demo.entities.Movie;
import com.example.demo.entities.Order;
import com.example.demo.entities.Review;
import com.example.demo.entities.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class ReviewRepositoryTest {
    @Autowired
    private RevievRepository revievRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void saves_and_load_review(){
        //given
        //Order
        Order order = new Order();
        LocalDate startDate= LocalDate.of(2000,6,23);
        User user = new User();
        userRepository.save(user);
        Movie movie = new Movie();
        movieRepository.save(movie);
        orderRepository.save(order);
        Integer rating =4;
        String rewiew = "Bardzo dobry film";

        Review review = new Review();
        review.setMovie(movie);
        review.setOrder(order);
        review.setRating(4);
        review.setReview("Spoko Film");

        List<Optional<Review>> foundReviewByMovie = revievRepository.findAllByMovie(movie);
        Assertions.assertThat(foundReviewByMovie.isEmpty()).isTrue();

        //when
        revievRepository.save(review);
        foundReviewByMovie=revievRepository.findAllByMovie(movie);

        // then
        Assertions.assertThat(foundReviewByMovie.stream().findFirst().isPresent()).isTrue();
        Review foundReview = foundReviewByMovie.get(0).get();
        Assertions.assertThat(foundReview.getMovie()).isEqualTo(movie);
        Assertions.assertThat(foundReview.getOrder()).isEqualTo(order);

    }
}
