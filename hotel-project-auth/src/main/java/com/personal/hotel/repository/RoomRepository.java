package com.personal.hotel.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.personal.hotel.model.Room;

public interface RoomRepository extends CrudRepository<Room, Long> {

	List<Room> findByOccupied(boolean occupied);
}
