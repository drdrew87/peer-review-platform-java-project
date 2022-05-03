package com.fdm.peer_review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.fdm.peer_review.model.Department;
import com.fdm.peer_review.model.Employee;
import com.fdm.peer_review.model.Permission;
import com.fdm.peer_review.repo.DepartmentRepo;
import com.fdm.peer_review.repo.EmployeeRepo;
import com.fdm.peer_review.repo.PermissionRepo;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private PermissionRepo permissionRepo;
    @Autowired
    private EmployeeRepo emRepo;
    
    
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
	Department trainee = new Department("Trainee");
	departmentRepo.save(trainee);
	Department trainer_dept = new Department("Trainer");
	departmentRepo.save(trainer_dept);
	Department sales = new Department("Sales");
	departmentRepo.save(sales);
	Department HR = new Department("HR");
	departmentRepo.save(HR);
	
	Permission employee = new Permission("Employee",false, false);
	permissionRepo.save(employee);
	Permission manager = new Permission("Manager",true, false);
	permissionRepo.save(manager);
	Permission HR_perm = new Permission("HR", false, true );
	permissionRepo.save(HR_perm);
	Permission HRManager = new Permission("HR Manager",true, true);
	permissionRepo.save(HRManager);
	
	
	Employee test = new Employee("Test","Test", "test", "test", "M", departmentRepo.getById(3), permissionRepo.getById(4));
	emRepo.save(test);
	Employee trainee1 = new Employee("Test","Test", "trainee1", "trainee", "F", departmentRepo.getById(1), permissionRepo.getById(1));
	emRepo.save(trainee1);
	Employee trainee2 = new Employee("Test","Test", "trainee2", "trainee", "M", departmentRepo.getById(1), permissionRepo.getById(1));
	emRepo.save(trainee2);
	Employee trainee3 = new Employee("Test","Test", "trainee3", "trainee", "M", departmentRepo.getById(1), permissionRepo.getById(1));
	emRepo.save(trainee3);
	Employee trainer = new Employee("Test","Test", "trainer", "trainer", "M", departmentRepo.getById(2), permissionRepo.getById(2));
	emRepo.save(trainer);
	Employee hr = new Employee("Test","Test", "hr", "hr", "F", departmentRepo.getById(3), permissionRepo.getById(3));
	emRepo.save(hr);

    }
    
}
