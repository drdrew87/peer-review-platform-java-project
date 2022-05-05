package com.fdm.peer_review.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fdm.peer_review.model.Department;
import com.fdm.peer_review.model.Employee;
import com.fdm.peer_review.repo.DepartmentRepo;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepo departmentRepo;
    
    public void generateDepartmentList(HttpSession session, Employee currentUser) {
	ArrayList<Department> departmentList = new ArrayList<Department>();
	    
	if (currentUser.getPermission().isDepartmentManager()) {
		departmentList.add(currentUser.getDepartment());
	}

	if (currentUser.getDepartment().getDepartmentName().equals("Trainer")) {
	    departmentList.add(departmentRepo.getByDepartmentName("Trainee"));
	}
	
	if (currentUser.getPermission().isHR()) {
	    ArrayList<Department> allDepartments = (ArrayList<Department>) departmentRepo.findAll();
	    for (Department department : allDepartments) {
		if (!department.getDepartmentName().equals("HR")) {
		    departmentList.add(department);
		}
	    }
	}
	session.setAttribute("departmentList", departmentList);
    }
    
    public Department getById(int departmentId) {
	return departmentRepo.getById(departmentId);
    }
    
    public List<Department> findAll() {
	return departmentRepo.findAll();
    }
    
    public Integer autoSelect(Model model, Map<String,?> inputFlashMap, List<Department> deptList) {
	
	Integer departmentId = 0;
	if (inputFlashMap != null && inputFlashMap.get("departmentId")!=null) {
	    departmentId = (Integer) inputFlashMap.get("departmentId");
	    model.addAttribute("selectedDept", departmentRepo.getById(departmentId));
	} else {
	    departmentId = deptList.get(0).getDeparmentId();
	    model.addAttribute("selectedDept",deptList.get(0));
	    model.addAttribute("departmentId",departmentId);   
	}
	return departmentId;
    }
}
