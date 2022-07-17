package com.personal.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.personal.hotel.services.DiscountCodeService;

@Controller
public class SlotMachineController {
	
	@Autowired
	private DiscountCodeService discountCodeService;
	
	//@PreAuthorize("hasRole('USER')")
	@GetMapping("/slotmachine")
	public String slotMachine(Model model) {
		
		model.addAttribute("discountCode", discountCodeService.generateDiscountCode(40));
		
		return "slotmachine.html";
	}
	
}
