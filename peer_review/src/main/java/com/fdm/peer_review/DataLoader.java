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
	
	
	Employee test = new Employee("Superuser","Super", "test", "test", "M", departmentRepo.getById(4), permissionRepo.getById(4));
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
	Employee sales1 = new Employee("Sales1","FDM", "sales1", "sales", "F", departmentRepo.getById(3), permissionRepo.getById(1));
	emRepo.save(sales1);
	Employee sales2 = new Employee("Sales2","FDM", "sales2", "sales", "M", departmentRepo.getById(3), permissionRepo.getById(1));
	emRepo.save(sales2);
	Employee sales3 = new Employee("Sales3","FDM", "sales3", "sales", "F", departmentRepo.getById(3), permissionRepo.getById(1));
	emRepo.save(sales3);
	
	Employee hr_manager = new Employee("HR","Manager", "hr", "hr", "F", departmentRepo.getById(4), permissionRepo.getById(4));
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
	
	Review trainee_closed1_12 = new Review(trainee_closed1, trainee1, trainee2, Date.valueOf("2022-04-01"), 5,4,4,1,5,3,1,1);
	reviewRepo.save(trainee_closed1_12);
	Review trainee_closed1_13 = new Review(trainee_closed1, trainee1, trainee3, Date.valueOf("2022-04-01"), 2,4,4,2,5,4,5,2);
	reviewRepo.save(trainee_closed1_13);
	Review trainee_closed1_21 = new Review(trainee_closed1, trainee2, trainee1, Date.valueOf("2022-04-01"), 1,3,2,1,4,5,3,4);
	reviewRepo.save(trainee_closed1_21);
	Review trainee_closed1_23 = new Review(trainee_closed1, trainee2, trainee3, Date.valueOf("2022-04-01"), 2,1,3,5,2,4,4,4);
	reviewRepo.save(trainee_closed1_23);
	Review trainee_closed1_31 = new Review(trainee_closed1, trainee3, trainee1, Date.valueOf("2022-04-01"), 1,1,3,3,3,3,2,4);
	reviewRepo.save(trainee_closed1_31);
	Review trainee_closed1_32 = new Review(trainee_closed1, trainee3, trainee2, Date.valueOf("2022-04-01"), 3,2,3,3,4,4,3,1);
	reviewRepo.save(trainee_closed1_32);
	
	Review trainee_closed2_12 = new Review(trainee_closed2, trainee1, trainee2, Date.valueOf("2022-03-01"), 5,2,4,5,3,1,5,3);
	reviewRepo.save(trainee_closed2_12);
	Review trainee_closed2_13 = new Review(trainee_closed2, trainee1, trainee3, Date.valueOf("2022-03-01"), 4,3,1,4,1,5,5,3);
	reviewRepo.save(trainee_closed2_13);
	Review trainee_closed2_21 = new Review(trainee_closed2, trainee2, trainee1, Date.valueOf("2022-03-01"), 2,4,3,2,3,1,4,4);
	trainee_closed2_21.setComment("A pleasure to work with!");
	reviewRepo.save(trainee_closed2_21);
	Review trainee_closed2_23 = new Review(trainee_closed2, trainee2, trainee3, Date.valueOf("2022-03-01"), 2,5,3,3,1,2,1,1);
	reviewRepo.save(trainee_closed2_23);
	Review trainee_closed2_31 = new Review(trainee_closed2, trainee3, trainee1, Date.valueOf("2022-03-01"), 5,5,4,3,4,1,4,5);
	trainee_closed2_31.setComment("Great personality!");
	reviewRepo.save(trainee_closed2_31);
	Review trainee_closed2_32 = new Review(trainee_closed2, trainee3, trainee2, Date.valueOf("2022-03-01"), 5,1,2,5,3,1,2,1);
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
	
	Review trainer_closed1_12 = new Review(trainer_closed1, trainer1, trainer2, Date.valueOf("2022-04-01"), 5,3,1,5,1,1,4,5);
	reviewRepo.save(trainer_closed1_12);
	Review trainer_closed1_13 = new Review(trainer_closed1, trainer1, trainer3, Date.valueOf("2022-04-01"), 3,2,3,4,4,3,1,5);
	reviewRepo.save(trainer_closed1_13);
	Review trainer_closed1_21 = new Review(trainer_closed1, trainer2, trainer1, Date.valueOf("2022-04-01"), 3,4,3,3,3,3,3,5);
	reviewRepo.save(trainer_closed1_21);
	Review trainer_closed1_23 = new Review(trainer_closed1, trainer2, trainer3, Date.valueOf("2022-04-01"), 4,1,1,4,5,4,5,2);
	reviewRepo.save(trainer_closed1_23);
	Review trainer_closed1_31 = new Review(trainer_closed1, trainer3, trainer1, Date.valueOf("2022-04-01"), 2,3,1,5,5,3,1,5);
	reviewRepo.save(trainer_closed1_31);
	Review trainer_closed1_32 = new Review(trainer_closed1, trainer3, trainer2, Date.valueOf("2022-04-01"), 5,5,5,4,2,4,4,4);
	reviewRepo.save(trainer_closed1_32);
	Review trainer_closed1_m1 = new Review(trainer_closed1, trainer_manager, trainer1, Date.valueOf("2022-04-01"), 3,1,5,4,4,3,4,4);
	reviewRepo.save(trainer_closed1_m1);
	Review trainer_closed1_m2 = new Review(trainer_closed1, trainer_manager, trainer2, Date.valueOf("2022-04-01"), 4,4,4,2,2,2,1,4);
	reviewRepo.save(trainer_closed1_m2);
	Review trainer_closed1_m3 = new Review(trainer_closed1, trainer_manager, trainer3, Date.valueOf("2022-04-01"), 1,3,1,3,5,4,2,2);
	reviewRepo.save(trainer_closed1_m3);
	Review trainer_closed1_1m = new Review(trainer_closed1, trainer1, trainer_manager, Date.valueOf("2022-04-01"), 4,3,3,1,5,4,2,1);
	reviewRepo.save(trainer_closed1_1m);
	Review trainer_closed1_2m = new Review(trainer_closed1, trainer2, trainer_manager, Date.valueOf("2022-04-01"), 3,4,4,3,2,4,5,2);
	reviewRepo.save(trainer_closed1_2m);
	Review trainer_closed1_3m = new Review(trainer_closed1, trainer3, trainer_manager, Date.valueOf("2022-04-01"), 3,5,2,2,5,4,1,3);
	reviewRepo.save(trainer_closed1_3m);
	
	Review trainer_closed2_12 = new Review(trainer_closed2, trainer1, trainer2, Date.valueOf("2022-03-01"), 1,4,3,4,2,4,4,1);
	reviewRepo.save(trainer_closed2_12);
	Review trainer_closed2_13 = new Review(trainer_closed2, trainer1, trainer3, Date.valueOf("2022-03-01"), 2,1,5,3,2,2,5,4);
	reviewRepo.save(trainer_closed2_13);
	Review trainer_closed2_21 = new Review(trainer_closed2, trainer2, trainer1, Date.valueOf("2022-03-01"), 2,5,3,2,1,3,3,4);
	reviewRepo.save(trainer_closed2_21);
	Review trainer_closed2_23 = new Review(trainer_closed2, trainer2, trainer3, Date.valueOf("2022-03-01"), 4,1,5,5,3,5,5,5);
	reviewRepo.save(trainer_closed2_23);
	Review trainer_closed2_31 = new Review(trainer_closed2, trainer3, trainer1, Date.valueOf("2022-03-01"), 5,3,3,2,4,3,1,2);
	reviewRepo.save(trainer_closed2_31);
	Review trainer_closed2_32 = new Review(trainer_closed2, trainer3, trainer2, Date.valueOf("2022-03-01"), 3,4,5,4,2,5,3,1);
	reviewRepo.save(trainer_closed2_32);
	Review trainer_closed2_m1 = new Review(trainer_closed2, trainer_manager, trainer1, Date.valueOf("2022-03-01"), 3,2,2,4,4,2,5,1);
	reviewRepo.save(trainer_closed2_m1);
	Review trainer_closed2_m2 = new Review(trainer_closed2, trainer_manager, trainer2, Date.valueOf("2022-03-01"), 4,5,5,1,4,5,2,3);
	reviewRepo.save(trainer_closed2_m2);
	Review trainer_closed2_m3 = new Review(trainer_closed2, trainer_manager, trainer3, Date.valueOf("2022-03-01"), 4,2,1,2,1,2,4,3);
	reviewRepo.save(trainer_closed2_m3);
	Review trainer_closed2_1m = new Review(trainer_closed2, trainer1, trainer_manager, Date.valueOf("2022-03-01"), 5,5,3,1,2,3,1,1);
	reviewRepo.save(trainer_closed2_1m);
	Review trainer_closed2_2m = new Review(trainer_closed2, trainer2, trainer_manager, Date.valueOf("2022-03-01"), 1,1,2,1,5,2,1,4);
	reviewRepo.save(trainer_closed2_2m);
	Review trainer_closed2_3m = new Review(trainer_closed2, trainer3, trainer_manager, Date.valueOf("2022-03-01"), 3,1,5,5,5,1,2,5);
	reviewRepo.save(trainer_closed2_3m);
	
	
	Review trainer_open_12 = new Review(trainer_open, trainer1, trainer2);
	reviewRepo.save(trainer_open_12);
	Review trainer_open_13 = new Review(trainer_open, trainer1, trainer3);
	reviewRepo.save(trainer_open_13);
	Review trainer_open_21 = new Review(trainer_open, trainer2, trainer1);
	reviewRepo.save(trainer_open_21);
	Review trainer_open_23 = new Review(trainer_open, trainer2, trainer3);
	reviewRepo.save(trainer_open_23);
	Review trainer_open_31 = new Review(trainer_open, trainer3, trainer1);
	reviewRepo.save(trainer_open_31);
	Review trainer_open_32 = new Review(trainer_open, trainer3, trainer2);
	reviewRepo.save(trainer_open_32);
	Review trainer_open_m1 = new Review(trainer_open, trainer_manager, trainer1);
	reviewRepo.save(trainer_open_m1);
	Review trainer_open_m2 = new Review(trainer_open, trainer_manager, trainer2);
	reviewRepo.save(trainer_open_m2);
	Review trainer_open_m3 = new Review(trainer_open, trainer_manager, trainer3);
	reviewRepo.save(trainer_open_m3);
	Review trainer_open_1m = new Review(trainer_open, trainer1, trainer_manager);
	reviewRepo.save(trainer_open_1m);
	Review trainer_open_2m = new Review(trainer_open, trainer2, trainer_manager);
	reviewRepo.save(trainer_open_2m);
	Review trainer_open_3m = new Review(trainer_open, trainer3, trainer_manager);
	reviewRepo.save(trainer_open_3m);
	
	Review sales_closed1_12 = new Review(sales_closed1, sales1, sales2, Date.valueOf("2022-04-01"), 2,2,5,2,2,5,3,3);
	reviewRepo.save(sales_closed1_12);
	Review sales_closed1_13 = new Review(sales_closed1, sales1, sales3, Date.valueOf("2022-04-01"), 5,2,5,3,2,1,5,4);
	reviewRepo.save(sales_closed1_13);
	Review sales_closed1_21 = new Review(sales_closed1, sales2, sales1, Date.valueOf("2022-04-01"), 4,3,5,5,1,2,5,5);
	reviewRepo.save(sales_closed1_21);
	Review sales_closed1_23 = new Review(sales_closed1, sales2, sales3, Date.valueOf("2022-04-01"), 1,3,5,2,2,2,2,1);
	reviewRepo.save(sales_closed1_23);
	Review sales_closed1_31 = new Review(sales_closed1, sales3, sales1, Date.valueOf("2022-04-01"), 2,3,4,2,5,2,4,1);
	reviewRepo.save(sales_closed1_31);
	Review sales_closed1_32 = new Review(sales_closed1, sales3, sales2, Date.valueOf("2022-04-01"), 5,5,5,4,5,2,5,2);
	reviewRepo.save(sales_closed1_32);
	Review sales_closed1_m1 = new Review(sales_closed1, sales_manager, sales1, Date.valueOf("2022-04-01"), 5,5,1,2,1,5,2,1);
	reviewRepo.save(sales_closed1_m1);
	Review sales_closed1_m2 = new Review(sales_closed1, sales_manager, sales2, Date.valueOf("2022-04-01"), 1,3,4,3,5,4,4,4);
	reviewRepo.save(sales_closed1_m2);
	Review sales_closed1_m3 = new Review(sales_closed1, sales_manager, sales3, Date.valueOf("2022-04-01"), 1,5,4,2,2,4,1,3);
	reviewRepo.save(sales_closed1_m3);
	Review sales_closed1_1m = new Review(sales_closed1, sales1, sales_manager, Date.valueOf("2022-04-01"), 3,1,5,3,1,4,2,4);
	reviewRepo.save(sales_closed1_1m);
	Review sales_closed1_2m = new Review(sales_closed1, sales2, sales_manager, Date.valueOf("2022-04-01"), 3,4,1,3,5,2,3,4);
	reviewRepo.save(sales_closed1_2m);
	Review sales_closed1_3m = new Review(sales_closed1, sales3, sales_manager, Date.valueOf("2022-04-01"), 1,4,5,2,3,3,5,1);
	reviewRepo.save(sales_closed1_3m);
	
	Review sales_closed2_12 = new Review(sales_closed2, sales1, sales2, Date.valueOf("2022-03-01"), 2,2,5,2,2,3,2,4);
	reviewRepo.save(sales_closed2_12);
	Review sales_closed2_13 = new Review(sales_closed2, sales1, sales3, Date.valueOf("2022-03-01"), 4,1,3,4,3,4,5,4);
	reviewRepo.save(sales_closed2_13);
	Review sales_closed2_21 = new Review(sales_closed2, sales2, sales1, Date.valueOf("2022-03-01"), 4,5,1,4,2,3,2,2);
	reviewRepo.save(sales_closed2_21);
	Review sales_closed2_23 = new Review(sales_closed2, sales2, sales3, Date.valueOf("2022-03-01"), 3,5,2,4,5,1,3,1);
	reviewRepo.save(sales_closed2_23);
	Review sales_closed2_31 = new Review(sales_closed2, sales3, sales1, Date.valueOf("2022-03-01"), 3,2,5,1,4,1,5,3);
	reviewRepo.save(sales_closed2_31);
	Review sales_closed2_32 = new Review(sales_closed2, sales3, sales2, Date.valueOf("2022-03-01"), 5,5,2,2,5,1,2,2);
	reviewRepo.save(sales_closed2_32);
	Review sales_closed2_m1 = new Review(sales_closed2, sales_manager, sales1, Date.valueOf("2022-03-01"), 1,4,5,1,2,2,4,4);
	reviewRepo.save(sales_closed2_m1);
	Review sales_closed2_m2 = new Review(sales_closed2, sales_manager, sales2, Date.valueOf("2022-03-01"), 5,2,1,5,1,5,2,1);
	reviewRepo.save(sales_closed2_m2);
	Review sales_closed2_m3 = new Review(sales_closed2, sales_manager, sales3, Date.valueOf("2022-03-01"), 5,3,5,4,2,1,2,5);
	reviewRepo.save(sales_closed2_m3);
	Review sales_closed2_1m = new Review(sales_closed2, sales1, sales_manager, Date.valueOf("2022-03-01"), 5,4,4,2,4,1,5,1);
	reviewRepo.save(sales_closed2_1m);
	Review sales_closed2_2m = new Review(sales_closed2, sales2, sales_manager, Date.valueOf("2022-03-01"), 5,4,4,2,2,5,2,5);
	reviewRepo.save(sales_closed2_2m);
	Review sales_closed2_3m = new Review(sales_closed2, sales3, sales_manager, Date.valueOf("2022-03-01"), 5,1,1,4,5,3,4,3);
	reviewRepo.save(sales_closed2_3m);
	
	
	Review sales_open_12 = new Review(sales_open, sales1, sales2);
	reviewRepo.save(sales_open_12);
	Review sales_open_13 = new Review(sales_open, sales1, sales3);
	reviewRepo.save(sales_open_13);
	Review sales_open_21 = new Review(sales_open, sales2, sales1);
	reviewRepo.save(sales_open_21);
	Review sales_open_23 = new Review(sales_open, sales2, sales3);
	reviewRepo.save(sales_open_23);
	Review sales_open_31 = new Review(sales_open, sales3, sales1);
	reviewRepo.save(sales_open_31);
	Review sales_open_32 = new Review(sales_open, sales3, sales2);
	reviewRepo.save(sales_open_32);
	Review sales_open_m1 = new Review(sales_open, sales_manager, sales1);
	reviewRepo.save(sales_open_m1);
	Review sales_open_m2 = new Review(sales_open, sales_manager, sales2);
	reviewRepo.save(sales_open_m2);
	Review sales_open_m3 = new Review(sales_open, sales_manager, sales3);
	reviewRepo.save(sales_open_m3);
	Review sales_open_1m = new Review(sales_open, sales1, sales_manager);
	reviewRepo.save(sales_open_1m);
	Review sales_open_2m = new Review(sales_open, sales2, sales_manager);
	reviewRepo.save(sales_open_2m);
	Review sales_open_3m = new Review(sales_open, sales3, sales_manager);
	reviewRepo.save(sales_open_3m);

	Review hr_closed1_12 = new Review(hr_closed1, hr1, hr2, Date.valueOf("2022-04-01"), 2,4,5,3,2,2,2,3);
	reviewRepo.save(hr_closed1_12);
	Review hr_closed1_13 = new Review(hr_closed1, hr1, hr3, Date.valueOf("2022-04-01"), 5,1,2,2,3,1,4,3);
	reviewRepo.save(hr_closed1_13);
	Review hr_closed1_21 = new Review(hr_closed1, hr2, hr1, Date.valueOf("2022-04-01"), 1,1,1,2,5,1,1,2);
	reviewRepo.save(hr_closed1_21);
	Review hr_closed1_23 = new Review(hr_closed1, hr2, hr3, Date.valueOf("2022-04-01"), 2,3,4,5,3,1,3,5);
	reviewRepo.save(hr_closed1_23);
	Review hr_closed1_31 = new Review(hr_closed1, hr3, hr1, Date.valueOf("2022-04-01"), 2,1,2,2,3,4,4,1);
	reviewRepo.save(hr_closed1_31);
	Review hr_closed1_32 = new Review(hr_closed1, hr3, hr2, Date.valueOf("2022-04-01"), 2,5,3,1,2,1,3,5);
	reviewRepo.save(hr_closed1_32);
	Review hr_closed1_m1 = new Review(hr_closed1, hr_manager, hr1, Date.valueOf("2022-04-01"), 2,2,3,1,4,5,4,1);
	reviewRepo.save(hr_closed1_m1);
	Review hr_closed1_m2 = new Review(hr_closed1, hr_manager, hr2, Date.valueOf("2022-04-01"), 1,3,5,4,2,3,5,4);
	reviewRepo.save(hr_closed1_m2);
	Review hr_closed1_m3 = new Review(hr_closed1, hr_manager, hr3, Date.valueOf("2022-04-01"), 5,2,3,1,4,5,1,1);
	reviewRepo.save(hr_closed1_m3);
	Review hr_closed1_1m = new Review(hr_closed1, hr1, hr_manager, Date.valueOf("2022-04-01"), 1,3,4,2,2,1,3,4);
	reviewRepo.save(hr_closed1_1m);
	Review hr_closed1_2m = new Review(hr_closed1, hr2, hr_manager, Date.valueOf("2022-04-01"), 4,2,2,3,4,5,4,4);
	reviewRepo.save(hr_closed1_2m);
	Review hr_closed1_3m = new Review(hr_closed1, hr3, hr_manager, Date.valueOf("2022-04-01"), 3,4,1,2,5,3,5,1);
	reviewRepo.save(hr_closed1_3m);
	
	Review hr_closed2_12 = new Review(hr_closed2, hr1, hr2, Date.valueOf("2022-03-01"), 3,3,4,5,4,3,3,2);
	reviewRepo.save(hr_closed2_12);
	Review hr_closed2_13 = new Review(hr_closed2, hr1, hr3, Date.valueOf("2022-03-01"), 2,1,2,2,4,5,4,1);
	reviewRepo.save(hr_closed2_13);
	Review hr_closed2_21 = new Review(hr_closed2, hr2, hr1, Date.valueOf("2022-03-01"), 3,2,5,5,4,5,2,1);
	hr_closed2_21.setComment("A pleasure to work with!");
	reviewRepo.save(hr_closed2_21);
	Review hr_closed2_23 = new Review(hr_closed2, hr2, hr3, Date.valueOf("2022-03-01"), 3,2,5,3,2,4,3,1);
	reviewRepo.save(hr_closed2_23);
	Review hr_closed2_31 = new Review(hr_closed2, hr3, hr1, Date.valueOf("2022-03-01"), 5,5,5,3,3,4,2,2);
	hr_closed2_31.setComment("Great personality!");
	reviewRepo.save(hr_closed2_31);
	Review hr_closed2_32 = new Review(hr_closed2, hr3, hr2, Date.valueOf("2022-03-01"), 1,4,2,4,5,3,4,4);
	reviewRepo.save(hr_closed2_32);
	Review hr_closed2_m1 = new Review(hr_closed2, hr_manager, hr1, Date.valueOf("2022-03-01"), 5,3,4,3,2,2,5,3);
	reviewRepo.save(hr_closed2_m1);
	Review hr_closed2_m2 = new Review(hr_closed2, hr_manager, hr2, Date.valueOf("2022-03-01"), 1,4,3,2,5,4,3,5);
	reviewRepo.save(hr_closed2_m2);
	Review hr_closed2_m3 = new Review(hr_closed2, hr_manager, hr3, Date.valueOf("2022-03-01"), 4,3,2,4,5,5,3,5);
	reviewRepo.save(hr_closed2_m3);
	Review hr_closed2_1m = new Review(hr_closed2, hr1, hr_manager, Date.valueOf("2022-03-01"), 5,5,5,2,5,4,5,2);
	reviewRepo.save(hr_closed2_1m);
	Review hr_closed2_2m = new Review(hr_closed2, hr2, hr_manager, Date.valueOf("2022-03-01"), 2,1,2,4,5,4,2,5);
	reviewRepo.save(hr_closed2_2m);
	Review hr_closed2_3m = new Review(hr_closed2, hr3, hr_manager, Date.valueOf("2022-03-01"), 3,3,3,4,5,1,3,2);
	reviewRepo.save(hr_closed2_3m);
	
	
	Review hr_open_12 = new Review(hr_open, hr1, hr2);
	reviewRepo.save(hr_open_12);
	Review hr_open_13 = new Review(hr_open, hr1, hr3);
	reviewRepo.save(hr_open_13);
	Review hr_open_21 = new Review(hr_open, hr2, hr1);
	reviewRepo.save(hr_open_21);
	Review hr_open_23 = new Review(hr_open, hr2, hr3);
	reviewRepo.save(hr_open_23);
	Review hr_open_31 = new Review(hr_open, hr3, hr1);
	reviewRepo.save(hr_open_31);
	Review hr_open_32 = new Review(hr_open, hr3, hr2);
	reviewRepo.save(hr_open_32);
	Review hr_open_m1 = new Review(hr_open, hr_manager, hr1);
	reviewRepo.save(hr_open_m1);
	Review hr_open_m2 = new Review(hr_open, hr_manager, hr2);
	reviewRepo.save(hr_open_m2);
	Review hr_open_m3 = new Review(hr_open, hr_manager, hr3);
	reviewRepo.save(hr_open_m3);
	Review hr_open_1m = new Review(hr_open, hr1, hr_manager);
	reviewRepo.save(hr_open_1m);
	Review hr_open_2m = new Review(hr_open, hr2, hr_manager);
	reviewRepo.save(hr_open_2m);
	Review hr_open_3m = new Review(hr_open, hr3, hr_manager);
	reviewRepo.save(hr_open_3m);
    }
    
}
