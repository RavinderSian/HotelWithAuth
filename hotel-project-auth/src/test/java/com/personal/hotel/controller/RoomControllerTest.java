package com.personal.hotel.controller;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.personal.hotel.model.DiscountCode;
import com.personal.hotel.model.Room;
import com.personal.hotel.services.DiscountCodeService;
import com.personal.hotel.services.RoomServices;

@SpringBootTest
@AutoConfigureMockMvc
class RoomControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	private RoomController controller;
	
	@MockBean
	private RoomServices services;
	
	@MockBean
	private DiscountCodeService discountCodeService;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.controller = new RoomController(services, discountCodeService);
	}

	@Test
	void test_Controller_IsNotNull() {
		assertThat(controller, not(nullValue()));
	}
	
	@Test
	void test_GetEmptyRooms_ReturnsCorrectPageAndListOfRooms_WhenCalled() throws Exception {
		
		Room room = new Room();
		room.setId(1L);
		room.setRoomNumber(50);
		room.setOccupied(false);
		room.setPrice(40.00);
		room.setCapacity(3);;
		
		Room secondRoom = new Room();
		secondRoom.setId(2L);
		secondRoom.setRoomNumber(45);
		secondRoom.setOccupied(false);
		secondRoom.setPrice(40.00);
		secondRoom.setCapacity(3);
		
		List<Room> rooms = Arrays.asList(room, secondRoom);
		
		when(services.getEmptyRooms()).thenReturn(Arrays.asList(room, secondRoom));
		
		mockMvc.perform(get("/rooms"))
		.andExpect(status().isOk())
		.andExpect(view().name("rooms"))
		.andExpect(model().attribute("rooms", hasSize(2)))
		.andExpect(model().attribute("rooms", rooms));
		
		verify(services, times(1)).getEmptyRooms();
	}
	
	@Test
	void test_getEmptyRooms_ReturnsEmptyPage_WhenNoRoomsUnoccupied() throws Exception {
		
		when(services.getEmptyRooms()).thenReturn(new ArrayList<>());
		
		mockMvc.perform(get("/rooms"))
		.andExpect(status().isOk())
		.andExpect(view().name("rooms"))
		.andExpect(model().attribute("rooms", hasSize(0)));
		
		verify(services, times(1)).getEmptyRooms();
	}
	
	
	@Test
	void test_ApplyDiscount_ReturnsCorrectPageAndListOfRooms_WhenCalledWithValidDiscount() throws Exception {
		
		Room room = new Room();
		room.setId(1L);
		room.setRoomNumber(50);
		room.setOccupied(false);
		room.setPrice(40.00);
		room.setCapacity(3);;
		
		Room secondRoom = new Room();
		secondRoom.setId(2L);
		secondRoom.setRoomNumber(45);
		secondRoom.setOccupied(false);
		secondRoom.setPrice(40.00);
		secondRoom.setCapacity(3);
		
		DiscountCode discountCode = new DiscountCode();
		discountCode.setCode("AAAAAAAA");
		discountCode.setExpired(false);
		discountCode.setDiscountPercentage(Double.valueOf(40));
		
		
		List<Room> rooms = Arrays.asList(room, secondRoom);
		
		when(services.getEmptyRooms()).thenReturn(Arrays.asList(room, secondRoom));
		when(discountCodeService.verifyDiscountCode(discountCode.getCode())).thenReturn(Optional.of(discountCode));
		
		rooms.forEach(hotelRoom -> hotelRoom.setPrice(hotelRoom.getPrice() * ( (100 - discountCode.getDiscountPercentage())/100)));
		
		mockMvc.perform(post("/rooms").contentType(MediaType.APPLICATION_FORM_URLENCODED)
		.param("code", discountCode.getCode()))
		.andExpect(status().isOk())
		.andExpect(view().name("rooms"))
		.andExpect(model().attribute("discountCodeString", discountCode.getCode()))
		.andExpect(model().attribute("rooms", hasSize(2)))
		.andExpect(model().attribute("rooms", rooms));
		
		verify(services, times(1)).getEmptyRooms();
		verify(discountCodeService, times(1)).verifyDiscountCode(discountCode.getCode());

	}
	
	@Test
	void test_ApplyDiscount_ReturnsCorrectPageAndListOfRooms_WhenCalledWithInvalidDiscount() throws Exception {
		
		Room room = new Room();
		room.setId(1L);
		room.setRoomNumber(50);
		room.setOccupied(false);
		room.setPrice(40.00);
		room.setCapacity(3);;
		
		Room secondRoom = new Room();
		secondRoom.setId(2L);
		secondRoom.setRoomNumber(45);
		secondRoom.setOccupied(false);
		secondRoom.setPrice(40.00);
		secondRoom.setCapacity(3);
		
		DiscountCode discountCode = new DiscountCode();
		discountCode.setCode("AAAAAAAA");
		discountCode.setExpired(false);
		discountCode.setDiscountPercentage(Double.valueOf(40));
		
		
		List<Room> rooms = Arrays.asList(room, secondRoom);
		
		when(services.getEmptyRooms()).thenReturn(Arrays.asList(room, secondRoom));
		
		rooms.forEach(hotelRoom -> hotelRoom.setPrice(hotelRoom.getPrice() * ( (100 - discountCode.getDiscountPercentage())/100)));
		
		mockMvc.perform(post("/rooms").contentType(MediaType.APPLICATION_FORM_URLENCODED)
		.param("code", discountCode.getCode()))
		.andExpect(status().isOk())
		.andExpect(view().name("rooms"))
		.andExpect(model().attribute("discountCodeString", " "))
		.andExpect(model().attribute("rooms", hasSize(2)))
		.andExpect(model().attribute("rooms", rooms));
		
		verify(services, times(1)).getEmptyRooms();
		verify(discountCodeService, times(1)).verifyDiscountCode(discountCode.getCode());

	}

}
