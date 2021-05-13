package com.personal.hotel.services;

import java.util.Optional;

import com.personal.hotel.model.Booking;

public interface BookingServices {

	Optional<Booking> findById(Long id);
	Booking save(Booking booking);
	void delete(Booking booking);
	
}
