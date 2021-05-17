package com.personal.hotel.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.personal.hotel.model.Guest;
import com.personal.hotel.model.GuestDTO;
import com.personal.hotel.repository.GuestRepository;

@Service
public class GuestServicesImpl implements GuestServices {

	private final GuestRepository repository;
	private ModelMapper mapper = new ModelMapper();
	
	public GuestServicesImpl(GuestRepository repository) {
		this.repository = repository;
	}

	@Override
	public Optional<GuestDTO> findGuestDTOById(Long id) {
		return findGuestById(id).isPresent() ? Optional.of(mapper.map(findGuestById(id).get(), GuestDTO.class)) : Optional.empty();
	}
	
	@Override
	public Optional<Guest> findGuestById(Long id) {
		return repository.findById(id).isPresent()
		? repository.findById(id)
		: Optional.empty();
	}

	@Override
	public GuestDTO save(Guest guest) {
		Guest savedGuest = repository.save(guest);
		return mapper.map(savedGuest, GuestDTO.class);
	}

	@Override
	public void delete(Guest guest) {
		repository.delete(guest);
	}


}
