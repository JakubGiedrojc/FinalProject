package repositories;

import entities.Movie;
import entities.Review;
import org.springframework.data.repository.CrudRepository;

public interface RevievRepository extends CrudRepository<Review, Long> {
}
