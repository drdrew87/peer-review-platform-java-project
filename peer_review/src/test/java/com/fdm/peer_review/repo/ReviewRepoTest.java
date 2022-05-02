package com.fdm.peer_review.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.fdm.peer_review.model.Department;
import com.fdm.peer_review.model.Employee;
import com.fdm.peer_review.model.Permission;
import com.fdm.peer_review.model.Review;
import com.fdm.peer_review.model.ReviewPK;
import com.fdm.peer_review.model.ReviewRound;

@DataJpaTest
public class ReviewRepoTest {
    @Autowired
    private ReviewRepo RRepo;
    @Autowired
    private EmployeeRepo emRepo;
    @Autowired
    private ReviewRoundRepo RRRepo;
    @Autowired
    private PermissionRepo permRepo;
    @Autowired
    private DepartmentRepo deptRepo;
    private Department dept;
    private Permission perm;
    private ReviewRound reviewRound1;
    private ReviewRound reviewRound2;
    private Employee em1;
    private Employee em2;
    private Employee em3;
    
    @BeforeEach
    void setUp() {
	dept = new Department("Sales");
	deptRepo.save(dept);
	
	perm = new Permission(false, false);
	permRepo.save(perm);
	
	reviewRound1 = new ReviewRound("New round of review", Date.valueOf("2022-04-30"),dept);
	RRRepo.save(reviewRound1);
	reviewRound2 = new ReviewRound("Second round of review", Date.valueOf("2022-05-30"),dept);
	RRRepo.save(reviewRound2);
	

	em1 = new Employee("John", "Doe", "joe.doe", "12345","M", dept, perm);
	em2 = new Employee("Foo", "Bar", "foo.bar", "54321","M", dept, perm);
	em3 = new Employee("Micky", "Mouse", "mmouse", "abc123","M", dept, perm);
	emRepo.save(em1);
	emRepo.save(em2);
	emRepo.save(em3);
    }
    
    @Test
    void test_ReviewRepo_saveReviewToDatabase() {
	
	Review review1 = new Review(new ReviewPK(reviewRound1, em3, em1));
	RRepo.save(review1);

	ArrayList<Review> resultList = (ArrayList<Review>) RRepo.findAll();
	assertEquals(1, resultList.size());
    }
    
    @Test
    void test_ReviewRepo_getReviewById() {
	Review review1 = new Review(new ReviewPK(reviewRound1, em3, em1));
	RRepo.save(review1);
	Review result = RRepo.getById(new ReviewPK(reviewRound1.getReviewRoundId(),em3.getEmployeeId(),em1.getEmployeeId()));
	assertEquals(reviewRound1.getReviewRoundId(), result.getReviewRoundId());
	assertEquals(em3.getEmployeeId(), result.getReviewerId());
	assertEquals(em1.getEmployeeId(), result.getRecipientId());
    }
    
    @Test
    void test_ReviewRepo_getReviewByReviewRoundId() {
	Review review1 = new Review(new ReviewPK(reviewRound1, em3, em1));
	Review review2 = new Review(new ReviewPK(reviewRound1, em3, em2));
	Review review3 = new Review(new ReviewPK(reviewRound2, em2, em1));
	RRepo.save(review1);
	RRepo.save(review2);
	RRepo.save(review3);
	ArrayList<Review> resultList1 = (ArrayList<Review>) RRepo.getByReviewRoundId(reviewRound1.getReviewRoundId());
	assertEquals(2, resultList1.size());
	ArrayList<Review> resultList2 = (ArrayList<Review>) RRepo.getByReviewRoundId(reviewRound2.getReviewRoundId());
	assertEquals(1, resultList2.size());
    }
    
    @Test
    void test_ReviewRepo_getReviewByReviewerId() {
	Review review1 = new Review(new ReviewPK(reviewRound1, em3, em1));
	Review review2 = new Review(new ReviewPK(reviewRound1, em3, em2));
	Review review3 = new Review(new ReviewPK(reviewRound2, em2, em1));
	Review review4 = new Review(new ReviewPK(reviewRound2, em2, em3));
	RRepo.save(review1);
	RRepo.save(review2);
	RRepo.save(review3);
	RRepo.save(review4);
	ArrayList<Review> resultList1 = (ArrayList<Review>) RRepo.getByReviewerId(em3.getEmployeeId());
	assertEquals(2, resultList1.size());
	ArrayList<Review> resultList2 = (ArrayList<Review>) RRepo.getByReviewerId(em2.getEmployeeId());
	assertEquals(2, resultList2.size());
	ArrayList<Review> resultList3 = (ArrayList<Review>) RRepo.getByReviewerId(em1.getEmployeeId());
	assertEquals(0, resultList3.size());
    }
    
