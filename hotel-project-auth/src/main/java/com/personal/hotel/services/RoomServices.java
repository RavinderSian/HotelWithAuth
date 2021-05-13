package com.personal.hotel.services;

import java.util.Optional;

import com.personal.hotel.model.Room;

public interface RoomServices {

	Optional<Room> findById(Long id);
	Room save(Room room);
	void delete(Room room);
}
