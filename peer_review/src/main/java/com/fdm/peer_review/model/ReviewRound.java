package com.fdm.peer_review.model;

import java.sql.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

@Component
@Entity
public class ReviewRound {
    @Id
    @SequenceGenerator(name = "RVRND_SEQ_GNTR", sequenceName = "RVRND_SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "RVRND_SEQ_GNTR")
    @Column(name = "review_round_id")
    private int reviewRoundId;
    
    @Column(name = "review_round_name")
    private String reviewRoundName;
    
    @Column(name = "completion_deadline")
    private Date completionDeadline;
    
    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    private Department department;
    

    public ReviewRound() {
	super();
    }
    
    
    public ReviewRound(String reviewRoundName, Date completionDeadline, Department department) {
	super();
	this.reviewRoundName = reviewRoundName;
	this.completionDeadline = completionDeadline;
	this.department = department;
    }

    
    public int getReviewRoundId() {
        return reviewRoundId;
    }

    public void setReviewRoundId(int reviewRoundId) {
        this.reviewRoundId = reviewRoundId;
    }

    public String getReviewRoundName() {
        return reviewRoundName;
    }

    public void setReviewRoundName(String reviewRoundName) {
        this.reviewRoundName = reviewRoundName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    
    public Date getCompletionDeadline() {
        return completionDeadline;
    }


    public void setCompletionDeadline(Date completionDeadline) {
        this.completionDeadline = completionDeadline;
    }


    @Override
    public int hashCode() {
	return Objects.hash(department, reviewRoundId, reviewRoundName);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	ReviewRound other = (ReviewRound) obj;
	return Objects.equals(department, other.department) && reviewRoundId == other.reviewRoundId
		&& Objects.equals(reviewRoundName, other.reviewRoundName);
    }
}
