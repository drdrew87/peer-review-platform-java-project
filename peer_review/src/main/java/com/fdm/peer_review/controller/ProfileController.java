package com.fdm.peer_review.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fdm.peer_review.model.Employee;
import com.fdm.peer_review.repo.EmployeeRepo;

@Controller
public class ProfileController {
    @Autowired
    private EmployeeRepo employeeRepo;
    
    @GetMapping("/profile")
    public String DirectAccessToProfilePage() {
	return "redirect:/";
    }
    
    @GetMapping("/profile/{username}/MyRatings")
    public String LogInToProfilePage(@PathVariable String username, HttpServletRequest request, Model model) {
	HttpSession session = request.getSession();
	if (session!=null && session.getAttribute("username")!=null && username.equals(session.getAttribute("username"))) {
	    Employee currentUser = employeeRepo.getByUserName(username);
	    if (currentUser.getPermission().isDepartmentManager() || currentUser.getPermission().isHR()) {
		session.setAttribute("allTabs", true);
		model.addAttribute("allTabs",true);
	    }
	    return "profile1";
	} else {
	    return "index";
	}
    }
    
    @GetMapping("/profile/{username}/OpenReviews")
    public String viewOpenReviews(@PathVariable String username, HttpServletRequest request, Model model) {
	HttpSession session = request.getSession();
	if (session!=null && session.getAttribute("username")!=null && username.equals(session.getAttribute("username"))) {
	    if(session.getAttribute("allTabs")!=null) {
		model.addAttribute("allTabs",true);
	    }
	    return "profile2";
	} else {
	    return "index";
	}
    }
    
   
    @GetMapping("/logout")
    public String logOut(HttpServletRequest request) {
	request.getSession().invalidate();
	return "index";
    }
    
}
