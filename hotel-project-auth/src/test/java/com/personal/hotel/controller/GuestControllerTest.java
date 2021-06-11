package com.personal.hotel.controller;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.personal.hotel.auth.User;
import com.personal.hotel.auth.UserRepository;
import com.personal.hotel.model.Guest;
import com.personal.hotel.services.GuestServices;

@WebMvcTest(GuestController.class)
public class GuestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private GuestController controller;
	
	@MockBean
	private GuestServices services;
	
	@MockBean
	private UserRepository userRepository;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.controller = new GuestController(services, userRepository);
	}

	@Test
	public void test_Controller_IsNotNull() {
		assertThat(controller, not(nullValue()));
	}
	
	@Test
	public void test_Register_ReturnsCorrectView_WhenCalled() throws Exception {
		mockMvc.perform(get("/guests/newguest"))
				.andExpect(status().isOk())
				.andExpect(view().name("register"));
	}
	
	
	@Test
	public void test_addGuestGet_ReturnsCorrectStatusAndView_WhenCalled() throws Exception {
		
		mockMvc.perform(get("/guests/newguest"))
				.andExpect(view().name("register"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void test_AddGuest_ReturnsCorrectViewAndStatus_WhenGivenValidInput() throws Exception {
		
		Guest guest = new Guest();
		
		guest.setFirstName("rav");
		guest.setLastName("sian");
		guest.setCardNumber("5334070956810518");
		
		User user = new User();
		user.setUsername("rs");
		user.setPassword("rs");
		
		mockMvc.perform(post("/guests/newguest")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("firstName", "rsn")
				.param("lastName", "rss")
				.param("cardNumber", "5334070956810518")
				.param("user.username", "rsv")
				.param("user.password", "rs"))
				.andExpect(redirectedUrl("/"))
				.andExpect(status().isFound());
	}
	
	@Test
	public void test_AddGuest_ReturnsCorrectViewAndStatus_WhenGivenInvalidInput() throws Exception {
		
		Guest guest = new Guest();
		
		guest.setFirstName("rav");
		guest.setLastName("sian");
		guest.setCardNumber("5334070956810518");
		
		User user = new User();
		user.setUsername("rs");
		user.setPassword("rs");
		
		mockMvc.perform(post("/guests/newguest")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("firstName", "rsn")
				.param("lastName", "rss")
				.param("cardNumber", "55")
				.param("user.username", "rsv")
				.param("user.password", "rs"))
				.andExpect(view().name("register"))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void test_AddGuest_ReturnsCorectView_WhenGivenNoInput() throws Exception {
		mockMvc.perform(post("/guests/newguest"))
				.andExpect(view().name("register"))
				.andExpect(status().isBadRequest());
	}
	
}
