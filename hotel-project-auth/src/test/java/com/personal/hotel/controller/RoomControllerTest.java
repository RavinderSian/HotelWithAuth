package com.personal.hotel.controller;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.personal.hotel.model.Room;
import com.personal.hotel.services.RoomServices;

@SpringBootTest
@AutoConfigureMockMvc
public class RoomControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	private RoomController controller;
	
	@MockBean
	private RoomServices services;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.controller = new RoomController(services);
	}

	@Test
	public void test_Controller_IsNotNull() {
		assertThat(controller, not(nullValue()));
	}
	
	@Test
	public void test_getEmptyRooms_ReturnsCorrectPageAndListOfRooms_WhenCalled() throws Exception {
		
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
	public void test_getEmptyRooms_ReturnsEmptyPage_WhenNoRoomsUnoccupied() throws Exception {
		
		when(services.getEmptyRooms()).thenReturn(new ArrayList<>());
		
		mockMvc.perform(get("/rooms"))
		.andExpect(status().isOk())
		.andExpect(view().name("rooms"))
		.andExpect(model().attribute("rooms", hasSize(0)));
		
		verify(services, times(1)).getEmptyRooms();
	}

}
