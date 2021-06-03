package com.personal.hotel.controller;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.personal.hotel.auth.User;
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
	
	@Test
	public void test_AddGuest_ReturnsCorrectView_WhenCalled() throws Exception {
		
		Guest guest = new Guest();
		
		guest.setFirstName("rav");
		guest.setLastName("sian");
		guest.setCardNumber("5334070956810518");
		
		User user = new User();
		user.setUsername("rs");
		user.setPassword("rs");
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson = ow.writeValueAsString(guest);
		mockMvc.perform(post("/guests/newguest").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE).content(requestJson)
				.param("firstName", "rsn")
				.param("lastName", "rss")
				.param("cardNumber", "5334070956810518")
				.param("username", "rsv")
				.param("password", "rs"))
				.andExpect(status().isOk());
		
		
//		verify(services, times(1)).save(guest);
//			verify(userRepository, times(1)).save(user);
	}

	@Test
	public void test_AddGuest_ReturnsCorectView_WhenGivenNoInput() throws Exception {
		mockMvc.perform(post("/guests/newguest"))
				.andExpect(view().name("register"))
				.andExpect(status().isOk());
	}
	
}
