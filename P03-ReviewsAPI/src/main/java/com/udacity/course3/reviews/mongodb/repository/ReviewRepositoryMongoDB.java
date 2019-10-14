package com.udacity.course3.reviews.mongodb.repository;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.mongodb.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepositoryMongoDB extends MongoRepository<Review, String> {

    Optional<Review> findById(int id);
    @Query(value="{ 'product' : ?0 }")
    List<Review> findAllByProduct(Product product);

}
