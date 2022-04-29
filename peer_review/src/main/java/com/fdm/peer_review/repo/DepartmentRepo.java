package com.fdm.peer_review.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.fdm.peer_review.model.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Integer>{

    Department getByDepartmentName(String name);
    
    Optional<Department> findByDepartmentName(String name);
}
