package com.fdm.peer_review.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.fdm.peer_review.model.Department;

@DataJpaTest
public class DepartmentRepoTest {
    @Autowired
    private DepartmentRepo deptRepo;
    
    @Test
    void test_DepartmentRepo_saveDepartmentToDatabase() {
	Department sales = new Department("Sales");
	deptRepo.save(sales);
	ArrayList<Department> resultList = (ArrayList<Department>) deptRepo.findAll();
	assertEquals(1, resultList.size());
    }
    
    @Test
    void test_DepartmentRepo_deleteDepartmentFromDatabase() {
	Department sales = new Department("Sales");
	deptRepo.save(sales);
	deptRepo.delete(sales);
	assertEquals(0, deptRepo.count());
    }
    
    @Test
    void test_DepartmentRepo_getByName() {
	Department sales = new Department("Sales");
	deptRepo.save(sales);
	Department result = deptRepo.getByDepartmentName("Sales");
	assertEquals(sales, result);
    }
}
