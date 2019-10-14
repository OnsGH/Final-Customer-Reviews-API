package com.udacity.course3.reviews.entity;




import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name="comment")
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private int commentId;

    @Column(name="comment_text")
    @NotEmpty(message = "Comment text cannot be null / empty")
    private String commentText;

    @Column(name="comment_created_time")
    private Timestamp commentCreatedTime;

    @ManyToOne
    @JoinColumn(name="review_id", nullable=false)
    private Review review;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Timestamp getCommentCreatedTime() {
        return commentCreatedTime;
    }

    public void setCommentCreatedTime(Timestamp commentCreatedTime) {
        this.commentCreatedTime = commentCreatedTime;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
