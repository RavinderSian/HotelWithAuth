package com.personal.hotel.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.personal.hotel.model.Guest;

@PreAuthorize("hasAuthority('USER')")
@Controller
public class HotelController {
	
	@GetMapping("test")
	public String register(Model model) {
		model.addAttribute("guest", new Guest());
		return "register";
	}
}
