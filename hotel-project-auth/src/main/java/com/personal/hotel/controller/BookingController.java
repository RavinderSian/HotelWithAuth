package com.personal.hotel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.personal.hotel.services.BookingServices;
import com.personal.hotel.services.RoomServices;

@PreAuthorize("hasRole('USER')")
@RequestMapping("booking/")
@Controller
public class BookingController {

	private final BookingServices services;
	private final RoomServices roomServices;

	public BookingController(BookingServices services, RoomServices roomServices) {
		this.services = services;
		this.roomServices = roomServices;
	}

	@GetMapping("/{roomId}/book")
	public String bookRoom(Model model, HttpServletRequest request) {
		String loggedInUsername = request.getUserPrincipal().getName();
		return null;
	}
	
}
