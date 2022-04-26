package com.fdm.peer_review.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdm.peer_review.model.Employee;

public interface EmployeeRepo  extends JpaRepository<Employee, Integer>{

}
