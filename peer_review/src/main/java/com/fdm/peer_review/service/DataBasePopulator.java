package com.fdm.peer_review.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.peer_review.model.Department;
import com.fdm.peer_review.model.Permission;
import com.fdm.peer_review.repo.DepartmentRepo;
import com.fdm.peer_review.repo.PermissionRepo;

@Service
@Transactional
public class DataBasePopulator {
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private PermissionRepo permissionRepo;
    
    public void populateDepartments() {
	Department academy = new Department("Academy");
	departmentRepo.save(academy);
	Department sales = new Department("Sales");
	departmentRepo.save(sales);
	Department HR = new Department("HR");
	departmentRepo.save(HR);
    }
    
    public void populatePermission() {
	Permission trainee = new Permission("Trainee",false, false);
	permissionRepo.save(trainee);
	Permission trainer = new Permission("Trainer",true, false);
	permissionRepo.save(trainer);
	Permission HR = new Permission("HR", false, true );
	permissionRepo.save(HR);
	Permission HRManager = new Permission("HR Manager",true, true);
	permissionRepo.save(HRManager);
	
    }
}

