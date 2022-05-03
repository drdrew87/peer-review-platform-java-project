package com.fdm.peer_review.mvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fdm.peer_review.repo.DepartmentRepo;
import com.fdm.peer_review.repo.EmployeeRepo;
import com.fdm.peer_review.repo.PermissionRepo;
import com.fdm.peer_review.repo.ReviewRepo;
import com.fdm.peer_review.repo.ReviewRoundRepo;
import com.fdm.peer_review.service.RegistrationValidator;


@WebMvcTest
public class HomeControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private DepartmentRepo deptRepo;
    @MockBean
    private PermissionRepo permRepo;
    @MockBean
    private EmployeeRepo emRepo;
    @MockBean
    private ReviewRoundRepo RRRepo;
    @MockBean
    private ReviewRepo RRepo;
    @MockBean
    private RegistrationValidator registrationValidator;

    
    @Test
    void test_Gethomepage_returnsRightView() throws Exception {
	mvc.perform(MockMvcRequestBuilders.get("/"))
		.andExpect(MockMvcResultMatchers.view().name("index"));
    }
    
    @Test
    void test_GetRegisterPage_returnsRightView() throws Exception {
	mvc.perform(MockMvcRequestBuilders.get("/register"))
		.andExpect(MockMvcResultMatchers.view().name("register"));
    }
    
    @Test
    void test_Posthomepage_returnsRightView() throws Exception {
	mvc.perform(MockMvcRequestBuilders.post("/"))
		.andExpect(MockMvcResultMatchers.view().name("profile"));
    }
    
    @Test
    void test_PostRegister_returnsRightView() throws Exception {
	mvc.perform(MockMvcRequestBuilders.post("/register"))
		.andExpect(MockMvcResultMatchers.view().name("index"));
    }
}
