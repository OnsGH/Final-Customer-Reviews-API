package com.udacity.course3.reviews.mongodb.model;

import org.springframework.data.mongodb.core.mapping.Document;

  @Document(collection = "product")
   public class Product{

      @org.springframework.data.annotation.Id
        private int id;

        private String productName;
        private String productDescription;


        public Product(){ }
        public Product(int productId){
            this.id = productId;
        }

        public int getProductId() {
            return id;
        }

        public void setProductId(int productId) {
            this.id = productId;
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
