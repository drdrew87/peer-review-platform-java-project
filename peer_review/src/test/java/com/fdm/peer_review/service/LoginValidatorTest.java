package com.fdm.peer_review.service;

import static org.junit.jupiter.api.Assertions.*;
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
public class LoginValidatorTest {
    @Autowired
    private LoginValidator loginValidator;
    
    @MockBean
    private EmployeeRepo emRepo;
    
    @Mock
    private Employee user;
    
    @Test
    void test_LoginValidator_InvokesEmployeeRepo() {
	loginValidator.validate("username", "password");
	verify(emRepo).getByUserName("username");
    }
    
    @Test
    void test_LoginValidator_returnFalseWhenUsernameIsNotRegistered() {
	when(emRepo.getByUserName("username")).thenReturn(null);
	boolean result = loginValidator.validate("username", "password");
	assertFalse(result);
    }
    
    @Test
    void test_LoginValidator_returnFalseWhenUsernameIsRegisteredButPasswordNotCorrect() {
	when(emRepo.getByUserName("username")).thenReturn(user);
	when(user.getPassWord()).thenReturn("IncorrectPassword");
	boolean result = loginValidator.validate("username", "password");
	assertFalse(result);
    }
    
    @Test
    void test_LoginValidator_returnTrueWhenUsernameIsRegisteredAndPasswordIsCorrect() {
	when(emRepo.getByUserName("username")).thenReturn(user);
	when(user.getPassWord()).thenReturn("password");
	boolean result = loginValidator.validate("username", "password");
	assertTrue(result);
    }
}
