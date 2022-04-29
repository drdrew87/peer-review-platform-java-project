package com.fdm.peer_review.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProfileController {
    
    @GetMapping("/profile/{username}")
    public String LogInToProfilePage(@PathVariable String username, HttpServletRequest request) {
	HttpSession session = request.getSession();
	if (session!=null && session.getAttribute("username")!=null && username.equals(session.getAttribute("username"))) {
	    return "profile";
	} else {
	    return "index";
	}
    }
    
    @GetMapping("/profile")
    public String DirectAccessToProfilePage() {
	   return "redirect:/";
    }
    
}
