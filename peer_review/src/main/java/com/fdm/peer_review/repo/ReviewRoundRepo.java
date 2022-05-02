package com.fdm.peer_review.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdm.peer_review.model.Department;
import com.fdm.peer_review.model.ReviewRound;

@Repository
public interface ReviewRoundRepo extends JpaRepository<ReviewRound, Integer>{

    @Query("SELECT rr FROM ReviewRound rr "
    	+ "WHERE rr.department = :dept "
    	+ "AND rr.completionDeadline < current_date "
    	+ "ORDER BY rr.completionDeadline DESC")
    List<ReviewRound> getClosedRoundsByDepartment(@Param("dept") Department dept);

    @Query("SELECT rr FROM ReviewRound rr "
	    	+ "WHERE rr.department = :dept "
	    	+ "AND rr.completionDeadline > current_date "
	    	+ "ORDER BY rr.completionDeadline ASC")
    List<ReviewRound> getOpenRoundsByDepartment(@Param("dept") Department dept);

}
