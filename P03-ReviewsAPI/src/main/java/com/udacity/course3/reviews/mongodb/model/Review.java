package com.udacity.course3.reviews.mongodb.model;


import com.udacity.course3.reviews.entity.Product;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection ="review" )
public class Review {

    @org.springframework.data.annotation.Id
    private int id;
    private String reviewTitle;
    private String reviewText;

    private int reviewRating;
    private Date reviewCreatedTime;
    private List<Comment> commentList = new ArrayList<Comment>();
    private com.udacity.course3.reviews.entity.Product product;


    public Review (){

    }

    public Review (int reviewId){

        this.id = reviewId;
    }
    public int getReviewId() {
        return id;
    }

    public void setReviewId(int reviewId) {
        this.id = reviewId;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }

    public Date getReviewCreatedTime() {
        return reviewCreatedTime;
    }

    public void setReviewCreatedTime(Date reviewCreatedTime) {
        this.reviewCreatedTime = reviewCreatedTime;
    }


    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(com.udacity.course3.reviews.entity.Product product) {
        this.product = product;
    }

}
