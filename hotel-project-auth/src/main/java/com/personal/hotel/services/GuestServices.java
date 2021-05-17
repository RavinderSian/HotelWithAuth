package com.personal.hotel.services;

import java.util.Optional;

import com.personal.hotel.model.Guest;
import com.personal.hotel.model.GuestDTO;

public interface GuestServices {

	Optional<GuestDTO> findGuestDTOById(Long id);
	Optional<Guest> findGuestById(Long id);
	GuestDTO save(Guest guest);
	void delete(Guest guest);
}
