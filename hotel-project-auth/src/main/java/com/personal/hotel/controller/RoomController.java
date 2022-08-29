package com.personal.hotel.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.personal.hotel.model.DiscountCode;
import com.personal.hotel.model.Room;
import com.personal.hotel.services.DiscountCodeService;
import com.personal.hotel.services.RoomServices;

@RequestMapping("/rooms")
@Controller
public class RoomController {
	
	private final RoomServices service;
	private final DiscountCodeService discountCodeService;
	
	public RoomController(RoomServices services, DiscountCodeService discountCodeService) {
		this.service = services;
		this.discountCodeService = discountCodeService;
	}

	@GetMapping
	public String getEmptyRooms(Model model) {
		model.addAttribute("rooms", service.getEmptyRooms());
		model.addAttribute("discountCode", new DiscountCode());
		model.addAttribute("discountCodeString", " ");

		return "rooms";
	}
	
	@PostMapping
	public String applyDiscount(DiscountCode discountCode, Model model) {
		
		Optional<DiscountCode> discount = discountCodeService.verifyDiscountCode(discountCode.getCode());
		List<Room> rooms = service.getEmptyRooms();

		if (discount.isPresent()) {
			rooms.forEach(room -> room.setPrice(room.getPrice() * ( (100 - discount.get().getDiscountPercentage())/100)));
			
			model.addAttribute("discountCodeString", discount.get().getCode());
			model.addAttribute("rooms", rooms);
		} else {
			
			model.addAttribute("discountCodeString", " ");
			model.addAttribute("rooms", rooms);
			
		}
		
		return "rooms";
	}
	
}
