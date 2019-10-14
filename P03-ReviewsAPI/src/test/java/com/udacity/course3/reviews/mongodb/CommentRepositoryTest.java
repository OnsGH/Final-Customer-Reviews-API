package com.udacity.course3.reviews.mongodb;


import com.udacity.course3.reviews.ReviewsApplication;
import com.udacity.course3.reviews.mongodb.model.Comment;
import com.udacity.course3.reviews.mongodb.model.Review;
import com.udacity.course3.reviews.mongodb.repository.CommentRepositoryMongoDB;
import com.udacity.course3.reviews.mongodb.repository.ReviewRepositoryMongoDB;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ReviewsApplication.class)
@DirtiesContext
public class CommentRepositoryTest {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ReviewRepositoryMongoDB reviewRepositoryMongoDB;
    @Autowired
    private CommentRepositoryMongoDB commentRepositoryMongoDB;

@Test
   public void findAllByReview(){

    Date date = new Date(System.currentTimeMillis());
    Comment comment = new Comment();
    comment.setCommentId(1);
    comment.setCommentText("CommentText1");
    comment.setCommentCreatedTime(date);
    Review review = new Review();
    review.setReviewId(1);
    review.setReviewTitle("ReviewTitle");
    review.setReviewText("ReviewText");
    review.setReviewRating(5);
    review.setReviewCreatedTime(date);
    comment.setReview(review);
    commentRepositoryMongoDB.save(comment);

    Comment comment2 = new Comment();
    comment2.setCommentId(2);
    comment2.setCommentText("CommentText2");
    comment2.setCommentCreatedTime(date);
    comment2.setReview(review);
    commentRepositoryMongoDB.save(comment2);


    review.setReviewId(1);
    List<Comment> commentResultList = commentRepositoryMongoDB.findAllByReview(review);
    assertEquals("CommentText1", commentResultList.get(0).getCommentText());
    assertEquals("CommentText2", commentResultList.get(1).getCommentText());


}



}
