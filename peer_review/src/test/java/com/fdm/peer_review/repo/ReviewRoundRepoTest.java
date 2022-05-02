package com.fdm.peer_review.repo;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
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
    
    private Department HR;
    private Department sales;
    
    @BeforeEach
    void setup() {
	HR = new Department("HR");
	deptRepo.save(HR);
	sales = new Department("Sales");
	deptRepo.save(sales);
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
    
    @Test
    void test_ReviewRoundRepo_getClosedRoundsByDepartment() {
	ReviewRound round1 = new ReviewRound("round1", Date.valueOf("2022-05-02"),sales);
	rrRepo.save(round1);
	ReviewRound round2 = new ReviewRound("round2", Date.valueOf("2021-05-02"),HR);
	rrRepo.save(round2);
	ArrayList<ReviewRound> resultList1 = (ArrayList<ReviewRound>) rrRepo.getClosedRoundsByDepartment(HR);
	assertEquals(1, resultList1.size());
	ReviewRound round3 = new ReviewRound("round3", Date.valueOf("2022-04-02"),HR);
	rrRepo.save(round3);
	ArrayList<ReviewRound> resultList2 = (ArrayList<ReviewRound>) rrRepo.getClosedRoundsByDepartment(HR);
	assertEquals(2, resultList2.size());
	assertEquals("round3", resultList2.get(0).getReviewRoundName());
	ReviewRound round4 = new ReviewRound("round4", Date.valueOf("2022-06-02"),HR);
	rrRepo.save(round4);
	ArrayList<ReviewRound> resultList3 = (ArrayList<ReviewRound>) rrRepo.getClosedRoundsByDepartment(HR);
	assertEquals(2, resultList3.size());
    }
    
    @Test
    void test_ReviewRoundRepo_getOpenRoundsByDepartment() {
	ReviewRound round1 = new ReviewRound("round1", Date.valueOf("2020-05-02"),sales);
	rrRepo.save(round1);
	ReviewRound round2 = new ReviewRound("round2", Date.valueOf("2021-05-02"),HR);
	rrRepo.save(round2);
	ArrayList<ReviewRound> resultList1 = (ArrayList<ReviewRound>) rrRepo.getOpenRoundsByDepartment(HR);
	assertEquals(0, resultList1.size());
	ReviewRound round3 = new ReviewRound("round3", Date.valueOf("2047-06-02"),HR);
	rrRepo.save(round3);
	ArrayList<ReviewRound> resultList2 = (ArrayList<ReviewRound>) rrRepo.getOpenRoundsByDepartment(HR);
	assertEquals(1, resultList2.size());
	ReviewRound round4 = new ReviewRound("round4", Date.valueOf("2046-05-05"),HR);
	rrRepo.save(round4);
	ReviewRound round5 = new ReviewRound("round5", Date.valueOf("2022-06-01"),HR);
	rrRepo.save(round5);
	ArrayList<ReviewRound> resultList3 = (ArrayList<ReviewRound>) rrRepo.getOpenRoundsByDepartment(HR);
	assertEquals(3, resultList3.size());
	assertEquals("round5", resultList3.get(0).getReviewRoundName());
	
    }
}
