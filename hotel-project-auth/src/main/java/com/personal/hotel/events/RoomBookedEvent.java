package com.personal.hotel.events;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.Setter;

public class RoomBookedEvent extends ApplicationEvent {

	@Getter
	@Setter
	private Long bookingId;

	public RoomBookedEvent(Object source, Long bookingId) {
		super(source);
		this.bookingId = bookingId;
	}
	
	
	
}
