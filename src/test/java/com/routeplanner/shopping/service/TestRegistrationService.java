package com.routeplanner.shopping.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.atLeastOnce;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.routeplanner.shopping.User;
import com.routeplanner.shopping.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)  
public class TestRegistrationService {

	@Mock  // mocks into myService
	private UserRepository repository;
	
	@Mock  // mocks into myService
	private UserRepository userRepository;
	
	@InjectMocks    // class is instantaited and has all mock objects injected automatically!!!
	private RegistrationService service;
	
	
	public TestRegistrationService() {
		
	}
	
	
	@Test
	public void testFindByUsername() {
		
	}
	
	
	@Test // simply a mocking exercise on repository
	public void testFindUser() {
		
		User user = new User();
		user.setUserName("username1");
		user.setPassword("NON-ENC PASS");
		user.setRoles("ROLE_USER");
		user.setActive(true);
		//user.setFullname("iyuiuyiuy");
		
		Mockito.when(userRepository.findByUserName("username1")).thenReturn(Optional.of(user));
		
		User outputUser = service.findUser("username1").get();
		assertEquals("username1", outputUser.getUserName());
		
		Mockito.verify(userRepository).findByUserName("username1"); // verifies that the method was called
		Mockito.verify(userRepository, atLeastOnce()).findByUserName("username1"); // verifies it was called at least once
		Mockito.verify(userRepository, times(1)).findByUserName("username1"); // verifies it was called x times
		
		
		
		
		
		
	}
	

}