    @Test
    void test_ReviewRepo_getReviewByRecipientId() {
	Review review1 = new Review(new ReviewPK(reviewRound1, em3, em1));
	Review review2 = new Review(new ReviewPK(reviewRound1, em3, em2));
	Review review3 = new Review(new ReviewPK(reviewRound2, em2, em1));
	Review review4 = new Review(new ReviewPK(reviewRound2, em2, em3));
	RRepo.save(review1);
	RRepo.save(review2);
	RRepo.save(review3);
	RRepo.save(review4);
	ArrayList<Review> resultList1 = (ArrayList<Review>) RRepo.getByRecipientId(em3.getEmployeeId());
	assertEquals(1, resultList1.size());
	ArrayList<Review> resultList2 = (ArrayList<Review>) RRepo.getByRecipientId(em2.getEmployeeId());
	assertEquals(1, resultList2.size());
	ArrayList<Review> resultList3 = (ArrayList<Review>) RRepo.getByRecipientId(em1.getEmployeeId());
	assertEquals(2, resultList3.size());
    }
    
    @Test
    void test_ReviewRepo_getReviewByRoundIdAndRecipientId() {
	Review review1 = new Review(new ReviewPK(reviewRound1, em3, em1));
	Review review2 = new Review(new ReviewPK(reviewRound1, em3, em2));
	Review review3 = new Review(new ReviewPK(reviewRound2, em2, em1));
	Review review4 = new Review(new ReviewPK(reviewRound2, em2, em3));
	RRepo.save(review1);
	RRepo.save(review2);
	RRepo.save(review3);
	RRepo.save(review4);
	ArrayList<Review> resultList1 = (ArrayList<Review>) RRepo.getByReviewRoundIdAndRecipientId(reviewRound1.getReviewRoundId(), em3.getEmployeeId());
	assertEquals(0, resultList1.size());
	ArrayList<Review> resultList2 = (ArrayList<Review>) RRepo.getByReviewRoundIdAndRecipientId(reviewRound2.getReviewRoundId(), em1.getEmployeeId());
	assertEquals(1, resultList2.size());
	ArrayList<Review> resultList3 = (ArrayList<Review>) RRepo.getByReviewRoundIdAndRecipientId(reviewRound1.getReviewRoundId(), em3.getEmployeeId());
	assertEquals(0, resultList3.size());
    }
    
    @Test
    void test_ReviewRepo_getReviewByRoundIdAndReviewerId() {
	Review review1 = new Review(new ReviewPK(reviewRound1, em3, em1));
	Review review2 = new Review(new ReviewPK(reviewRound1, em3, em2));
	Review review3 = new Review(new ReviewPK(reviewRound2, em2, em1));
	Review review4 = new Review(new ReviewPK(reviewRound2, em2, em3));
	RRepo.save(review1);
	RRepo.save(review2);
	RRepo.save(review3);
	RRepo.save(review4);
	ArrayList<Review> resultList1 = (ArrayList<Review>) RRepo.getByReviewRoundIdAndReviewerId(reviewRound1.getReviewRoundId(), em3.getEmployeeId());
	assertEquals(2, resultList1.size());
	ArrayList<Review> resultList2 = (ArrayList<Review>) RRepo.getByReviewRoundIdAndReviewerId(reviewRound2.getReviewRoundId(), em1.getEmployeeId());
	assertEquals(0, resultList2.size());
	ArrayList<Review> resultList3 = (ArrayList<Review>) RRepo.getByReviewRoundIdAndReviewerId(reviewRound2.getReviewRoundId(), em2.getEmployeeId());
	assertEquals(2, resultList3.size());
    }
    
    @Test
    void test_confirm_PKConstraintIsWorking() {
	Review review1 = new Review(new ReviewPK(reviewRound1, em3, em1));
	RRepo.save(review1);
	Review review2 = new Review(new ReviewPK(reviewRound1, em3, em2));
	RRepo.save(review2);
	
	Review review3 = new Review(new ReviewPK(reviewRound2, em2, em1));
	RRepo.save(review3);
	
	Review review4 = new Review(new ReviewPK(reviewRound2, em2, em3));
	RRepo.save(review4);
	ArrayList<Review> resultList1 = (ArrayList<Review>) RRepo.findAll();
	assertEquals(4, resultList1.size());
	
	Review review5 = new Review(new ReviewPK(reviewRound1, em3, em1));
	RRepo.save(review5);
	ArrayList<Review> resultList2 = (ArrayList<Review>) RRepo.findAll();
	assertEquals(4, resultList2.size());
	
	Review review6 = new Review(new ReviewPK(reviewRound1, em3, em2));
	RRepo.save(review6);
	ArrayList<Review> resultList3 = (ArrayList<Review>) RRepo.findAll();
	assertEquals(4, resultList3.size());
	
	Review review7 = new Review(new ReviewPK(reviewRound2, em3, em1));
	RRepo.save(review7);
	ArrayList<Review> resultList4 = (ArrayList<Review>) RRepo.findAll();
	assertEquals(5, resultList4.size());
	
	Review review8 = new Review(new ReviewPK(reviewRound2, em3, em2));
	RRepo.save(review8);
	ArrayList<Review> resultList5 = (ArrayList<Review>) RRepo.findAll();
	assertEquals(6, resultList5.size());
    }

