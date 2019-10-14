package com.udacity.course3.reviews.mongodb.model;

import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Date;

@Document(collection = "comment")
public class Comment {

    @org.springframework.data.annotation.Id
    private int id;

    private String commentText;
    private Date commentCreatedTime;
    private Review review;

    public Comment() {
    }

    public int getCommentId() {
        return id;
    }

    public void setCommentId(int commentId) {
        this.id = commentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Date getCommentCreatedTime() {
        return commentCreatedTime;
    }

    public void setCommentCreatedTime(Date commentCreatedTime) {
        this.commentCreatedTime = commentCreatedTime;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
