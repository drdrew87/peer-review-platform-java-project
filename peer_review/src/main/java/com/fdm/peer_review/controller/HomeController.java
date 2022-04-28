package com.fdm.peer_review.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fdm.peer_review.model.Employee;
import com.fdm.peer_review.repo.DepartmentRepo;
import com.fdm.peer_review.repo.EmployeeRepo;
import com.fdm.peer_review.repo.PermissionRepo;


@Controller
public class HomeController {
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private PermissionRepo permissionRepo;
    @Autowired
    private EmployeeRepo employeeRepo;
    
    @GetMapping("/")
    public String goToLandingPage(Model model) {
	return "index";
    }

    @PostMapping("/")
    public String logInUser() {
	return "home";
    }
    
    

    @GetMapping("/register")
    public String goToRegisterPage(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentRepo.findAll());
        model.addAttribute("permissions", permissionRepo.findAll());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(HttpServletRequest request, Employee employee) {
        return "redirect:/registrationSuccessful";
    }
    
    @GetMapping("/registrationSuccessful")
    public String registerUser(Model model) {
	model.addAttribute("registerSuccessful", true);	
        return "index";
    }
}
