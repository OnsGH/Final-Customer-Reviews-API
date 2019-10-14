package com.udacity.course3.reviews;


import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {


    @Autowired
    private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired private CommentRepository commentRepository;
    @Autowired private ReviewRepository reviewRepository;

    @Test
    public void injectedComponentsAreNotNull(){
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(testEntityManager).isNotNull();
        assertThat(commentRepository).isNotNull();
        assertThat(reviewRepository).isNotNull();
    }

    @Test
    public void findAllByReview() {
        Date date = new Date();

        /* create Review */
        Review review = new Review();

        /* set Review fields */
        review.setReviewTitle("ReviewTitle");
        review.setReviewText("ReviewText");
        review.setReviewRating(5);
        review.setReviewCreatedTime(new Timestamp(date.getTime()));

        /* create Product */
        Product product = new Product();

        /* set Product fields */
        product.setProductName("product");
        product.setProductDescription("product1Desc");

        review.setProduct(product);

        entityManager.persist(product);
        entityManager.persist(review);

        /* create comment */
        Comment comment = new Comment();
        comment.setReview(review);
        comment.setCommentText("commentText for a review 1");
        comment.setCommentCreatedTime(new Timestamp(date.getTime()));


        entityManager.persist(comment);
        List<Comment> actualList = commentRepository.findAllByReview(review);
        assertThat(actualList).isNotNull();
        assertEquals(comment.getCommentText(), actualList.get(0).getCommentText());
        assertEquals(1, actualList.get(0).getReview().getReviewId());
        System.out.print("**  ActualCommentText  " + actualList.get(0).getCommentText() + "  ** SettetdCommentText " + comment.getCommentText() + "   ");
        System.out.print("**  ActualReviewId  " + actualList.get(0).getReview().getReviewId() + "  ** SettetdReviewId " + 1 + "  ");


    }






    }
