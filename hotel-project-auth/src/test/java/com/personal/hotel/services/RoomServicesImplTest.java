package com.personal.hotel.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.personal.hotel.model.Room;
import com.personal.hotel.repository.RoomRepository;

@SpringBootTest
public class RoomServicesImplTest {

	private RoomServices services;
	
	@Mock
	private RoomRepository repository;
	
	@Mock
	private Room room;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.services = new RoomServicesImpl(repository);
		room = new Room();
		room.setCapacity(4);
	}

	@Test
	public void test_Service_IsNotNull() {
		assertThat(services, not(nullValue()));
	}
	
	@Test
	public void test_Save_CallsRepositorySave_WhenCalled() {
		services.save(room);
		verify(repository, times(1)).save(room);
	}
	
	@Test
	public void test_Save_ReturnsCorrectRoom_WhenGivenRoom() {
		when(repository.save(room)).thenReturn(room);
		Room roomResult = services.save(room);
		assertThat(roomResult, equalTo(room));
	}
	
	@Test
	public void test_Delete_CallsRepositoryDelete_WhenCalled() {
		services.delete(room);
		verify(repository, times(1)).delete(room);
	}
	
	@Test
	public void test_FindById_CallsRepositoryFindById_WhenCalled() {
		services.findById(1L);
		verify(repository, times(1)).findById(1L);
	}
	
	@Test
	public void test_FindById_ReturnsCorrectRoom_WhenGivenId1() {
		when(repository.findById(1L)).thenReturn(Optional.of(room));
		Optional<Room> roomOptional = services.findById(1L);
		assertThat(roomOptional, equalTo(Optional.of(room)));
	}
	
	@Test
	public void test_FindById_ReturnsEmptyOptional_WhenGivenId10() {
		Optional<Room> roomOptional = services.findById(10L);
		assertThat(roomOptional, equalTo(Optional.empty()));
	}

	@Test
	public void test_GetEmptyRooms_CallsRepositoryFindByOccupied() {
		services.getEmptyRooms();
		verify(repository, times(1)).findByOccupied(false);
	}
	
	@Test
	public void test_GetEmptyRooms_ReturnsListOfSize1_When1EmptyRoomPresent() {
		room.setOccupied(false);
		when(repository.findByOccupied(false)).thenReturn(Arrays.asList(room));
		List<Room> emptyRooms = services.getEmptyRooms();
		assertThat(emptyRooms.size(), equalTo(1));
	}
	
	@Test
	public void test_GetEmptyRooms_ReturnsEmptyList_WhenNoEmptyRoomPresent() {
		room.setOccupied(true);
		List<Room> emptyRooms = services.getEmptyRooms();
		assertThat(emptyRooms.size(), equalTo(0));
	}
	
}
