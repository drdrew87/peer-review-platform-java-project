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
    
    
    ReviewRound reviewRound;
    Employee reviewer;
    Employee recipient;
    
    public ReviewPK() {
	super();
    }
    
    public ReviewPK(ReviewRound reviewRound, Employee reviewer, Employee recipient) {
	super();
	this.reviewRound = reviewRound;
	this.reviewer = reviewer;
	this.recipient = recipient;
    }

    @Override
    public int hashCode() {
	return Objects.hash(recipient, reviewRound, reviewer);
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
	return Objects.equals(recipient, other.recipient) && Objects.equals(reviewRound, other.reviewRound)
		&& Objects.equals(reviewer, other.reviewer);
    }
}
