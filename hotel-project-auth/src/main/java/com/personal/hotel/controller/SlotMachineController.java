package com.personal.hotel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.personal.hotel.publishers.SlotMachineWonEventPublisher;
import com.personal.hotel.services.DiscountCodeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/slotmachine")
@PreAuthorize("hasRole('USER')")
public class SlotMachineController {
	
	private final DiscountCodeService discountCodeService;
	
	private final SlotMachineWonEventPublisher slotMachineWonEventPublisher;
	
	public SlotMachineController(DiscountCodeService discountCodeService,
			SlotMachineWonEventPublisher slotMachineWonEventPublisher) {
		this.discountCodeService = discountCodeService;
		this.slotMachineWonEventPublisher = slotMachineWonEventPublisher;
	}

	@GetMapping
	public String slotMachine() {
		return "slotmachine.html";
	}
	
	@GetMapping("/winner")
	public String slotMachineWinner(HttpServletRequest request) {
		
		String loggedInUser =  request.getUserPrincipal().getName();
		
		slotMachineWonEventPublisher.publishSlotMachineWonEvent(loggedInUser, 
				discountCodeService.generateDiscountCode(40).getCode());
		
		log.info("User has won: " + loggedInUser);
		
		return "redirect:/";
	}
	
}
