package com.fdm.peer_review.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdm.peer_review.model.Department;
import com.fdm.peer_review.model.Employee;

@Repository
public interface EmployeeRepo  extends JpaRepository<Employee, Integer>{
    
    Employee getByUserName(String userName);
    
    List<Employee> getByFirstName(String firstName);
    
    List<Employee> getByLastName(String lastName);
    
    Optional<Employee> findByUserName(String userName);

    Optional<List<Employee>> findByFirstName(String firstName);
    
    Optional<List<Employee>> findByLastName(String lastName);

    List<Employee> getByDepartment(Department dept);
}
