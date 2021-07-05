package com.cg.onlinepizza.service;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.onlinepizza.model.Admin;
import com.cg.onlinepizza.dao.adminRepository;
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AdminLoginTest {
	
	@InjectMocks
	AdminLogin service;
	@Mock
	adminRepository dao;
	@Test
	public void addUser()
	{
		Admin admin = new Admin();
		admin.setAdminid("1");
		when(service.addNewUser(admin)).thenReturn(admin);
	}
	
	@Test
	public void login()
	{
		Admin admin = new Admin();
		admin.setAdminid("1");
		admin.setAdminName("zan");
		admin.setPassword("de");
		when(dao.existsById("1")).thenReturn(true);
		Admin user1 = new Admin();
		user1.setAdminid("1");
		user1.setAdminName("zan");
		user1.setPassword("de");	
		assertEquals(admin.getAdminid(), "1");
		assertNotEquals(service.signIn("zan", "de"), "Logged In SuccessFully");
		
		
	}

}