    @Test
    void tes_ReviewRepo_getByReviewRoundIdIn() {
	Review review1 = new Review(new ReviewPK(reviewRound1, em3, em1));
	RRepo.save(review1);
	Review review2 = new Review(new ReviewPK(reviewRound1, em3, em2));
	RRepo.save(review2);
	Review review3 = new Review(new ReviewPK(reviewRound2, em2, em1));
	RRepo.save(review3);
	Review review4 = new Review(new ReviewPK(reviewRound2, em2, em3));
	RRepo.save(review4);
	List<Integer> reviewRoundList = new ArrayList<Integer>();
	ArrayList<Review> resultList = (ArrayList<Review>) RRepo.getByReviewRoundIdIn(reviewRoundList);
	assertEquals(0, resultList.size());
	
	reviewRoundList.add(reviewRound1.getReviewRoundId());
	resultList = (ArrayList<Review>) RRepo.getByReviewRoundIdIn(reviewRoundList);
	assertEquals(2, resultList.size());
	
	reviewRoundList.add(reviewRound2.getReviewRoundId());
	resultList = (ArrayList<Review>) RRepo.getByReviewRoundIdIn(reviewRoundList);
	assertEquals(4, resultList.size());
    } 
    
    @Test
    void tes_ReviewRepo_getByCompletionDateIsNullAndReviewRoundIdIn() {
	Review review1 = new Review(new ReviewPK(reviewRound1, em3, em1));
	RRepo.save(review1);
	Review review2 = new Review(new ReviewPK(reviewRound1, em3, em2));
	RRepo.save(review2);
	Review review3 = new Review(new ReviewPK(reviewRound2, em2, em1));
	RRepo.save(review3);
	Review review4 = new Review(new ReviewPK(reviewRound2, em2, em3));
	RRepo.save(review4);
	List<Integer> reviewRoundList = new ArrayList<Integer>();
	ArrayList<Review> resultList = (ArrayList<Review>) RRepo.getByCompletionDateIsNullAndReviewRoundIdIn(reviewRoundList);
	assertEquals(0, resultList.size());
	
	reviewRoundList.add(reviewRound1.getReviewRoundId());
	resultList = (ArrayList<Review>) RRepo.getByCompletionDateIsNullAndReviewRoundIdIn(reviewRoundList);
	assertEquals(2, resultList.size());
	
	reviewRoundList.add(reviewRound2.getReviewRoundId());
	resultList = (ArrayList<Review>) RRepo.getByCompletionDateIsNullAndReviewRoundIdIn(reviewRoundList);
	assertEquals(4, resultList.size());
	
	review4.setCompletionDate(new Date(new java.util.Date().getTime()));
	RRepo.save(review4);
	resultList = (ArrayList<Review>) RRepo.getByCompletionDateIsNullAndReviewRoundIdIn(reviewRoundList);
	assertEquals(3, resultList.size());
	
	review1.setCompletionDate(new Date(new java.util.Date().getTime()));
	RRepo.save(review1);
	review2.setCompletionDate(new Date(new java.util.Date().getTime()));
	RRepo.save(review2);
	resultList = (ArrayList<Review>) RRepo.getByCompletionDateIsNullAndReviewRoundIdIn(reviewRoundList);
	assertEquals(1, resultList.size());
    } 
    
    @Test
    void tes_ReviewRepo_getByCompletionDateIsNotNullAndReviewRoundIdIn() {
	Review review1 = new Review(new ReviewPK(reviewRound1, em3, em1));
	RRepo.save(review1);
	Review review2 = new Review(new ReviewPK(reviewRound1, em3, em2));
	RRepo.save(review2);
	Review review3 = new Review(new ReviewPK(reviewRound2, em2, em1));
	RRepo.save(review3);
	Review review4 = new Review(new ReviewPK(reviewRound2, em2, em3));
	RRepo.save(review4);
	List<Integer> reviewRoundList = new ArrayList<Integer>();
	
	ArrayList<Review> resultList = (ArrayList<Review>) RRepo.getByCompletionDateIsNotNullAndReviewRoundIdIn(reviewRoundList);
	assertEquals(0, resultList.size());
	
	reviewRoundList.add(reviewRound1.getReviewRoundId());
	resultList = (ArrayList<Review>) RRepo.getByCompletionDateIsNotNullAndReviewRoundIdIn(reviewRoundList);
	assertEquals(0, resultList.size());
	
	reviewRoundList.add(reviewRound2.getReviewRoundId());
	resultList = (ArrayList<Review>) RRepo.getByCompletionDateIsNotNullAndReviewRoundIdIn(reviewRoundList);
	assertEquals(0, resultList.size());
	
	review4.setCompletionDate(new Date(new java.util.Date().getTime()));
	RRepo.save(review4);
	resultList = (ArrayList<Review>) RRepo.getByCompletionDateIsNotNullAndReviewRoundIdIn(reviewRoundList);
	assertEquals(1, resultList.size());
	
	review1.setCompletionDate(new Date(new java.util.Date().getTime()));
	RRepo.save(review1);
	review2.setCompletionDate(new Date(new java.util.Date().getTime()));
	RRepo.save(review2);
	resultList = (ArrayList<Review>) RRepo.getByCompletionDateIsNotNullAndReviewRoundIdIn(reviewRoundList);
	assertEquals(3, resultList.size());
    } 
}
