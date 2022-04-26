package com.fdm.peer_review.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdm.peer_review.model.Permission;

public interface PermissionRepo extends JpaRepository<Permission, Integer>{

}
