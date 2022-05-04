package com.fdm.peer_review.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.peer_review.model.Department;
import com.fdm.peer_review.model.Employee;
import com.fdm.peer_review.repo.DepartmentRepo;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepo departmentRepo;
    
    public List<Department> generateDepartmentList(Employee currentUser) {
	ArrayList<Department> departmentList = new ArrayList<Department>();
	if (currentUser.getDepartment().getDepartmentName().equals("Trainer")) {
		departmentList.add(departmentRepo.getByDepartmentName("Trainee"));
	}
	    
	if (currentUser.getPermission().isDepartmentManager()) {
		departmentList.add(currentUser.getDepartment());
	}
	
	if (currentUser.getPermission().isDepartmentManager() && currentUser.getPermission().isHR()) {
	    departmentList = (ArrayList<Department>) departmentRepo.findAll();
	} else if (currentUser.getPermission().isHR()) {
	    ArrayList<Department> allDepartments = (ArrayList<Department>) departmentRepo.findAll();
	    for (Department department : allDepartments) {
		if (!department.getDepartmentName().equals("HR")) {
		    departmentList.add(department);
		}
	    }
	}
	return departmentList;
    }
    
    public Department getById(int departmentId) {
	return departmentRepo.getById(departmentId);
    }
    
    public List<Department> findAll() {
	return departmentRepo.findAll();
    }
}
