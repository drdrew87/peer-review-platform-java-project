package com.fdm.peer_review.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdm.peer_review.model.ReviewRound;

public interface ReviewRoundRepo extends JpaRepository<ReviewRound, Integer>{

}
