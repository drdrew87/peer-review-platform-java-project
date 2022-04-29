package com.fdm.peer_review.repo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.fdm.peer_review.model.Department;
import com.fdm.peer_review.model.ReviewRound;

@DataJpaTest
public class ReviewRoundRepoTest {
    @Autowired
    private ReviewRoundRepo rrRepo;
    @Autowired
    private DepartmentRepo deptRepo;
    
    @BeforeEach
    void setup() {
	Department HR = new Department("HR");
	deptRepo.save(HR);
    }
    
    @Test
    void test_ReviewRoundRepo_saveReviewRoundtoDatabase() {
	ReviewRound round1 = new ReviewRound();
	rrRepo.save(round1);
	ArrayList<ReviewRound> resultList = (ArrayList<ReviewRound>) rrRepo.findAll();
	assertEquals(1, resultList.size());
    }
    
    @Test
    void test_ReviewRoundRepo_deleteReviewRoundFromDatabase() {
	ReviewRound round1 = new ReviewRound();
	rrRepo.save(round1);
	rrRepo.delete(round1);
	ArrayList<ReviewRound> resultList = (ArrayList<ReviewRound>) rrRepo.findAll();
	assertEquals(0, resultList.size());
    }
}
