package com.personal.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.personal.hotel.services.RoomServices;

@RequestMapping("/rooms")
@Controller
public class RoomController {
	
	private final RoomServices services;
	
	public RoomController(RoomServices services) {
		this.services = services;
	}

	@RequestMapping
	public String getEmptyRooms(Model model) {
		model.addAttribute("rooms", services.getEmptyRooms());
		return "rooms";
	}
}
