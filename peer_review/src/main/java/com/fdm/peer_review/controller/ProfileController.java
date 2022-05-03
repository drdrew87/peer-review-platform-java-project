package com.fdm.peer_review.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.fdm.peer_review.model.Department;
import com.fdm.peer_review.model.Employee;
import com.fdm.peer_review.model.Review;
import com.fdm.peer_review.model.ReviewRound;
import com.fdm.peer_review.repo.DepartmentRepo;
import com.fdm.peer_review.repo.EmployeeRepo;
import com.fdm.peer_review.repo.ReviewRepo;
import com.fdm.peer_review.repo.ReviewRoundRepo;
import com.fdm.peer_review.service.DepartmentListService;
import com.fdm.peer_review.service.ReviewRoundCreationService;
import com.fdm.peer_review.view.ProfileView;
import com.fdm.peer_review.view.ReviewAveragesView;

@Controller
public class ProfileController {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private ReviewRoundRepo reviewRoundRepo;
    @Autowired
    private ReviewRepo reviewRepo;
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private ReviewRoundCreationService roundCreator;
    @Autowired
    private DepartmentListService deptListService;
    
    @GetMapping("/profile")
    public String DirectAccessToProfilePage() {
	return "redirect:/";
    }
    
    @GetMapping("/profile/{username}/MyRatings")
    public String LogInToProfilePage(@PathVariable String username, HttpServletRequest request, Model model) {
	HttpSession session = request.getSession();
	Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
	
	
	if (session!=null && session.getAttribute("username")!=null && username.equals(session.getAttribute("username"))) {
	    Employee currentUser = employeeRepo.getByUserName(username);
	    if (currentUser.getPermission().isDepartmentManager() || currentUser.getPermission().isHR()) {
		session.setAttribute("allTabs", true);
		model.addAttribute("allTabs",true);
	    } else {
		if (currentUser.getDepartment().getDepartmentName().equals("Trainer")) {
		    session.setAttribute("allTabs", true);
		    model.addAttribute("allTabs",true);
		} else {
		    session.setAttribute("allTabs", false);
		}
	    }
	    
	    ArrayList<Department> departmentList = (ArrayList<Department>) deptListService.generateDepartmentList(currentUser);
	    session.setAttribute("departmentList", departmentList);
	    
	    
	    ProfileView userProfile = new ProfileView(currentUser);
	    model.addAttribute("userProfile", userProfile);
	    
	    ArrayList<ReviewRound> reiviewRoundList = new ArrayList<ReviewRound>();
	    HashMap<Integer, ArrayList<Review>> reviewMapByRoundId = new HashMap<Integer, ArrayList<Review>>();
	    ArrayList<ReviewRound> reiviewRoundListInDept = (ArrayList<ReviewRound>) reviewRoundRepo.getClosedRoundsByDepartment(currentUser.getDepartment());
	    for (ReviewRound reviewRound : reiviewRoundListInDept) {
		ArrayList<Review> reviewsList = (ArrayList<Review>) reviewRepo.getByReviewRoundIdAndRecipientIdAndCompletionDateIsNotNull(reviewRound.getReviewRoundId(), currentUser.getEmployeeId());
		if (!(reviewsList.size() < 1)) {
		    reiviewRoundList.add(reviewRound);
		    reviewMapByRoundId.put(reviewRound.getReviewRoundId(),reviewsList);
		}
	    }
	    
	    model.addAttribute("reiviewRoundList", reiviewRoundList);
	    
	    
	    if (reiviewRoundList.size()<1) {
		model.addAttribute("emptyList",true);
	    } else {
		Integer selectedRoundId = 0;
		if (inputFlashMap != null && inputFlashMap.get("selectedRoundId")!=null) {
		    selectedRoundId = (Integer) inputFlashMap.get("selectedRoundId");
		    model.addAttribute("selectedRound", reviewRoundRepo.getById(selectedRoundId));
		} else {
		    selectedRoundId = reiviewRoundList.get(0).getReviewRoundId();
		    model.addAttribute("selectedRound",reiviewRoundList.get(0));
		    model.addAttribute("selectedRoundId",selectedRoundId);
		}
		
		ReviewAveragesView reviewAverages = new ReviewAveragesView(currentUser, reviewMapByRoundId.get(selectedRoundId));
		if (reviewAverages.getCommentList().size()>0) {
		    model.addAttribute("showCommentList", true);
		}
		model.addAttribute("reviewAverages", reviewAverages);
	    }
	    
	    return "profile_my_ratings";
	} else {
	    return "index";
	}
    }
    
    
    @PostMapping("/profile/{username}/MyRatings")
    public String selectReviewRoundToViewRating(@PathVariable String username, @RequestParam String selectedRoundId, RedirectAttributes attributes) {
	attributes.addFlashAttribute("selectedRoundId", Integer.valueOf(selectedRoundId));
	return "redirect:/profile/"+username+"/MyRatings";
    }
    
    
    @GetMapping("/profile/{username}/OpenReviews")
    public String goToOpenReviews(@PathVariable String username, HttpServletRequest request, Model model) {
	HttpSession session = request.getSession();
	if (session!=null && session.getAttribute("username")!=null && username.equals(session.getAttribute("username"))) {
	    if((boolean) session.getAttribute("allTabs")) {
		model.addAttribute("allTabs",true);
	    }
	    return "profile_open_reviews";
	} else {
	    return "index";
	}
    }
    
    @GetMapping("/profile/{username}/TeamReviews")
    public String goToTeamReviews(@PathVariable String username, HttpServletRequest request, Model model) {
	HttpSession session = request.getSession();
	if (session!=null && session.getAttribute("username")!=null && username.equals(session.getAttribute("username"))) {
	    if((boolean) session.getAttribute("allTabs")) {
		model.addAttribute("allTabs",true);
	    }
	    return "profile_team_reviews";
	} else {
	    return "index";
	}
    }
    
    
    @GetMapping("/profile/{username}/CreateReviewRound")
    public String goToCreateReviewRound(@PathVariable String username, HttpServletRequest request, Model model) {
	HttpSession session = request.getSession();
	if (session!=null && session.getAttribute("username")!=null && username.equals(session.getAttribute("username"))) {
	    if((boolean) session.getAttribute("allTabs")) {
		model.addAttribute("allTabs",true);
	    }

	    @SuppressWarnings("unchecked")
	    ArrayList<Department> departmentList = (ArrayList<Department>) session.getAttribute("departmentList");
	    
	   
	    
	    
	    
	    
	    model.addAttribute("departments", departmentList);
	    
	    return "profile_create_review_round";
	} else {
	    return "index";
	}
    }
    
    
    @PostMapping("/profile/{username}/CreateReviewRound")
    public String createANewReviewRound(@PathVariable String username, RedirectAttributes attributes, @RequestParam String reviewRoundName, @RequestParam String completionDeadline, @RequestParam String department) {
	ReviewRound newReviewRound = new ReviewRound();
	newReviewRound.setReviewRoundName(reviewRoundName);
	newReviewRound.setCompletionDeadline(Date.valueOf(completionDeadline));
	newReviewRound.setDepartment(departmentRepo.getById(Integer.valueOf(department)));
	
	roundCreator.createNewReviewRound(newReviewRound);
	
	attributes.addFlashAttribute("roundCreatedSuccessful", true);
	
	return "redirect:/profile/"+username+"/CreateReviewRound";
    }
    
   
    @GetMapping("/logout")
    public String logOut(HttpServletRequest request) {
	request.getSession().invalidate();
	return "index";
    }
    
}
