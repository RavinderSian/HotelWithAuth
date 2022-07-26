package com.personal.hotel.publishers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.personal.hotel.events.SlotMachineWonEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SlotMachineWonEventPublisher {
	
	 @Autowired
	 private ApplicationEventPublisher applicationEventPublisher;
	 
	 public void publishSlotMachineWonEvent(String username, String discountCode) { 
		SlotMachineWonEvent slotMachineWonEvent =  new SlotMachineWonEvent(this, username, discountCode);
		applicationEventPublisher.publishEvent(slotMachineWonEvent);
		log.info("Slot machine won event published");
	 }

}
