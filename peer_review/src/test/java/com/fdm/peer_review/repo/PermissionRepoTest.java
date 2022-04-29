package com.fdm.peer_review.repo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.fdm.peer_review.model.Permission;

@DataJpaTest
public class PermissionRepoTest {

    @Autowired
    private PermissionRepo permRepo;
    
    @Test
    void test_PermissionRepo_savePermissionToDatabase() {
	Permission perm1 = new Permission(true,false);
	permRepo.save(perm1);
	ArrayList<Permission> resultList = (ArrayList<Permission>) permRepo.findAll();
	assertEquals(1, resultList.size());
    }
    
    @Test
    void test_PermissionRepo_deletePermissionFromDatabase() {
	Permission perm1 = new Permission(true,false);
	permRepo.save(perm1);
	permRepo.delete(perm1);
	ArrayList<Permission> resultList = (ArrayList<Permission>) permRepo.findAll();
	assertEquals(0, resultList.size());
    }
}
