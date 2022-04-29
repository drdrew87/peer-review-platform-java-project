package com.fdm.peer_review.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fdm.peer_review.model.Employee;
import com.fdm.peer_review.repo.EmployeeRepo;

@SpringBootTest
public class RegistrationValidatorTest {

    @Autowired
    private RegistrationValidator registrationValidator;
    
    @MockBean
    private EmployeeRepo emRepo;
    @Mock
    private Employee em1;

   
    
    @Test
    void test_RegistrationValidator_InvokeEmployeeRepo() {
	when(em1.getUserName()).thenReturn("User1");
	registrationValidator.validate(em1);
	verify(emRepo).getByUserName("User1");
    }
    
    
    @Test
    void test_RegistrationValidator_returnsFalseWhenUsernameIsTaken() {
	when(em1.getUserName()).thenReturn("User1");
	when(emRepo.getByUserName(anyString())).thenReturn(em1);
	assertFalse(registrationValidator.validate(em1));
    }
    
    @Test
    void test_RegistrationValidator_returnsTrueWhenUsernameIsNotTaken() {
	when(em1.getUserName()).thenReturn("User1");
	when(emRepo.getByUserName(anyString())).thenReturn(null);
	assertTrue(registrationValidator.validate(em1));
    }
}
