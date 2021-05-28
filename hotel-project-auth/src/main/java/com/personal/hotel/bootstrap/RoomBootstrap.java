package com.personal.hotel.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.personal.hotel.model.Room;
import com.personal.hotel.services.RoomServices;

@Component
public class RoomBootstrap implements CommandLineRunner {

	@Autowired
	private RoomServices services;
	
	@Override
	public void run(String... args) throws Exception {
		Room room = new Room();
		room.setRoomNumber(42);
		room.setCapacity(3);
		room.setOccupied(false);
		room.setPrice(25.00);
		services.save(room);
		
		Room roomSecond = new Room();
		roomSecond.setRoomNumber(43);
		roomSecond.setCapacity(3);
		roomSecond.setOccupied(false);
		roomSecond.setPrice(25.00);
		services.save(roomSecond);
	}

}
