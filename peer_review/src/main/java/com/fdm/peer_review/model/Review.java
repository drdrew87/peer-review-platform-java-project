package com.fdm.peer_review.model;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

@Component
@Entity
@IdClass(ReviewPK.class)
public class Review {
    
    @Id
    @JoinColumn(name = "review_round_id", referencedColumnName = "review_round_id")
    @ManyToOne
    private ReviewRound reviewRound;
    
    @Id
    @JoinColumn(name = "reviewer_id", referencedColumnName = "employee_id")
    @ManyToOne
    private Employee reviewer;
    
    @Id
    @JoinColumn(name = "recipient_id", referencedColumnName = "employee_id")
    @ManyToOne
    private Employee recipient;
    
    @Column(name = "completion_date")
    private Date completionDate;
    
    @Column(name = "quality_of_work_rating")
    private int qualityOfWorkRating;
    
    @Column(name = "punctuality_rating")
    private int punctualityRating;
    
    @Column(name = "reliability_rating")
    private int reliabilityRating;
    
    @Column(name = "communication_skill_rating")
    private int communicationSkillRating;
    
    @Column(name = "decision_making_rating")
    private int decisionMakingRating;
    
    @Column(name = "initiative_rating")
    private int initiativeRating;
    
    @Column(name = "teamwork_rating")
    private int teamworkRating;
    
    @Column(name = "knowledge_rating")
    private int knowledgeRating;
    
    @Column(name = "comment")
    private String comment;

    public Review() {
	super();
    }
    
    
    public Review(ReviewRound reviewRound, Employee reviewer, Employee recipient) {
	super();
	this.reviewRound = reviewRound;
	this.reviewer = reviewer;
	this.recipient = recipient;
    }
    
    

    public Review(ReviewRound reviewRound, Employee reviewer, Employee recipient, Date completionDate) {
	super();
	this.reviewRound = reviewRound;
	this.reviewer = reviewer;
	this.recipient = recipient;
	this.completionDate = completionDate;
    }


    public Review(ReviewRound reviewRound, Employee reviewer, Employee recipient, Date completionDate,
	    int qualityOfWorkRating, int punctualityRating, int reliabilityRating, int communicationSkillRating,
	    int decisionMakingRating, int initiativeRating, int teamworkRating, int knowledgeRating) {
	super();
	this.reviewRound = reviewRound;
	this.reviewer = reviewer;
	this.recipient = recipient;
	this.completionDate = completionDate;
	
	if (qualityOfWorkRating<0) {
            this.qualityOfWorkRating = 0;
        } else if (qualityOfWorkRating>5) {
            this.qualityOfWorkRating = 5;
        } else {
            this.qualityOfWorkRating = qualityOfWorkRating;
	}
	
	if (punctualityRating<0) {
            this.punctualityRating = 0;
        } else if (punctualityRating>5) {
            this.punctualityRating = 5;
        } else {
            this.punctualityRating = punctualityRating;
	}
	
	if (reliabilityRating<0) {
            this.reliabilityRating = 0;
        } else if (reliabilityRating>5) {
            this.reliabilityRating = 5;
        } else {
            this.reliabilityRating = reliabilityRating;
	}
	
	if (communicationSkillRating<0) {
            this.communicationSkillRating = 0;
        } else if (communicationSkillRating>5) {
            this.communicationSkillRating = 5;
        } else {
            this.communicationSkillRating = communicationSkillRating;
	}
	
	if (decisionMakingRating<0) {
            this.decisionMakingRating = 0;
        } else if (decisionMakingRating>5) {
            this.decisionMakingRating = 5;
        } else {
            this.decisionMakingRating = decisionMakingRating;
	}
	
	if (initiativeRating<0) {
            this.initiativeRating = 0;
        } else if (initiativeRating>5) {
            this.initiativeRating = 5;
        } else {
            this.initiativeRating = initiativeRating;
	}
	
	if (teamworkRating<0) {
            this.teamworkRating = 0;
        } else if (teamworkRating>5) {
            this.teamworkRating = 5;
        } else {
            this.teamworkRating = teamworkRating;
	}
	
	if (knowledgeRating<0) {
            this.knowledgeRating = 0;
        } else if (knowledgeRating>5) {
            this.knowledgeRating = 5;
        } else {
            this.knowledgeRating = knowledgeRating;
	}
    }

