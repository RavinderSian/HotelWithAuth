package com.personal.hotel.controller;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.personal.hotel.auth.DatabaseUserDetailsService;
import com.personal.hotel.auth.UserRepository;
import com.personal.hotel.model.Room;
import com.personal.hotel.repository.GuestRepository;
import com.personal.hotel.services.BookingServices;
import com.personal.hotel.services.RoomServices;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private BookingController controller;
	
	@Mock
	private GuestRepository guestRepository;
	
	@Mock
	private BookingServices services;
	
	@Mock
	private RoomServices roomServices;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private DatabaseUserDetailsService userService;
	
	
	
	@BeforeEach
	public void setUp() throws Exception {
		this.controller = new BookingController(services, roomServices, userRepository);
	}
	
	@Test
	public void test_Controller_IsNotNull() {
		assertThat(controller, not(nullValue()));
	}
	
	@Test
	@WithMockUser(username = "rsian", password = "password", roles = "USER")
	public void test_BookRoom_ReturnsCorrectViewAndPage_WhenCalled() throws Exception {
		
		Room room = new Room();
		room.setCapacity(2);
		room.setOccupied(false);
		
//		User user = new User();
//		user.setUsername("rsian");
//		user.setPassword("pw");
//		user.setAuthority("USERrrrr");
//		
//		
//		Guest guest = new Guest();
//		guest.setFirstName("ravi");
//		guest.setLastName("sian");
//		guest.setCardNumber("5188890121014283");
//		guest.setRoom(room);
//		guest.setUser(user);
//		user.setGuest(guest);
//		
//		Booking booking = new Booking();
//		
//		
//		when(userRepository.findByUsername("rsian")).thenReturn(user);
		when(roomServices.findById(1L)).thenReturn(Optional.of(room));
		
		mockMvc.perform(get("/booking/1/book"))
		.andExpect(redirectedUrl("/booking/yourbooking"))
		.andExpect(status().isFound());
		
	}

}
