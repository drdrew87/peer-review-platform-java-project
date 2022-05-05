package com.fdm.peer_review.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fdm.peer_review.model.Department;
import com.fdm.peer_review.model.Employee;
import com.fdm.peer_review.model.Review;
import com.fdm.peer_review.model.ReviewRound;
import com.fdm.peer_review.repo.EmployeeRepo;
import com.fdm.peer_review.repo.ReviewRepo;
import com.fdm.peer_review.repo.ReviewRoundRepo;

@Service
public class ReviewRoundService {
    
    @Autowired
    private ReviewRoundRepo reviewRoundRepo;
    @Autowired
    private ReviewRepo reviewRepo;
    @Autowired
    private EmployeeRepo employeeRepo;

    
    public ReviewRound getById(int reviewRoundId) {
	return reviewRoundRepo.getById(reviewRoundId);
    }
    
    public List<ReviewRound> getOpenRoundsByDepartment(Department dept) {
	return reviewRoundRepo.getOpenRoundsByDepartment(dept);
    }
    
    public List<ReviewRound> getClosedRoundsByDepartment(Department dept) {
	return reviewRoundRepo.getClosedRoundsByDepartment(dept);
    }
    
    public void createNewReviewRound(ReviewRound reviewRound) {
	reviewRoundRepo.save(reviewRound);
	
	ArrayList<Employee> employeeList = (ArrayList<Employee>) employeeRepo.getByDepartment(reviewRound.getDepartment());
	
	for (int i = 0; i < employeeList.size(); i++) {
	    for (int j = 0; j < employeeList.size(); j++) {
		if (i != j) {
		    Review newReview = new Review(reviewRound,employeeList.get(i),employeeList.get(j));
		    reviewRepo.save(newReview);
		}
	    }
	}
    }
    
    public Integer autoSelect(Model model, Map<String, ?> inputFlashMap, List<ReviewRound> roundList) {
	Integer selectedRoundId = 0;
	if (inputFlashMap != null && inputFlashMap.get("selectedRoundId")!=null) {
	    selectedRoundId = (Integer) inputFlashMap.get("selectedRoundId");
	    model.addAttribute("selectedRound", reviewRoundRepo.getById(selectedRoundId));
	} else {
	    selectedRoundId = roundList.get(0).getReviewRoundId();
	    model.addAttribute("selectedRound",roundList.get(0));
	    model.addAttribute("selectedRoundId",selectedRoundId);   
	}
	return selectedRoundId;
    }
 }
