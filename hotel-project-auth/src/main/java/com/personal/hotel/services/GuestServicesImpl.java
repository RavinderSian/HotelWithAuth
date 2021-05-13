package com.personal.hotel.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.personal.hotel.model.Guest;
import com.personal.hotel.repository.GuestRepository;

@Service
public class GuestServicesImpl implements GuestServices {

	private final GuestRepository repository;
	
	public GuestServicesImpl(GuestRepository repository) {
		this.repository = repository;
	}

	@Override
	public Optional<Guest> findById(Long id) {
		return repository.findById(id).isPresent()
		? repository.findById(id)
		: Optional.empty();
	}

	@Override
	public Guest save(Guest guest) {
		return repository.save(guest);
	}

	@Override
	public void delete(Guest guest) {
		// TODO Auto-generated method stub
		
	}

}
