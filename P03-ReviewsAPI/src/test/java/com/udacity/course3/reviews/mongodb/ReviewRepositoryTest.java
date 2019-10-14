
package com.udacity.course3.reviews.mongodb;


import com.mongodb.*;
import com.udacity.course3.reviews.ReviewsApplication;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.mongodb.model.Review;
import com.udacity.course3.reviews.mongodb.repository.ReviewRepositoryMongoDB;

import org.junit.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.Optional;




@RunWith(SpringRunner.class)
@DataMongoTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ReviewsApplication.class)
@DirtiesContext
public class ReviewRepositoryTest {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ReviewRepositoryMongoDB reviewRepositoryMongoDB;


    @Test
    public void testFindAll() {

        Date date = new Date(System.currentTimeMillis());
        // given
        DBObject review = BasicDBObjectBuilder.start()
                .add("id", "1")
                .add("reviewTitle", "ReviewTitle")
                .add("reviewText","ReviewText")
                .add("reviewRating",5)
                .add("reviewCreatedTime",date)
                .get();

        DBObject product = BasicDBObjectBuilder.start()
        .add("id", 1)
        .add("productName", "productName1")
        .add("productDescription", "productDescription")
                .get();

        review.put("product", product);

        // when
        mongoTemplate.save(review, "review");

        // then
        assertThat(mongoTemplate.
                findAll(DBObject.class, "review")).extracting("reviewRating")
                .containsOnly(5);



    }

    @Test
    public void testFindById() {

        Date date = new Date(System.currentTimeMillis());

        Review review = new Review();
        review.setReviewId(1);
        review.setReviewTitle("ReviewTitle");
        review.setReviewText("ReviewText");
        review.setReviewRating(5);
        review.setReviewCreatedTime(date);
        Product product=new Product();
        product.setProductId(1);
        product.setProductName("ProductName1");
        product.setProductDescription("ProductDescription1");
        review.setProduct(product);

        reviewRepositoryMongoDB.save(review);
        Optional<Review> reviewResult = reviewRepositoryMongoDB.findById(1);
        assertEquals(5, reviewResult.get().getReviewRating());
        assertEquals("ProductName1", reviewResult.get().getProduct().getProductName());

    }


    @Test
    public void testFindByProduct() {

        Date date = new Date(System.currentTimeMillis());

        Review review = new Review();
        review.setReviewId(1);
        review.setReviewTitle("ReviewTitle1");
        review.setReviewText("ReviewText1");
        review.setReviewRating(5);
        review.setReviewCreatedTime(date);
        Product product=new Product();
        product.setProductId(1);
        product.setProductName("ProductName1");
        product.setProductDescription("ProductDescription1");
        review.setProduct(product);

        reviewRepositoryMongoDB.save(review);

        Review review2 = new Review();
        review2.setReviewId(2);
        review2.setReviewTitle("ReviewTitle2");
        review2.setReviewText("ReviewText2");
        review2.setReviewRating(4);
        review2.setReviewCreatedTime(date);
        review2.setProduct(product);
        reviewRepositoryMongoDB.save(review2);

        /**
         * Find by productId
         */
        product.setProductId(1);

        List<Review> reviewResultList = reviewRepositoryMongoDB.findAllByProduct(product);
        assertEquals("ReviewTitle1", reviewResultList.get(0).getReviewTitle());
        assertEquals("ReviewTitle2", reviewResultList.get(1).getReviewTitle());

    }
}
