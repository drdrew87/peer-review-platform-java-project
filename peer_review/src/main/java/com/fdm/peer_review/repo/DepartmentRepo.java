package com.fdm.peer_review.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdm.peer_review.model.Department;

public interface DepartmentRepo extends JpaRepository<Department, Integer>{

}
