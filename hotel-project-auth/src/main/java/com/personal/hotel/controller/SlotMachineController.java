package com.personal.hotel.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SlotMachineController {

	//@PreAuthorize("hasRole('USER')")
	@GetMapping("/slotmachine")
	public String slotMachine() {
		return "slotmachine.html";
	}
	
}
