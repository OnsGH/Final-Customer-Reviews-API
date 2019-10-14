package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.mongodb.repository.CommentRepositoryMongoDB;
import com.udacity.course3.reviews.mongodb.repository.ReviewRepositoryMongoDB;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    /**
     *  JPA repositories
     */

   @Autowired
    CommentRepository commentRepository;
   @Autowired
   ReviewRepository reviewRepository;

    /**
     *  MongoDB repositories
     */

    @Autowired
    CommentRepositoryMongoDB commentRepositoryMongoDB;
    @Autowired
    ReviewRepositoryMongoDB reviewRepositoryMongoDB;


    /**
     * Creates a comment for a review.
     *
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<?> createCommentForReview(@PathVariable("reviewId") Integer reviewId, @RequestBody @Valid Comment comment) {

        Optional<Review>  review = reviewRepository.findById(reviewId);

        com.udacity.course3.reviews.mongodb.model.Comment commentMongodb = new com.udacity.course3.reviews.mongodb.model.Comment();

        if(review.isPresent()){
            comment.setReview(review.get());

            commentMongodb.setCommentCreatedTime(comment.getCommentCreatedTime());
            commentMongodb.setCommentText(comment.getCommentText());
            int commentId =  commentRepository.save(comment).getCommentId();

            /**
             *
             * MongoDB
             */

            commentMongodb.setCommentId(commentId);
            Optional<com.udacity.course3.reviews.mongodb.model.Review> reviewMongodb =
                    reviewRepositoryMongoDB.findById(reviewId);
            commentMongodb.setReview(reviewMongodb.get());
            commentRepositoryMongoDB.save(commentMongodb);
            /**
             * Setting Mongo comments for a review document
             */
            List<com.udacity.course3.reviews.mongodb.model.Comment> commentList;

            if(reviewMongodb.get().getCommentList().isEmpty()){


                // Avoid redundant data
                commentMongodb.setReview(null);
                commentList = new ArrayList<com.udacity.course3.reviews.mongodb.model.Comment>();
                commentList.add(commentMongodb) ;
                reviewMongodb.get().setCommentList(commentList);
                reviewRepositoryMongoDB.save(reviewMongodb.get());

            }else{
                // Avoid redundant data
               commentMongodb.setReview(null);
               reviewMongodb.get().getCommentList().add(commentMongodb);
               reviewRepositoryMongoDB.save(reviewMongodb.get());

            }

            return  ResponseEntity.status(HttpStatus.CREATED).body(comment);
        }
        else{
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * List comments for a review.
     *
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public List<?> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {
        Optional<Review>  review = reviewRepository.findById(reviewId);

        if(review.isPresent()){
            Optional<com.udacity.course3.reviews.mongodb.model.Review> reviewMongodb =
                    reviewRepositoryMongoDB.findById(reviewId);
          return commentRepositoryMongoDB.
                  findAllByReview(reviewMongodb.get());
        }
          else {

                   throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        }
    }
}