package com.udacity.course3.reviews;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.ProductRepository;
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
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import java.util.Date;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewRepositoryTest {


    @Autowired
    private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired private ProductRepository productRepository;
    @Autowired private ReviewRepository reviewRepository;

    @Test
    public void injectedComponentsAreNotNull(){
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(testEntityManager).isNotNull();
        assertThat(productRepository).isNotNull();
        assertThat(reviewRepository).isNotNull();
    }

    @Test
    public void testfindAllByProduct(){
        Date date = new Date();;
        // create Review
        Review review = new Review();

        /* set fields */

        review.setReviewTitle("ReviewTitle");
        review.setReviewText("ReviewText");
        review.setReviewRating(5);
        review.setReviewCreatedTime(new Timestamp(date.getTime()));

        Product product = new Product();
        product.setProductName("product1");
        product.setProductDescription("product1Desc1");

        review.setProduct(product);

        entityManager.persist(product);

        entityManager.persist(review);



        List<Review> actualList = reviewRepository.findAllByProduct(product);
        assertThat(actualList).isNotNull();
        assertEquals(review.getReviewText(), actualList.get(0).getReviewText());
        assertEquals(1,actualList.get(0).getProduct().getProductId());
        System.out.print("**  ActualReviewText  "+ actualList.get(0).getReviewText() +"  ** SettetdReviewText "+ review.getReviewText()+"   ");
        System.out.print("**  ActualProductId  "+ actualList.get(0).getProduct().getProductId() +"  ** SettetdProductId "+ 1 + "  ");


    }
}
