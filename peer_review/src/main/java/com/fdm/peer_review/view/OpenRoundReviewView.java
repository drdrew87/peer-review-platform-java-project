package com.fdm.peer_review.view;

import java.util.ArrayList;
import java.util.List;

import com.fdm.peer_review.model.Department;
import com.fdm.peer_review.model.Employee;
import com.fdm.peer_review.model.Review;
import com.fdm.peer_review.model.ReviewRound;

public class OpenRoundReviewView {
    
    private int index;
    
    private ReviewRound reviewRound;
    
    private Review review;
    
    private Employee reviewer;
    
    private Employee recipient;
    

    public OpenRoundReviewView(int index, ReviewRound reviewRound, Review review, Employee reviewer, Employee recipient) {
	super();
	this.index = index;
	this.reviewRound = reviewRound;
	this.review = review;
	this.reviewer = reviewer;
	this.recipient = recipient;
    }
    
    

    public int getIndex() {
        return index;
    }



    public ReviewRound getReviewRound() {
        return reviewRound;
    }

    public Review getReview() {
        return review;
    }

    public Employee getReviewer() {
        return reviewer;
    }

    public Employee getRecipient() {
        return recipient;
    }

    
}
