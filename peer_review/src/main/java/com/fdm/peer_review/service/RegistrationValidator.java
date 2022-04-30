package com.fdm.peer_review.service;

import com.fdm.peer_review.model.Employee;
import com.fdm.peer_review.repo.EmployeeRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationValidator {
    
    @Autowired
    private EmployeeRepo employeeRepo;
    
    public RegistrationValidator() {
	super();
    }
    
    public boolean validate(Employee employee) {
	boolean isValid = (employeeRepo.getByUserName(employee.getUserName())==null);
	return isValid;
    }
    
}
