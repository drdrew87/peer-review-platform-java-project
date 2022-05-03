package com.fdm.peer_review.view;

import java.util.ArrayList;
import java.util.List;

import com.fdm.peer_review.model.Employee;
import com.fdm.peer_review.model.Review;

public class ReviewAveragesView {
    
    private Employee user;
    private int reviewCount;
    
    private double averageQualityOfWorkRating;
    
    private double averagePunctualityRating;
    
    private double averageReliabilityRating;
    
    private double averageCommunicationSkillRating;
    
    private double averageDecisionMakingRating;
    
    private double averageInitiativeRating;
    
    private double averageTeamworkRating;
    
    private double averageKnowledgeRating;
    
    private List<String> commentList = new ArrayList<String>();
    
    public ReviewAveragesView(Employee user, List<Review> reviewList) {
	this.user = user;
	this.reviewCount = reviewList.size();
	
	double sumQualityOfWorkRating = 0;
	double sumPunctualityRating = 0;
	double sumReliabilityRating = 0;
	double sumCommunicationSkillRating = 0;
	double sumDecisionMakingRating = 0;
	double sumInitiativeRating = 0;
	double sumTeamworkRating = 0;
	double sumKnowledgeRating = 0;
	
	for (Review review : reviewList) {
	    sumQualityOfWorkRating += review.getQualityOfWorkRating();
	    sumPunctualityRating += review.getPunctualityRating();
	    sumReliabilityRating += review.getReliabilityRating();
	    sumCommunicationSkillRating += review.getCommunicationSkillRating();
	    sumDecisionMakingRating += review.getDecisionMakingRating();
	    sumInitiativeRating += review.getInitiativeRating();
	    sumTeamworkRating += review.getTeamworkRating();
	    sumKnowledgeRating += review.getKnowledgeRating();
	    
	    if (review.getComment()!=null) {
		this.commentList.add(review.getComment());
	    }
	}
	
	this.averageQualityOfWorkRating = sumQualityOfWorkRating / this.reviewCount;
	this.averagePunctualityRating = sumPunctualityRating / this.reviewCount;
	this.averageReliabilityRating = sumReliabilityRating /this.reviewCount;
	this.averageCommunicationSkillRating = sumCommunicationSkillRating / this.reviewCount;
	this.averageDecisionMakingRating = sumDecisionMakingRating / this.reviewCount;
	this.averageInitiativeRating = sumInitiativeRating / this.reviewCount;
	this.averageTeamworkRating = sumTeamworkRating / this.reviewCount;
	this.averageKnowledgeRating = sumKnowledgeRating / this.reviewCount;
	
	
    }

    public Employee getUser() {
        return user;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public double getAverageQualityOfWorkRating() {
        return averageQualityOfWorkRating;
    }

    public double getAveragePunctualityRating() {
        return averagePunctualityRating;
    }

    public double getAverageReliabilityRating() {
        return averageReliabilityRating;
    }

    public double getAverageCommunicationSkillRating() {
        return averageCommunicationSkillRating;
    }

    public double getAverageDecisionMakingRating() {
        return averageDecisionMakingRating;
    }

    public double getAverageInitiativeRating() {
        return averageInitiativeRating;
    }

    public double getAverageTeamworkRating() {
        return averageTeamworkRating;
    }

    public double getAverageKnowledgeRating() {
        return averageKnowledgeRating;
    }

    public List<String> getCommentList() {
        return commentList;
    }
}
