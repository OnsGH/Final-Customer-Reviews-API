package com.udacity.course3.reviews.mongodb.repository;

import com.udacity.course3.reviews.mongodb.model.Comment;
import com.udacity.course3.reviews.mongodb.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepositoryMongoDB extends MongoRepository<Comment, String> {

    List<Comment> findAllByReview(Review review);


}
