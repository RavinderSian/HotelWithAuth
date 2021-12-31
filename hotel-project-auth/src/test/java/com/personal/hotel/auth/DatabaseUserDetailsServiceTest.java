package com.personal.hotel.auth;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SpringBootTest
class DatabaseUserDetailsServiceTest {
	
	private DatabaseUserDetailsService service;

	@Mock
	private UserRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		service = new DatabaseUserDetailsService(repository);
	}

	@Test
	void test_Service_IsNotNull() {
		assertThat(service, is(notNullValue()));
	}
	
	@Test
	void test_LoadByUsername_ReturnsCorrectUserDetails_WhenGivenValidUsername() {
		
		User user = new User();
		user.setUsername("test");
		user.setAuthority("EMPLOYEE");
		
		when(repository.findByUsername("test")).thenReturn(user);
		UserDetails userDetails = service.loadUserByUsername("test");
		
		assertThat(userDetails.getUsername(), equalTo("test"));
		assertThat(userDetails.getAuthorities().size(), equalTo(1)); //cannot check for match to authority
		
	}
	
	@Test
	void test_LoadByUsername_ThrowsUsernameNotFoundException_WhenGivenInValidUsername() {
		
		UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {service.loadUserByUsername("test");},
							 "Failed login");
		
		assertThat(exception.getMessage(), equalTo("Failed login"));
		
	}

}
