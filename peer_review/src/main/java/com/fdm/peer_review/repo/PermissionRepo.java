package com.fdm.peer_review.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdm.peer_review.model.Permission;

@Repository
public interface PermissionRepo extends JpaRepository<Permission, Integer>{

}
