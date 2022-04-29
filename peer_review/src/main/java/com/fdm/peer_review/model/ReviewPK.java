package com.fdm.peer_review.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;


public class ReviewPK implements Serializable{

    private static final long serialVersionUID = 1L;
    
    
    private int reviewRoundId;
    private int reviewerId;
    private int recipientId;
    
    public ReviewPK() {
	super();
    }
    
    

    public ReviewPK(int reviewRoundId, int reviewerId, int recipientId) {
	super();
	this.reviewRoundId = reviewRoundId;
	this.reviewerId = reviewerId;
	this.recipientId = recipientId;
    }
    
    public ReviewPK(ReviewRound reviewRound, Employee reviewer, Employee recipient) {
	super();
	this.reviewRoundId = reviewRound.getReviewRoundId();
	this.reviewerId = reviewer.getEmployeeId();
	this.recipientId = recipient.getEmployeeId();
    }
    
    


    public int getReviewRoundId() {
        return reviewRoundId;
    }



    public void setReviewRoundId(int reviewRoundId) {
        this.reviewRoundId = reviewRoundId;
    }



    public int getReviewerId() {
        return reviewerId;
    }



    public void setReviewerId(int reviewerId) {
        this.reviewerId = reviewerId;
    }



    public int getRecipientId() {
        return recipientId;
    }



    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }



    @Override
    public int hashCode() {
	return Objects.hash(recipientId, reviewRoundId, reviewerId);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	ReviewPK other = (ReviewPK) obj;
	return recipientId == other.recipientId && reviewRoundId == other.reviewRoundId
		&& reviewerId == other.reviewerId;
    }
    
    
}
