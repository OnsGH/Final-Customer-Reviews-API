package com.udacity.course3.reviews.entity;



import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;



@Entity
@Table(name="product")
public class Product{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private int productId;

    @Column(name="product_name")
    @NotEmpty(message = "Product name cannot be null / empty")
    private String productName;

    @Column(name="product_description")
    @NotEmpty(message = "Product description cannot be null / empty")
    private String productDescription;


    public Product(){ }
    public Product(int productId){
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }



}
