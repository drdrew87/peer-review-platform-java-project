package com.fdm.peer_review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.peer_review.model.Permission;
import com.fdm.peer_review.repo.PermissionRepo;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepo permissionRepo;
    
    public List<Permission> findAll() {
	return permissionRepo.findAll();
    }
    
    public Permission getById(int permissionId) {
	return permissionRepo.getById(permissionId);
    }
}
