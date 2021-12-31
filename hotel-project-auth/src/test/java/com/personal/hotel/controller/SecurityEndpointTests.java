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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.personal.hotel.auth.User;
import com.personal.hotel.auth.UserRepository;

@AutoConfigureMockMvc
@SpringBootTest
class SecurityEndpointTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserRepository userRepository;
	
	@BeforeEach
	public void setUp() throws Exception {
	}

	//@WithMockUser(username = "rsian", password = "pw", roles = "USER")
	@Test
	void test_Login_ReturnsCorrectStatusAndView_WhenGivenValidUser() throws Exception {
		mockMvc.perform(post("/peformlogin")
				.param("rsian", "pw"))
				.andExpect(status().isOk());
	}

}
