package com.personal.hotel.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.personal.hotel.auth.User;
import com.personal.hotel.auth.UserRepository;
import com.personal.hotel.model.Guest;
import com.personal.hotel.model.Room;
import com.personal.hotel.repository.GuestRepository;

@Component
public class GuestUserBootstrap implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private GuestRepository guestRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		User user = userRepository.findByUsername("rsian");
		
		Room room = new Room();
		room.setCapacity(2);
		room.setOccupied(false);
		
		Guest guest = new Guest();
		guest.setFirstName("ravi");
		guest.setLastName("sian");
		guest.setCardNumber("5188890121014283");
		guest.setRoom(room);
		guestRepository.save(guest);
		guest.setUser(user);
		userRepository.save(user);
		guestRepository.save(guest);
		
	}

}
