package com.fdm.peer_review.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.peer_review.model.Employee;
import com.fdm.peer_review.repo.EmployeeRepo;

@Service
public class LoginValidator {
    @Autowired
    private EmployeeRepo emRepo;
    
    public boolean validate(String username, String password) {
	Employee inputUser = emRepo.getByUserName(username);
	return (inputUser!=null && password.equals(inputUser.getPassWord()));
    }

}
