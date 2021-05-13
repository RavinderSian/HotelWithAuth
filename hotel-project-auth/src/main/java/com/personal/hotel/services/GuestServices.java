package com.personal.hotel.services;

import java.util.Optional;

import com.personal.hotel.model.Guest;

public interface GuestServices {

	Optional<Guest> findById(Long id);
	Guest save(Guest guest);
	void delete(Guest guest);
}
