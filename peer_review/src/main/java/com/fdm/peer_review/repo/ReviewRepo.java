package com.fdm.peer_review.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdm.peer_review.model.Review;
import com.fdm.peer_review.model.ReviewPK;

public interface ReviewRepo extends JpaRepository<Review, ReviewPK>{

}
