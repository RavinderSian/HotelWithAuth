package com.personal.hotel.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.personal.hotel.auth.UserRepository;
import com.personal.hotel.communication.SendEmail;
import com.personal.hotel.events.SlotMachineWonEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SlotMachineWonEventListener implements ApplicationListener<SlotMachineWonEvent> {
	
	@Autowired
	private SendEmail emailSender;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void onApplicationEvent(SlotMachineWonEvent event) {
		
		 log.info("Slot machine won event occured and received");
		 emailSender.sendSimpleMessage(userRepository.findByUsername(event.getUsername()).getEmail(), "Winner",
			"Code: " + event.getDiscountCode());
	}

}
