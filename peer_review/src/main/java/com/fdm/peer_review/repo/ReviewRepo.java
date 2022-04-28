package com.fdm.peer_review.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdm.peer_review.model.Review;
import com.fdm.peer_review.model.ReviewPK;

@Repository
public interface ReviewRepo extends JpaRepository<Review, ReviewPK>{

}
