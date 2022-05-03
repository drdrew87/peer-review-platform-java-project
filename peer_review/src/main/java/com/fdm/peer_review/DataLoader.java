package com.fdm.peer_review;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.fdm.peer_review.model.Department;
import com.fdm.peer_review.model.Employee;
import com.fdm.peer_review.model.Permission;
import com.fdm.peer_review.model.Review;
import com.fdm.peer_review.model.ReviewRound;
import com.fdm.peer_review.repo.DepartmentRepo;
import com.fdm.peer_review.repo.EmployeeRepo;
import com.fdm.peer_review.repo.PermissionRepo;
import com.fdm.peer_review.repo.ReviewRepo;
import com.fdm.peer_review.repo.ReviewRoundRepo;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private PermissionRepo permissionRepo;
    @Autowired
    private EmployeeRepo emRepo;
    @Autowired
    private ReviewRoundRepo reviewRoundRepo;
    @Autowired
    private ReviewRepo reviewRepo;
    
    
    
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
	
	
	Employee test = new Employee("Superuser","Super", "test", "test", "M", departmentRepo.getById(3), permissionRepo.getById(4));
	emRepo.save(test);
	
	Employee trainee1 = new Employee("Trainee1","FDM", "trainee1", "trainee", "F", departmentRepo.getById(1), permissionRepo.getById(1));
	emRepo.save(trainee1);
	Employee trainee2 = new Employee("Trainee2","FDM", "trainee2", "trainee", "M", departmentRepo.getById(1), permissionRepo.getById(1));
	emRepo.save(trainee2);
	Employee trainee3 = new Employee("Trainee3","FDM", "trainee3", "trainee", "M", departmentRepo.getById(1), permissionRepo.getById(1));
	emRepo.save(trainee3);
	
	Employee trainer_manager = new Employee("Trainer","Manager", "trainer", "trainer", "M", departmentRepo.getById(2), permissionRepo.getById(2));
	emRepo.save(trainer_manager);
	Employee trainer1 = new Employee("Trainer1","FDM", "trainer1", "trainer", "M", departmentRepo.getById(2), permissionRepo.getById(1));
	emRepo.save(trainer1);
	Employee trainer2 = new Employee("Trainer2","FDM", "trainer2", "trainer", "M", departmentRepo.getById(2), permissionRepo.getById(1));
	emRepo.save(trainer2);
	Employee trainer3 = new Employee("Trainer3","FDM", "trainer3", "trainer", "M", departmentRepo.getById(2), permissionRepo.getById(1));
	emRepo.save(trainer3);

	Employee sales_manager = new Employee("Sales","Manager", "sales", "sales", "F", departmentRepo.getById(3), permissionRepo.getById(2));
	emRepo.save(sales_manager);
	
	Employee hr_manager = new Employee("HR","Manager", "hr", "hr", "F", departmentRepo.getById(3), permissionRepo.getById(4));
	emRepo.save(hr_manager);
	Employee hr1 = new Employee("HR1","FDM", "hr1", "hr", "F", departmentRepo.getById(4), permissionRepo.getById(3));
	emRepo.save(hr1);
	Employee hr2 = new Employee("HR2","FDM", "hr2", "hr", "F", departmentRepo.getById(4), permissionRepo.getById(3));
	emRepo.save(hr2);
	Employee hr3 = new Employee("HR3","FDM", "hr3", "hr", "F", departmentRepo.getById(4), permissionRepo.getById(3));
	emRepo.save(hr3);
	
	ReviewRound trainee_open = new ReviewRound("Trainee open round", Date.valueOf("2022-05-31"), trainee);
	reviewRoundRepo.save(trainee_open);
	ReviewRound trainee_closed1 = new ReviewRound("Trainee closed round 1", Date.valueOf("2022-04-30"), trainee);
	reviewRoundRepo.save(trainee_closed1);
	ReviewRound trainee_closed2 = new ReviewRound("Trainee closed round 2", Date.valueOf("2022-03-31"), trainee);
	reviewRoundRepo.save(trainee_closed2);
	ReviewRound trainer_open = new ReviewRound("Trainer open round", Date.valueOf("2022-05-31"), trainer_dept);
	reviewRoundRepo.save(trainer_open);
	ReviewRound trainer_closed1 = new ReviewRound("Trainer closed round 1", Date.valueOf("2022-04-30"), trainer_dept);
	reviewRoundRepo.save(trainer_closed1);
	ReviewRound trainer_closed2 = new ReviewRound("Trainer closed round 2", Date.valueOf("2022-03-31"), trainer_dept);
	reviewRoundRepo.save(trainer_closed2);
	ReviewRound sales_open = new ReviewRound("Sales open round", Date.valueOf("2022-05-31"), sales);
	reviewRoundRepo.save(sales_open);
	ReviewRound sales_closed1 = new ReviewRound("Sales closed round 1", Date.valueOf("2022-04-30"), sales);
	reviewRoundRepo.save(sales_closed1);
	ReviewRound sales_closed2 = new ReviewRound("Sales closed round 2", Date.valueOf("2022-03-31"), sales);
	reviewRoundRepo.save(sales_closed2);
	ReviewRound hr_open = new ReviewRound("HR open round", Date.valueOf("2022-05-31"), HR);
	reviewRoundRepo.save(hr_open);
	ReviewRound hr_closed1 = new ReviewRound("HR closed round 1", Date.valueOf("2022-04-30"), HR);
	reviewRoundRepo.save(hr_closed1);
	ReviewRound hr_closed2 = new ReviewRound("HR closed round 2", Date.valueOf("2022-03-31"), HR);
	reviewRoundRepo.save(hr_closed2);
	
	Review trainee_closed1_12 = new Review(trainee_closed1, trainee1, trainee2, Date.valueOf("2022-04-01"), 5, 4 ,3, 4, 5, 4, 3, 4);
	reviewRepo.save(trainee_closed1_12);
	Review trainee_closed1_13 = new Review(trainee_closed1, trainee1, trainee3, Date.valueOf("2022-04-01"), 5, 4 ,3, 4, 5, 4, 3, 4);
	reviewRepo.save(trainee_closed1_13);
	Review trainee_closed1_21 = new Review(trainee_closed1, trainee2, trainee1, Date.valueOf("2022-04-01"), 5, 4 ,3, 4, 5, 4, 3, 4);
	reviewRepo.save(trainee_closed1_21);
	Review trainee_closed1_23 = new Review(trainee_closed1, trainee2, trainee3, Date.valueOf("2022-04-01"), 5, 4 ,3, 4, 5, 4, 3, 4);
	reviewRepo.save(trainee_closed1_23);
	Review trainee_closed1_31 = new Review(trainee_closed1, trainee3, trainee1, Date.valueOf("2022-04-01"), 3, 2 ,2, 1, 4, 5, 5, 3);
	reviewRepo.save(trainee_closed1_31);
	Review trainee_closed1_32 = new Review(trainee_closed1, trainee3, trainee2, Date.valueOf("2022-04-01"), 5, 4 ,3, 4, 5, 4, 3, 4);
	reviewRepo.save(trainee_closed1_32);
	
	Review trainee_closed2_12 = new Review(trainee_closed2, trainee1, trainee2, Date.valueOf("2022-03-01"), 5, 4 ,3, 4, 5, 4, 3, 4);
	reviewRepo.save(trainee_closed2_12);
	Review trainee_closed2_13 = new Review(trainee_closed2, trainee1, trainee3, Date.valueOf("2022-03-01"), 5, 4 ,3, 4, 5, 4, 3, 4);
	reviewRepo.save(trainee_closed2_13);
	Review trainee_closed2_21 = new Review(trainee_closed2, trainee2, trainee1, Date.valueOf("2022-03-01"), 5, 4 ,3, 4, 5, 4, 3, 4);
	trainee_closed2_21.setComment("A pleasure to work with!");
	reviewRepo.save(trainee_closed2_21);
	Review trainee_closed2_23 = new Review(trainee_closed2, trainee2, trainee3, Date.valueOf("2022-03-01"), 5, 4 ,3, 4, 5, 4, 3, 4);
	reviewRepo.save(trainee_closed2_23);
	Review trainee_closed2_31 = new Review(trainee_closed2, trainee3, trainee1, Date.valueOf("2022-03-01"), 5, 4 ,3, 4, 5, 4, 3, 4);
	trainee_closed2_31.setComment("Great personality!");
	reviewRepo.save(trainee_closed2_31);
	Review trainee_closed2_32 = new Review(trainee_closed2, trainee3, trainee2, Date.valueOf("2022-03-01"), 5, 4 ,3, 4, 5, 4, 3, 4);
	reviewRepo.save(trainee_closed2_32);
	
	Review trainee_open_12 = new Review(trainee_open, trainee1, trainee2);
	reviewRepo.save(trainee_open_12);
	Review trainee_open_13 = new Review(trainee_open, trainee1, trainee3);
	reviewRepo.save(trainee_open_13);
	Review trainee_open_21 = new Review(trainee_open, trainee2, trainee1);
	reviewRepo.save(trainee_open_21);
	Review trainee_open_23 = new Review(trainee_open, trainee2, trainee3);
	reviewRepo.save(trainee_open_23);
	Review trainee_open_31 = new Review(trainee_open, trainee3, trainee1);
	reviewRepo.save(trainee_open_31);
	Review trainee_open_32 = new Review(trainee_open, trainee3, trainee2);
	reviewRepo.save(trainee_open_32);
    }
    
}
