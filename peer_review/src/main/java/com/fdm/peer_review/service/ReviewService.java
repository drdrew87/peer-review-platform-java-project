package com.fdm.peer_review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.peer_review.model.Review;
import com.fdm.peer_review.repo.ReviewRepo;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepo reviewRepo;
    
    public List<Review> getClosedRoundCompletedReviews(int reviewRoundId, int recipientId) {
	return reviewRepo.getByReviewRoundIdAndRecipientIdAndCompletionDateIsNotNull(reviewRoundId, recipientId);
    }
    
    public List<Review> getOpenRoundIncompleteReviews(int reviewRoundId, int recipientId) {
    	return reviewRepo.getByReviewRoundIdAndReviewerIdAndCompletionDateIsNull(reviewRoundId, recipientId);
    }
    
    public void save(Review review) {
	reviewRepo.save(review);
    }
}