    public ReviewRound getReviewRound() {
        return reviewRound;
    }

    public void setReviewRound(ReviewRound reviewRound) {
        this.reviewRound = reviewRound;
    }

    public Employee getReviewer() {
        return reviewer;
    }

    public void setReviewer(Employee reviewer) {
        this.reviewer = reviewer;
    }

    public Employee getRecipient() {
        return recipient;
    }

    public void setRecipient(Employee recipient) {
        this.recipient = recipient;
    }

    public void setRecipientId(Employee recipientId) {
        this.recipient = recipientId;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public int getQualityOfWorkRating() {
        return qualityOfWorkRating;
    }

    public void setQualityOfWorkRating(int qualityOfWorkRating) {
        if (qualityOfWorkRating<0) {
            this.qualityOfWorkRating = 0;
        } else if (qualityOfWorkRating>5) {
            this.qualityOfWorkRating = 5;
        } else {
            this.qualityOfWorkRating = qualityOfWorkRating;
	}
    }

    public int getPunctualityRating() {
        return punctualityRating;
    }

    public void setPunctualityRating(int punctualityRating) {
	if (punctualityRating<0) {
            this.punctualityRating = 0;
        } else if (punctualityRating>5) {
            this.punctualityRating = 5;
        } else {
            this.punctualityRating = punctualityRating;
	}
    }

    public int getReliabilityRating() {
        return reliabilityRating;
    }

    public void setReliabilityRating(int reliabilityRating) {
	if (reliabilityRating<0) {
            this.reliabilityRating = 0;
        } else if (reliabilityRating>5) {
            this.reliabilityRating = 5;
        } else {
            this.reliabilityRating = reliabilityRating;
	}
    }

    public int getCommunicationSkillRating() {
        return communicationSkillRating;
    }

    public void setCommunicationSkillRating(int communicationSkillRating) {
	if (communicationSkillRating<0) {
            this.communicationSkillRating = 0;
        } else if (communicationSkillRating>5) {
            this.communicationSkillRating = 5;
        } else {
            this.communicationSkillRating = communicationSkillRating;
	}
    }

    public int getDecisionMakingRating() {
        return decisionMakingRating;
    }

    public void setDecisionMakingRating(int decisionMakingRating) {
	if (decisionMakingRating<0) {
            this.decisionMakingRating = 0;
        } else if (decisionMakingRating>5) {
            this.decisionMakingRating = 5;
        } else {
            this.decisionMakingRating = decisionMakingRating;
	}
    }

    public int getInitiativeRating() {
        return initiativeRating;
    }

    public void setInitiativeRating(int initiativeRating) {
	if (initiativeRating<0) {
            this.initiativeRating = 0;
        } else if (initiativeRating>5) {
            this.initiativeRating = 5;
        } else {
            this.initiativeRating = initiativeRating;
	}
    }

    public int getTeamworkRating() {
        return teamworkRating;
    }

    public void setTeamworkRating(int teamworkRating) {
	if (teamworkRating<0) {
            this.teamworkRating = 0;
        } else if (teamworkRating>5) {
            this.teamworkRating = 5;
        } else {
            this.teamworkRating = teamworkRating;
	}
    }

    public int getKnowledgeRating() {
        return knowledgeRating;
    }

    public void setKnowledgeRating(int knowledgeRating) {
	if (knowledgeRating<0) {
            this.knowledgeRating = 0;
        } else if (knowledgeRating>5) {
            this.knowledgeRating = 5;
        } else {
            this.knowledgeRating = knowledgeRating;
	}
    }

    public String getComment() {
        return comment;
    }


    public void setComment(String comment) {
        this.comment = comment;
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
	Review other = (Review) obj;
	return Objects.equals(recipient, other.recipient) && Objects.equals(reviewRound, other.reviewRound)
		&& Objects.equals(reviewer, other.reviewer);
    }


}
