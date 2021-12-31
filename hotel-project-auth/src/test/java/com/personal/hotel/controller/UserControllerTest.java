package com.personal.hotel.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.personal.hotel.auth.User;
import com.personal.hotel.auth.UserRepository;

@WebMvcTest(UserController.class)
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private UserController controller;
	
	@MockBean
	private UserRepository userRepository;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.controller = new UserController(userRepository);
	}

	@Test
	void test_Controller_IsNotNull() {
		assertThat(controller, not(nullValue()));
	}
	
	@Test
	void test_addUserGet_ReturnsCorrectStatusAndView_WhenCalled() throws Exception {
		mockMvc.perform(get("/user/newuser"))
				.andExpect(view().name("register"))
				.andExpect(status().isOk());
	}
	
	@Test
	void test_RegisterUser_ReturnsCorrectViewAndStatus_WhenGivenValidInput() throws Exception {
		
		User user = new User();
		user.setUsername("rs");
		user.setPassword("rs");
		
		mockMvc.perform(post("/user/newuser")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("username", "rsv")
				.param("password", "rs")
				.param("cardNumber", "5467933247891697")
				.param("email", "testing@hotmail.co.uk")
				.param("firstName", "rsv")
				.param("lastName", "rs"))
				.andExpect(redirectedUrl("/"))
				.andExpect(status().isFound());
	}
	
	@Test
	void test_RegisterUser_ReturnsCorrectViewAndStatus_WhenGivenInvalidGuestInput() throws Exception {
		
		User user = new User();
		user.setUsername("rs");
		user.setPassword("rs");
		
		mockMvc.perform(post("/user/newuser")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("username", "rsv"))
				.andExpect(view().name("register"))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	void test_RegisterUser_ReturnsCorectViewAndStatus_WhenGivenNoInput() throws Exception {
		mockMvc.perform(post("/user/newuser"))
				.andExpect(view().name("register"))
				.andExpect(status().isBadRequest());
	}
	
	@WithMockUser(username = "rsian", password = "pw", roles = "USER")
	@Test
	void test_changePasswordGet_ReturnsCorrectStatusAndView_WhenCalled() throws Exception {
		mockMvc.perform(get("/user/changepassword"))
				.andExpect(view().name("changepassword.html"))
				.andExpect(status().isOk());
	}
	
	@WithMockUser(username = "rs", password = "pw", roles = "USER")
	@Test
	void test_ChangePassword_ReturnsCorrectViewAndStatus_WhenGivenValidInput() throws Exception {
		
		User user = new User();
		user.setUsername("rs");
		user.setPassword("rs");
		user.setCardNumber("5467933247891697");
		user.setEmail("testing@hotmail.co.uk");
		user.setFirstName("rav");
		user.setLastName("sian");
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		when(userRepository.findByUsername("rs")).thenReturn(user);
		
		mockMvc.perform(post("/user/changepassword")
				.contentType(MediaType.APPLICATION_JSON)
				.param("password", "test"))
				.andExpect(redirectedUrl("/"))
				.andExpect(status().isFound());
		assertThat(encoder.matches("test", user.getPassword()), is(true));
	}
	
	@WithMockUser(username = "rsian", password = "pw", roles = "USER")
	@Test
	void test_changePasswordPost_ReturnsCorrectStatusAndView_WhenGivenEmptyPassword() throws Exception {
		mockMvc.perform(post("/user/changepassword")
				.param("password", "t"))
				.andExpect(view().name("changepasswordempty.html"))
				.andExpect(status().isBadRequest());
	}
	
	@WithMockUser(username = "rsian", password = "pw", roles = "USER")
	@Test
	void test_Login_ReturnsCorrectStatusAndView_WhenGivenEmptyPassword() throws Exception {
		mockMvc.perform(post("/login")
				.param("rsian", "pw"))
				.andExpect(status().isOk());
	}

}
