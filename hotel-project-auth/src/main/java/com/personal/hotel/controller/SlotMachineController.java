package com.personal.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SlotMachineController {

	@GetMapping("/slotmachine")
	public String slotMachine() {
		return "slotmachine.html";
	}
	
}
