package com.personal.hotel.services;

import static org.hamcrest.CoreMatchers.equalTo;
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
import org.springframework.boot.test.context.SpringBootTest;

import com.personal.hotel.model.Booking;
import com.personal.hotel.repository.BookingRepository;

@SpringBootTest
public class BookingServicesImplTest {

	private BookingServices services;
	
	@Mock
	private BookingRepository repository;
	
	@Mock
	private Booking booking;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.services = new BookingServicesImpl(repository);
		booking = new Booking();
		booking.setCost(50.00);
	}

	@Test
	public void test_Service_IsNotNull() {
		assertThat(services, not(nullValue()));
	}
	
	@Test
	public void test_Save_CallsRepositorySave_WhenCalled() {
		services.save(booking);
		verify(repository, times(1)).save(booking);
	}
	
	@Test
	public void test_Save_ReturnsCorrectBooking_WhenGivenBooking() {
		when(repository.save(booking)).thenReturn(booking);
		Booking bookingResult = services.save(booking);
		assertThat(bookingResult, equalTo(booking));
	}
	
	@Test
	public void test_Delete_CallsRepositoryDelete_WhenCalled() {
		services.delete(booking);
		verify(repository, times(1)).delete(booking);
	}
	
	@Test
	public void test_FindById_CallsRepositoryFindById_WhenCalled() {
		services.findById(1L);
		verify(repository, times(1)).findById(1L);
	}
	
	@Test
	public void test_FindById_ReturnsCorrectBooking_WhenGivenId1() {
		when(repository.findById(1L)).thenReturn(Optional.of(booking));
		Optional<Booking> bookingOptional = services.findById(1L);
		assertThat(bookingOptional, equalTo(Optional.of(booking)));
	}
	
	@Test
	public void test_FindById_ReturnsEmptyOptional_WhenGivenId10() {
		Optional<Booking> bookingOptional = services.findById(10L);
		assertThat(bookingOptional, equalTo(Optional.empty()));
	}

}
