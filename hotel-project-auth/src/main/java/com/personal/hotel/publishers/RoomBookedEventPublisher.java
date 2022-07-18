package com.personal.hotel.publishers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.personal.hotel.communication.SendEmail;
import com.personal.hotel.events.RoomBookedEvent;
import com.personal.hotel.services.BookingServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RoomBookedEventPublisher {
	
	 @Autowired
	 private ApplicationEventPublisher applicationEventPublisher;
	 
	 public void publishRoomBookedEvent(Long id) { 
		RoomBookedEvent roomBookedEvent =  new RoomBookedEvent(this, id);
		applicationEventPublisher.publishEvent(roomBookedEvent);
		log.info("Room Booked event published");
	 }

}
