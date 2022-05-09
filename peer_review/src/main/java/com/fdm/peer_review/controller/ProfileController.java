package com.fdm.peer_review.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.fdm.peer_review.service.DepartmentService;
import com.fdm.peer_review.service.EmployeeService;
import com.fdm.peer_review.service.ReviewRoundService;
import com.fdm.peer_review.service.ReviewService;
import com.fdm.peer_review.view.OpenRoundReviewView;
import com.fdm.peer_review.view.ProfileView;
import com.fdm.peer_review.view.ReviewAveragesView;

@Controller
public class ProfileController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ReviewRoundService reviewRoundService;

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private DepartmentService deptService;
    
    @GetMapping("/profile")
    public String DirectAccessToProfilePage() {
	return "redirect:/";
    }
    
    @GetMapping("/profile/{username}/MyRatings")
    public String LogInToProfilePage(@PathVariable String username, HttpServletRequest request, Model model) {
	HttpSession session = request.getSession();
	Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
	
	
	if (session!=null && session.getAttribute("username")!=null && username.equals(session.getAttribute("username"))) {
	    Employee currentUser = employeeService.getByUsername(username);
    	    if (session.getAttribute("allTabs")!=null) {
    		model.addAttribute("allTabs",(boolean) session.getAttribute("allTabs"));
    	    } else {
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
    	    }
    	    
    	    if (session.getAttribute("updateMode")==null) {
    		session.setAttribute("updateMode",false);
    	    }
	    
	    deptService.generateDepartmentList(session, currentUser);

	    ProfileView userProfile = new ProfileView(currentUser);
	    model.addAttribute("userProfile", userProfile);
	    
	    ArrayList<ReviewRound> closedReiviewRoundList = new ArrayList<ReviewRound>();
	    HashMap<Integer, ArrayList<Review>> closedReviewMapByRoundId = new HashMap<Integer, ArrayList<Review>>();
	    ArrayList<ReviewRound> reiviewRoundListInDept = (ArrayList<ReviewRound>) reviewRoundService.getClosedRoundsByDepartment(currentUser.getDepartment());
	    for (ReviewRound reviewRound : reiviewRoundListInDept) {
		ArrayList<Review> reviewsList = (ArrayList<Review>) reviewService.getClosedRoundCompletedReviews(reviewRound.getReviewRoundId(), currentUser.getEmployeeId());
		if (!(reviewsList.size() < 1)) {
		    closedReiviewRoundList.add(reviewRound);
		    closedReviewMapByRoundId.put(reviewRound.getReviewRoundId(),reviewsList);
		}
	    }
	    
	    model.addAttribute("reiviewRoundList", closedReiviewRoundList);
	    
	    
	    if (closedReiviewRoundList.size()<1) {
		model.addAttribute("emptyList",true);
	    } else {

		Integer selectedRoundId = reviewRoundService.autoSelect(model, inputFlashMap, closedReiviewRoundList);
		
		ReviewAveragesView reviewAverages = new ReviewAveragesView(currentUser, closedReviewMapByRoundId.get(selectedRoundId));
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
    
    
    @PostMapping("/profile/{username}/MyRatings/ReviewRounds")
    public String selectReviewRoundToViewRating(@PathVariable String username, @RequestParam String selectedRoundId, RedirectAttributes attributes) {
	attributes.addFlashAttribute("selectedRoundId", Integer.valueOf(selectedRoundId));
	return "redirect:/profile/"+username+"/MyRatings";
    }
    
    
    @GetMapping("/profile/{username}/OpenReviews")
    public String goToOpenReviews(@PathVariable String username, HttpServletRequest request, Model model) {
	HttpSession session = request.getSession();
	Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
	
	
	if (session!=null && session.getAttribute("username")!=null && username.equals(session.getAttribute("username"))) {
	    if((boolean) session.getAttribute("allTabs")) {
		model.addAttribute("allTabs",true);
	    }
	    
	    boolean updateMode = (boolean) session.getAttribute("updateMode");
	    model.addAttribute("updateMode",updateMode);
	    
	    Employee currentUser = employeeService.getByUsername(username);
	    
	    ArrayList<ReviewRound> openReiviewRoundList = new ArrayList<ReviewRound>();
	    HashMap<Integer,List<OpenRoundReviewView>> openReviewMapByRoundId = new HashMap<Integer, List<OpenRoundReviewView>>();
	    ArrayList<ReviewRound> reiviewRoundListInDept = (ArrayList<ReviewRound>) reviewRoundService.getOpenRoundsByDepartment(currentUser.getDepartment());
	    for (ReviewRound reviewRound : reiviewRoundListInDept) {
		ArrayList<Review> reviewsList;
		if (!updateMode) {
		    reviewsList = (ArrayList<Review>) reviewService.getOpenRoundIncompleteReviews(reviewRound.getReviewRoundId(), currentUser.getEmployeeId());
		} else {
		    reviewsList = (ArrayList<Review>) reviewService.getOpenRoundCompletedReviews(reviewRound.getReviewRoundId(), currentUser.getEmployeeId());
		}
		if (!(reviewsList.size() < 1)) {
		    openReiviewRoundList.add(reviewRound);
		    ArrayList<OpenRoundReviewView> viewObjectList = new ArrayList<OpenRoundReviewView>();
		    for (int index=0; index < reviewsList.size(); index++) {
			viewObjectList.add(new OpenRoundReviewView(index, reviewRound, reviewsList.get(index), 
				employeeService.getById(reviewsList.get(index).getReviewerId()), employeeService.getById(reviewsList.get(index).getRecipientId())));
		    }
		    openReviewMapByRoundId.put(reviewRound.getReviewRoundId(),viewObjectList);
		}
	    }
	    
	    model.addAttribute("reiviewRoundList", openReiviewRoundList);
	    
	    if (openReiviewRoundList.size()<1) {
		model.addAttribute("emptyList",true);
	    } else {

		Integer selectedRoundId = reviewRoundService.autoSelect(model, inputFlashMap, openReiviewRoundList);
		
		model.addAttribute("openReviewList",openReviewMapByRoundId.get(selectedRoundId));
		
		Integer listIndex = 0;
		if (inputFlashMap != null && inputFlashMap.get("listIndex")!=null) {
		    listIndex = (Integer) inputFlashMap.get("listIndex");
		} else {
		    listIndex = openReviewMapByRoundId.get(selectedRoundId).get(0).getIndex();
		    model.addAttribute("listIndex",listIndex);
		}
		
		Review selectedReview = openReviewMapByRoundId.get(selectedRoundId).get(listIndex).getReview();
		model.addAttribute("selectedReview", selectedReview);
		Employee selectedRecipient = openReviewMapByRoundId.get(selectedRoundId).get(listIndex).getRecipient();
		model.addAttribute("selectedRecipient", selectedRecipient);
	    }
	    
	    
	    return "profile_open_reviews";
	} else {
	    return "index";
	}
    }
    
    @PostMapping("/profile/{username}/OpenReviews/ReviewRounds")
    public String selectReviewRoundInOpenViews(@PathVariable String username, @RequestParam String selectedRoundId, RedirectAttributes attributes) {
	attributes.addFlashAttribute("selectedRoundId", Integer.valueOf(selectedRoundId));
	return "redirect:/profile/"+username+"/OpenReviews";
    }
    
    @PostMapping("/profile/{username}/OpenReviews/review")
    public String selectReviewInOpenRound(@PathVariable String username, RedirectAttributes attributes, @RequestParam String roundNIndex) {
	Integer selectedRoundId = Integer.valueOf(roundNIndex.split(",")[0]);
	Integer listIndex = Integer.valueOf(roundNIndex.split(",")[1]);
	attributes.addFlashAttribute("selectedRoundId", selectedRoundId);
	attributes.addFlashAttribute("listIndex", listIndex);
	return "redirect:/profile/"+username+"/OpenReviews";
    }
    
    @PostMapping("/profile/{username}/OpenReviews/submit")
    public String submitReviewForm(@PathVariable String username, RedirectAttributes attributes, Review submittingReview) {
	submittingReview.setCompletionDate(Date.valueOf(LocalDate.now()));
	reviewService.save(submittingReview);
	return "redirect:/profile/"+username+"/OpenReviews";
    }
    
    @PostMapping("/profile/{username}/OpenReviews/updateMode")
    public String toggleUpdateMode(@PathVariable String username, HttpServletRequest request, @RequestParam String updateMode) {
   	boolean mode = updateMode.equals("true") ? true : false;
   	request.getSession().setAttribute("updateMode", mode);
   	return "redirect:/profile/"+username+"/OpenReviews";
    }
    
    
    @GetMapping("/profile/{username}/TeamReviews")
    public String goToTeamReviews(@PathVariable String username, HttpServletRequest request, Model model) {
	HttpSession session = request.getSession();
	Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
	
	if (session!=null && session.getAttribute("username")!=null && username.equals(session.getAttribute("username"))) {
	    if((boolean) session.getAttribute("allTabs")) {
		model.addAttribute("allTabs",true);
	    }
    
	    @SuppressWarnings("unchecked")
	    ArrayList<Department> departmentList = (ArrayList<Department>) session.getAttribute("departmentList");
	    model.addAttribute("departments", departmentList);
 
	    Integer departmentId = deptService.autoSelect(model, inputFlashMap, departmentList);
	    
	    Department selectedDept = deptService.getById(departmentId);
	    ArrayList<ReviewRound> closedRoundListInDept = (ArrayList<ReviewRound>) reviewRoundService.getClosedRoundsByDepartment(selectedDept);
	    ArrayList<Employee> employeesInDept = (ArrayList<Employee>) employeeService.getByDepartment(selectedDept);
	    HashMap<Integer, ArrayList<ReviewAveragesView>> reviewAverageViewsInRound = new HashMap<Integer, ArrayList<ReviewAveragesView>>();
	    for (ReviewRound round : closedRoundListInDept) {
		ArrayList<ReviewAveragesView> reviewAverageList = new ArrayList<ReviewAveragesView>();
		for (Employee recipient : employeesInDept) {
		    ArrayList<Review> completedReviews = (ArrayList<Review>) reviewService.getClosedRoundCompletedReviews(round.getReviewRoundId(), recipient.getEmployeeId());
	
		    if (completedReviews.size()>0) {
			ReviewAveragesView recipientAverageView = new ReviewAveragesView(recipient, completedReviews);		
			reviewAverageList.add(recipientAverageView);
		    }
		}
		
		if (reviewAverageList.size()>0) {
		    reviewAverageViewsInRound.put(round.getReviewRoundId(), reviewAverageList);
		}
	    }
	    
	    model.addAttribute("reiviewRoundList", closedRoundListInDept);
	        
	    Integer selectedRoundId = reviewRoundService.autoSelect(model, inputFlashMap, closedRoundListInDept);
	    
	    if (reviewAverageViewsInRound.get(selectedRoundId) != null) {
		model.addAttribute("reviewAverageViewList", reviewAverageViewsInRound.get(selectedRoundId));
	    } else {
		model.addAttribute("emptyList",true);
	    }
	    return "profile_team_reviews";
	} else {
	    return "index";
	}
    }
    
    @PostMapping("/profile/{username}/TeamReviews/departmentList")
    public String selectDepartmentOnTeamReviews(@PathVariable String username, RedirectAttributes attributes, @RequestParam String departmentId) {
	attributes.addFlashAttribute("departmentId", Integer.valueOf(departmentId));
	return "redirect:/profile/"+username+"/TeamReviews";
    }
    
    @PostMapping("/profile/{username}/TeamReviews/ReviewRounds")
    public String selectReviewRoundInTeamReviews(@PathVariable String username, @RequestParam String deptAndRound, RedirectAttributes attributes) {
//	attributes.addFlashAttribute("selectedRoundId", Integer.valueOf(selectedRoundId));
	int departmentId = Integer.valueOf(deptAndRound.split(",")[0]);
	int selectedRoundId = Integer.valueOf(deptAndRound.split(",")[1]);
	attributes.addFlashAttribute("departmentId", departmentId);
	attributes.addFlashAttribute("selectedRoundId", selectedRoundId);
	return "redirect:/profile/"+username+"/TeamReviews";
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
	newReviewRound.setDepartment(deptService.getById(Integer.valueOf(department)));
	
	reviewRoundService.createNewReviewRound(newReviewRound);
	
	attributes.addFlashAttribute("roundCreatedSuccessful", true);
	
	return "redirect:/profile/"+username+"/CreateReviewRound";
    }
    
   
    @GetMapping("/logout")
    public String logOut(HttpServletRequest request) {
	request.getSession().invalidate();
	return "index";
    }
    
}
