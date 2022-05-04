package com.fdm.peer_review.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fdm.peer_review.model.Employee;
import com.fdm.peer_review.service.DepartmentService;
import com.fdm.peer_review.service.EmployeeService;
import com.fdm.peer_review.service.LoginValidator;
import com.fdm.peer_review.service.PermissionService;
import com.fdm.peer_review.service.RegistrationValidator;


@Controller
public class HomeController {

    @Autowired
    private DepartmentService deptService;
    @Autowired
    private PermissionService permService;
    @Autowired
    private EmployeeService empService;
    @Autowired
    private RegistrationValidator registrationValidator;
    @Autowired
    private LoginValidator loginValidator;
    
    @GetMapping("/")
    public String goToLandingPage(Model model) {
	return "index";
    }

    @PostMapping("/")
    public String logInUser(Model model, RedirectAttributes attributes, @RequestParam String username, @RequestParam String password, HttpServletRequest request) {
	if (loginValidator.validate(username, password)) {
	    attributes.addFlashAttribute("username", username);
	    request.getSession().setAttribute("username", username);
	    return "redirect:/profile/"+username+"/MyRatings";
	} else {
	    model.addAttribute("invalidLogin", true);
	    return "index";
	}
	
    }
    
    
    @GetMapping("/register")
    public String goToRegisterPage(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", deptService.findAll());
        model.addAttribute("permissions", permService.findAll());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String confirm_password, @RequestParam String department, @RequestParam String permission, RedirectAttributes attributes, Employee employee) {
	if (!employee.getPassWord().equals(confirm_password)) {
	    attributes.addFlashAttribute("passwordMismatched", true);
            return "redirect:/register";
        }
	if (registrationValidator.validate(employee)) {
            employee.setDepartment(deptService.getById(Integer.valueOf(department)));
            employee.setPermission(permService.getById(Integer.valueOf(permission)));
            empService.save(employee);
            attributes.addFlashAttribute("registerSuccessful", true);
            return "redirect:/";
        } else {
            attributes.addFlashAttribute("accountIsTaken", true);
            return "redirect:/register";
        }
	
    }
    
}
