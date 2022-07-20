package com.personal.hotel.events;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.Setter;

public class SlotMachineWonEvent extends ApplicationEvent {
	
	@Getter
	@Setter
	private String username;
	
	@Getter
	@Setter
	private String discountCode;

	public SlotMachineWonEvent(Object source, String username, String discountCode) {
		super(source);
		this.username = username;
		this.discountCode = discountCode;
	}
	
	

}
