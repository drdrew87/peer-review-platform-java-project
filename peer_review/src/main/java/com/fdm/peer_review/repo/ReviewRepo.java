package com.fdm.peer_review.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    
    @Query("SELECT r FROM Review r "
    	+ "WHERE r.reviewRoundId = :reviewRoundId "
    	+ "AND r.recipientId = :recipientId")
    List<Review> getByRoundIdAndRecipientId(@Param("reviewRoundId") int reviewRoundId, @Param("recipientId") int recipientId);
    
    @Query("SELECT r FROM Review r "
    	+ "WHERE r.reviewRoundId = :reviewRoundId "
    	+ "AND r.recipientId = :recipientId")
    Optional<List<Review>> findByRoundIdAndRecipientId(@Param("reviewRoundId") int reviewRoundId, @Param("recipientId") int recipientId);
    
    @Query("SELECT r FROM Review r "
	+ "WHERE r.reviewRoundId = :reviewRoundId "
	+ "AND r.reviewerId = :reviewerId")
    List<Review> getByRoundIdAndReviewerId(@Param("reviewRoundId") int reviewRoundId, @Param("reviewerId") int reviewerId);
    
    @Query("SELECT r FROM Review r "
	+ "WHERE r.reviewRoundId = :reviewRoundId "
	+ "AND r.reviewerId = :reviewerId")
    Optional<List<Review>> findByRoundIdAndReviewerId(@Param("reviewRoundId") int reviewRoundId, @Param("reviewerId") int reviewerId);
    
}
