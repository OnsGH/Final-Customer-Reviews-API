package com.udacity.course3.reviews.mongodb.repository;

import com.udacity.course3.reviews.mongodb.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepositoryMongoDB extends MongoRepository<Product, String> {
}
