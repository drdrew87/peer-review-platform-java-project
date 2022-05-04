package com.fdm.peer_review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.peer_review.model.Department;
import com.fdm.peer_review.model.Employee;
import com.fdm.peer_review.repo.EmployeeRepo;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo emRepo;

    public Employee getById(int employeeId) {
        return emRepo.getById(employeeId);
    }
    
    public Employee getByUsername(String username) {
	return emRepo.getByUserName(username);
    }
    
    public List<Employee> getByDepartment(Department dept) {
	return emRepo.getByDepartment(dept);
    }
    
    public void save(Employee employee) {
	emRepo.save(employee);
    }
}
