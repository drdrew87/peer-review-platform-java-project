package com.fdm.peer_review.repo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdm.peer_review.model.Review;
import com.fdm.peer_review.model.ReviewPK;

@Repository
public interface ReviewRepo extends JpaRepository<Review, ReviewPK>{

    List<Review> getByReviewRoundId(int reviewRoundId);
    Optional<List<Review>> findByReviewRoundId(int reviewRoundId);
    
    List<Review> getByReviewerId(int reviewRoundId);
    Optional<List<Review>> findByReviewerId(int reviewRoundId);
    
    List<Review> getByRecipientId(int employeeId);
    Optional<List<Review>> findByRecipientId(int employeeId);
    

    List<Review> getByReviewRoundIdAndRecipientId(int reviewRoundId, int recipientId);
    
    Optional<List<Review>> findByReviewRoundIdAndRecipientId( int reviewRoundId, int recipientId);
    
    
    List<Review> getByReviewRoundIdAndRecipientIdAndCompletionDateIsNotNull(int reviewRoundId, int recipientId);
    
    Optional<List<Review>> findByReviewRoundIdAndRecipientIdAndCompletionDateIsNotNull(int reviewRoundId, int recipientId);
    
    List<Review> getByReviewRoundIdAndRecipientIdAndCompletionDateIsNull(int reviewRoundId, int recipientId);
    
    Optional<List<Review>> findByReviewRoundIdAndRecipientIdAndCompletionDateIsNull(int reviewRoundId, int recipientId);
    
    List<Review> getByReviewRoundIdAndReviewerIdAndCompletionDateIsNull(int reviewRoundId, int employeeId);
    
    Optional<List<Review>> findByReviewRoundIdAndReviewerIdAndCompletionDateIsNull(int reviewRoundId, int employeeId);
    
    List<Review> getByReviewRoundIdAndReviewerIdAndCompletionDateIsNotNull(int reviewRoundId, int recipientId);

    Optional<List<Review>> findByReviewRoundIdAndReviewerIdAndCompletionDateIsNotNull(int reviewRoundId, int recipientId);
    
    List<Review> getByReviewRoundIdAndReviewerId(int reviewRoundId, int reviewerId);
    
    Optional<List<Review>> findByReviewRoundIdAndReviewerId(int reviewRoundId, int reviewerId);
    
    List<Review> getByReviewRoundIdIn(Collection<Integer> reviewRoundList);
    
    Optional<List<Review>> findByReviewRoundIdIn(Collection<Integer> reviewRoundList);
    
    List<Review> getByCompletionDateIsNullAndReviewRoundIdIn(Collection<Integer> reviewRoundList);
    
    Optional<List<Review>> findByCompletionDateIsNullAndReviewRoundIdIn(Collection<Integer> reviewRoundList);

    List<Review> getByCompletionDateIsNotNullAndReviewRoundIdIn(Collection<Integer> reviewRoundList);
    
    Optional<List<Review>> findByCompletionDateIsNotNullAndReviewRoundIdIn(Collection<Integer> reviewRoundList);
    
    
    

}
