package com.personal.hotel.controller;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.personal.hotel.auth.UserRepository;
import com.personal.hotel.model.Guest;
import com.personal.hotel.services.GuestServices;

@SpringBootTest
@AutoConfigureMockMvc
public class GuestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private GuestController controller;
	
	@MockBean
	private GuestServices services;
	
	@Mock
	private UserRepository userRepository;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.controller = new GuestController(services, userRepository);
	}

	@Test
	public void test() {
		assertThat(controller, not(nullValue()));
	}
	
	@Test
	public void test_Register_ReturnsCorrectView_WhenCalled() throws Exception {
		mockMvc.perform(get("/guests/newguest"))
				.andExpect(status().isOk())
				.andExpect(view().name("register"));
	}
	
	@WithMockUser(username = "rsian", password = "$2y$12$dOHmu8Hj5GlPzXs7PEivOugwiiqhehtDBeHNh5FLHfZb0FikRjgIm", roles = "USER")
	@Test
	public void test_AddGuest_ReturnsCorrectView_WhenCalled() throws Exception {
		mockMvc.perform(post("/newguest"))
				.andExpect(status().isOk());
	}

}
