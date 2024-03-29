package com.personal.hotel.controller;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.personal.hotel.auth.User;
import com.personal.hotel.auth.UserRepository;
import com.personal.hotel.model.Booking;
import com.personal.hotel.model.DiscountCode;
import com.personal.hotel.model.Room;
import com.personal.hotel.publishers.RoomBookedEventPublisher;
import com.personal.hotel.services.BookingServices;
import com.personal.hotel.services.DiscountCodeService;
import com.personal.hotel.services.RoomServices;

@WebMvcTest(BookingController.class)
class BookingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private BookingController controller;
	
	@MockBean
	private BookingServices services;
	
	@MockBean
	private RoomServices roomServices;
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private RoomBookedEventPublisher publisher;
	
	@MockBean
	private DiscountCodeService discountCodeService;
	
	@BeforeEach
	void setUp() throws Exception {
		this.controller = new BookingController(services, roomServices, userRepository, publisher, discountCodeService);
	}
	
	@Test
	void test_Controller_IsNotNull() {
		assertThat(controller, not(nullValue()));
		assertThat(services, not(nullValue()));
		assertThat(roomServices, not(nullValue()));
		assertThat(userRepository, not(nullValue()));
		assertThat(publisher, not(nullValue()));

	}
	
	@Test
	@WithMockUser(username = "rsian", password = "pw", roles = "USER")
	void test_BookRoom_ReturnsCorrectViewAndPage_WhenCalledWithoutDiscountCode() throws Exception {
		
		User user = new User();
		user.setUsername("rsian");
		user.setPassword("rs");
		user.setAuthority("USER");
		
		Room room = new Room();
		room.setCapacity(2);
		room.setOccupied(false);
		when(roomServices.findById(1L)).thenReturn(Optional.of(room));
		when(userRepository.findByUsername("rsian")).thenReturn(user);
		
		mockMvc.perform(get("/booking/1/book/ "))
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/booking/yourbooking"));
		
		verify(userRepository, times(1)).findByUsername("rsian");
		verify(userRepository, times(1)).save(user);
		verify(roomServices, times(1)).findById(1L);
		verify(roomServices, times(1)).save(room);
		
	}
	
	@Test
	@WithMockUser(username = "rsian", password = "pw", roles = "USER")
	void test_BookRoom_ReturnsCorrectViewAndPage_WhenCalledWithDiscountCode() throws Exception {
		
		User user = new User();
		user.setUsername("rsian");
		user.setPassword("rs");
		user.setAuthority("USER");
		
		Room room = new Room();
		room.setCapacity(2);
		room.setOccupied(false);
		room.setPrice(Double.valueOf(40));
		
		DiscountCode discountCode = new DiscountCode();
		discountCode.setCode("AAAAAAAA");
		discountCode.setExpired(false);
		discountCode.setDiscountPercentage(Double.valueOf(40));
		
		Booking booking = new Booking();
		booking.setRoom(room);
		booking.setUser(user);
		booking.setCost(room.getPrice()*(100 - discountCode.getDiscountPercentage())/100);
		
		when(roomServices.findById(1L)).thenReturn(Optional.of(room));
		when(userRepository.findByUsername("rsian")).thenReturn(user);
		when(discountCodeService.findByDiscountCode("AAAAAAAA")).thenReturn(Optional.of(discountCode));
		
		mockMvc.perform(get("/booking/1/book/AAAAAAAA"))
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/booking/yourbooking"));
		
		verify(userRepository, times(1)).findByUsername("rsian");
		verify(userRepository, times(1)).save(user);
		verify(roomServices, times(1)).findById(1L);
		verify(roomServices, times(1)).save(room);
		verify(services, times(1)).save(booking);

	}
	
	@Test
	@WithMockUser(username = "rsian", password = "pw", roles = "USER")
	void test_YourBooking_ReturnsCorrectViewAndPage_WhenBookingPresent() throws Exception {
		
		User user = new User();
		user.setUsername("rsian");
		user.setPassword("rs");
		user.setAuthority("USER");
		
		
		Room room = new Room();
		room.setCapacity(2);
		room.setOccupied(false);
		
		Booking booking = new Booking();
		booking.setRoom(room);

		user.setBooking(booking);
		
		when(userRepository.findByUsername("rsian")).thenReturn(user);
		
		mockMvc.perform(get("/booking/yourbooking"))
		.andExpect(view().name("yourbooking"))
		.andExpect(model().attribute("username", "rsian"))
		.andExpect(model().attribute("booking", user.getBooking()))
		.andExpect(model().attribute("room", user.getBooking().getRoom()))
		.andExpect(status().isOk());
		
		verify(userRepository, times(1)).findByUsername("rsian");
	}
	
}
