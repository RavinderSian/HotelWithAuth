package com.personal.hotel.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.personal.hotel.model.Guest;
import com.personal.hotel.model.GuestDTO;
import com.personal.hotel.repository.GuestRepository;


@SpringBootTest
public class GuestServicesImplTest {

	private GuestServices services;
	
	@Mock
	private GuestRepository repository;
	
	@Mock
	private Guest guest;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.services = new GuestServicesImpl(repository);
		guest = new Guest();
		guest.setFirstName("rav");
	}
	
	@Test
	public void test_Service_IsNotNull() {
		assertThat(services, is(not(nullValue())));
	}
	
	@Test
	public void test_Save_CallsRepositorySave_WhenCalled() {
		when(repository.save(guest)).thenReturn(guest);
		services.save(guest);
		verify(repository, times(1)).save(guest);
	}
	
	@Test
	public void test_Save_ReturnsCorrectGuestDTO_WhenGivenGuest() {
		guest.setCardNumber("4716652914573998");
		when(repository.save(guest)).thenReturn(guest);
		GuestDTO guestDTO = services.save(guest);
		assertThat(guest.getFirstName(), equalTo(guestDTO.getFirstName()));
	}
	
	@Test
	public void test_Delete_CallsRepositoryDelete_WhenCalled() {
		services.delete(guest);
		verify(repository, times(1)).delete(guest);
	}
	
	@Test
	public void test_FindGuestById_CallsRepositoryFindById_WhenCalled() {
		services.findGuestById(1L);
		verify(repository, times(1)).findById(1L);
	}
	
	@Test
	public void test_FindGuestById_ReturnsCorrectGuest_WhenGivenId1() {
		when(repository.findById(1L)).thenReturn(Optional.of(guest));
		Optional<Guest> guestOptional = services.findGuestById(1L);
		assertThat(guestOptional, equalTo(Optional.of(guest)));
	}
	
	@Test
	public void test_FindGuestById_ReturnsEmptyOptional_WhenGivenId10() {
		Optional<Guest> guestOptional = services.findGuestById(10L);
		assertThat(guestOptional, equalTo(Optional.empty()));
	}
	
	@Test
	public void test_FindGuestDTOById_ReturnsCorrectGuest_WhenGivenId1() {
		when(repository.findById(1L)).thenReturn(Optional.of(guest));
		Optional<GuestDTO> guestDTOOptional = services.findGuestDTOById(1L);
		ModelMapper mapper = new ModelMapper();
		GuestDTO guestDTO = mapper.map(guest, GuestDTO.class);
		assertThat(guestDTOOptional, equalTo(Optional.of(guestDTO)));
	}
	
	@Test
	public void test_FindGuestDTOById_ReturnsEmptyOptional_WhenGivenId10() {
		Optional<GuestDTO> guestDTOOptional = services.findGuestDTOById(10L);
		assertThat(guestDTOOptional, equalTo(Optional.empty()));
	}

}
