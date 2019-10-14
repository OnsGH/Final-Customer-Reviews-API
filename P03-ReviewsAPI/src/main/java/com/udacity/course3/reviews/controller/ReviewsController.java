package com.udacity.course3.reviews.controller;



import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.mongodb.repository.ReviewRepositoryMongoDB;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    /**
     *  JPA repositories
     */
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ProductRepository productRepository;

    /**
     *  MongoDB repositories
     */
    @Autowired
    ReviewRepositoryMongoDB reviewRepositoryMongoDB;



    /**
     * Creates a review for a product.
     * <p>
     * 1. Add argument for review entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of product.
     * 3. If product not found, return NOT_FOUND.
     * 4. If found, save review.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */

    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<?> createReviewForProduct(@PathVariable("productId") Integer productId,@RequestBody @Valid Review review) {
        com.udacity.course3.reviews.mongodb.model.Review reviewMongodb;
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()){
            review.setProduct(product.get());

            reviewMongodb = new com.udacity.course3.reviews.mongodb.model.Review();
            reviewMongodb.setReviewTitle(review.getReviewTitle());
            reviewMongodb.setReviewText(review.getReviewText());
            reviewMongodb.setReviewRating(review.getReviewRating());
            reviewMongodb.setReviewCreatedTime(review.getReviewCreatedTime());
            reviewMongodb.setProduct(product.get());
            int id = reviewRepository.save(review).getReviewId();
            reviewMongodb.setReviewId(id);

            reviewRepositoryMongoDB.save(reviewMongodb);

            return ResponseEntity.status(HttpStatus.CREATED).body(reviewMongodb);
        }else
        {
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);}
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */

    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
   //
    public ResponseEntity<List<?>> listReviewsForProduct(@PathVariable("productId") Integer productId) {

        /**
         *
         * Loading reviews for a product,the service reads the ids from MySQL
         *  and the review document from MongoDB.
         **/

          Optional<Product> product = productRepository.findById(productId);
            if(product.isPresent())
            {

                List<com.udacity.course3.reviews.mongodb.model.Review> reviewList = reviewRepositoryMongoDB.
                      findAllByProduct(product.get());

               return ResponseEntity.ok(reviewList);


            }else{

                 throw new HttpServerErrorException(HttpStatus.NOT_FOUND);}
    }
}