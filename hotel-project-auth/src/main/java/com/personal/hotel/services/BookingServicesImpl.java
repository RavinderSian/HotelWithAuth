package com.personal.hotel.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.personal.hotel.model.Booking;
import com.personal.hotel.repository.BookingRepository;

@Service
public class BookingServicesImpl implements BookingServices {

	private final BookingRepository repository;
	
	public BookingServicesImpl(BookingRepository repository) {
		this.repository = repository;
	}

	@Override
	public Optional<Booking> findById(Long id) {
		return repository.findById(id).isPresent() ? repository.findById(id) : Optional.empty();
	}

	@Override
	public Booking save(Booking booking) {
		return repository.save(booking);
	}

	@Override
	public void delete(Booking booking) {
		repository.delete(booking);
		
	}

	
}
