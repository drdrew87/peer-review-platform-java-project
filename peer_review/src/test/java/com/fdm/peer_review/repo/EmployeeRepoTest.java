package com.fdm.peer_review.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.fdm.peer_review.model.Department;
import com.fdm.peer_review.model.Employee;
import com.fdm.peer_review.model.Permission;

@DataJpaTest
public class EmployeeRepoTest {

    @Autowired
    private EmployeeRepo emRepo;
    @Autowired
    private DepartmentRepo deptRepo;
    @Autowired
    private PermissionRepo permRepo;
    Department dept1;
    Department dept2;
    Permission perm1;
    Permission perm2;
    
    @BeforeEach
    void setUp() {
	dept1 = new Department("Sales");
	dept2 = new Department("Academy");
	deptRepo.save(dept1);
	deptRepo.save(dept2);
	perm1 = new Permission(false, false);
	perm2 = new Permission(true, false);
	permRepo.save(perm1);
	permRepo.save(perm2);
    }
    
    @Test
    void test_EmployeeRepo_saveEmployeeToDatabase() {
	Employee em = new Employee("John", "Doe", "joe.doe", "12345", dept1, perm1);
	emRepo.save(em);
	assertEquals(1, emRepo.count());
    }
    
    @Test
    void test_EmployeeRepo_deleteEmployeeFromDatabase() {
	Employee em = new Employee("John", "Doe", "joe.doe", "12345", dept1, perm1);
	emRepo.save(em);
	emRepo.delete(em);
	assertEquals(0, emRepo.count());
    }
    
    @Test
    void test_EmployeeRepo_getByUserName() {
	Employee em1 = new Employee("John", "Doe", "joe.doe", "12345", dept1, perm1);
	emRepo.save(em1);
	Employee result = emRepo.getByUserName("joe.doe");
	assertEquals("12345", result.getPassWord());
    }
    
    @Test
    void test_EmployeeRepo_getByFirstName() {
	Employee em1 = new Employee("John", "Doe", "joe.doe", "12345", dept1, perm1);
	emRepo.save(em1);
	Employee em2 = new Employee("John", "Curry", "curry", "12345", dept1, perm1);
	emRepo.save(em2);
	ArrayList<Employee> resultList = (ArrayList<Employee>) emRepo.getByFirstName("John");
	assertEquals(2, resultList.size());
	assertEquals("12345", resultList.get(0).getPassWord());
    }
    
    @Test
    void test_EmployeeRepo_getByLastName() {
	Employee em1 = new Employee("John", "Doe", "joe.doe", "12345", dept1, perm1);
	emRepo.save(em1);
	Employee em2 = new Employee("John", "Curry", "curry", "12345", dept1, perm1);
	emRepo.save(em2);
	ArrayList<Employee> resultList = (ArrayList<Employee>) emRepo.getByLastName("Curry");
	assertEquals(1, resultList.size());
	assertEquals("12345", resultList.get(0).getPassWord());
    }
    
    @Test
    void test_EmployeeRepo_getByDepartment() {
	Employee em1 = new Employee("John", "Doe", "joe.doe", "12345", dept1, perm1);
	emRepo.save(em1);
	Employee em2 = new Employee("John", "Curry", "curry", "12345", dept1, perm1);
	emRepo.save(em2);
	ArrayList<Employee> resultList = (ArrayList<Employee>) emRepo.getByDepartment(dept1);
	assertEquals(2, resultList.size());
	ArrayList<Employee> resultList2 = (ArrayList<Employee>) emRepo.getByDepartment(dept2);
	assertEquals(0, resultList2.size());
    }
}
