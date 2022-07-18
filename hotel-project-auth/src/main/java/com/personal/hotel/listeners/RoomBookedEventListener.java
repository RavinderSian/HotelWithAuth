package com.personal.hotel.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.personal.hotel.communication.SendEmail;
import com.personal.hotel.events.RoomBookedEvent;
import com.personal.hotel.services.BookingServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RoomBookedEventListener implements ApplicationListener<RoomBookedEvent> {
	
	@Autowired
	private BookingServices bookingService;
	
	@Autowired
	private SendEmail emailSender;

	@Override
	public void onApplicationEvent(RoomBookedEvent event) {
		
		if (bookingService.findById(event.getBookingId()).isEmpty()) {
			 log.info("Id not present");
		 } else {
			 log.info("Room booked event occured and received");
			 emailSender.sendSimpleMessage(bookingService.findById(event.getBookingId()).get().getUser().getEmail(), "Room Booked",
				"Booking id: " + event.getBookingId());
		 }
	}

}
