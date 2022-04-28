package com.fdm.peer_review.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.fdm.peer_review.model.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Integer>{

}
