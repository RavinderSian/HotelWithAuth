package com.personal.hotel.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.personal.hotel.model.Room;
import com.personal.hotel.repository.RoomRepository;

@Service
public class RoomServicesImpl implements RoomServices {
	
	private final RoomRepository repository;
	
	public RoomServicesImpl(RoomRepository repository) {
 		this.repository = repository;
	}

	@Override
	public Optional<Room> findById(Long id) {
		return repository.findById(id).isPresent()
		? repository.findById(id)
		: Optional.empty();
	}

	@Override
	public Room save(Room room) {
		return repository.save(room);
	}

	@Override
	public void delete(Room room) {
		repository.delete(room);
		
	}

}
