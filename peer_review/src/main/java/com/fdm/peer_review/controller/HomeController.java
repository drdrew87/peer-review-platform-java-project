package com.fdm.peer_review.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdm.peer_review.model.Employee;
import com.fdm.peer_review.repo.DepartmentRepo;
import com.fdm.peer_review.repo.EmployeeRepo;
import com.fdm.peer_review.repo.PermissionRepo;
import com.fdm.peer_review.service.DataBasePopulator;
import com.fdm.peer_review.service.RegistrationValidator;


@Controller
public class HomeController {
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private PermissionRepo permissionRepo;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private RegistrationValidator registrationValidator;
    @Autowired
    private DataBasePopulator dataBasePopulator;
    
    @GetMapping("/")
    public String goToLandingPage(Model model) {
	if(permissionRepo.count()<1) {
	    dataBasePopulator.populatePermission();
	}
	if(departmentRepo.count()<1) {
	    dataBasePopulator.populateDepartments();
	}
	return "index";
    }

    @PostMapping("/")
    public String logInUser() {
	return "profile";
    }
    
    

    @GetMapping("/register")
    public String goToRegisterPage(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentRepo.findAll());
        model.addAttribute("permissions", permissionRepo.findAll());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String department, @RequestParam String permission, Model model, Employee employee) {
        if (registrationValidator.validate(employee)) {
            employee.setDepartment(departmentRepo.getById(Integer.valueOf(department)));
            employee.setPermission(permissionRepo.getById(Integer.valueOf(permission)));
            employeeRepo.save(employee);
            return "redirect:/registrationSuccessful";
        } else {
            return "register";
        }
	
    }
    
    @GetMapping("/registrationSuccessful")
    public String registerUser(Model model) {
	model.addAttribute("registerSuccessful", true);	
        return "index";
    }
}
