package com.personal.hotel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.personal.hotel.auth.User;
import com.personal.hotel.auth.UserRepository;
import com.personal.hotel.model.Booking;
import com.personal.hotel.model.Room;
import com.personal.hotel.services.BookingServices;
import com.personal.hotel.services.RoomServices;

@PreAuthorize("hasRole('USER')")
@RequestMapping("booking/")
@Controller
public class BookingController {

	private final BookingServices services;
	private final RoomServices roomServices;
	private final UserRepository userRepository;

	public BookingController(BookingServices services, RoomServices roomServices, UserRepository userRepository) {
		this.services = services;
		this.roomServices = roomServices;
		this.userRepository = userRepository;
	}

	@GetMapping("/{roomId}/book") //get request as values are visible in url
	public String bookRoom(Model model, HttpServletRequest request, @PathVariable Long roomId) {
		
		User user = userRepository.findByUsername(request.getUserPrincipal().getName());
		
		System.out.println(request.getUserPrincipal().getName() + "----------------------");
		
		System.out.println(user.toString());
		
		Room room = roomServices.findById(roomId).get();

		
		room.addGuest(user.getGuest());
		room.setOccupied(true);
		Booking booking = new Booking();
		booking.addRoom(room);
		
		roomServices.save(room);
		System.out.println(room.toString());
		services.save(booking);
		
		return "redirect:/booking/yourbooking";
	}
	
	@RequestMapping("yourbooking")
	public String yourBookings(Model model, HttpServletRequest request) {
		
		User user = userRepository.findByUsername(request.getUserPrincipal().getName());

		model.addAttribute("username", request.getUserPrincipal().getName());
		model.addAttribute("booking", user.getGuest().getRoom().getBooking());
		model.addAttribute("room", user.getGuest().getRoom());
		return "yourbooking";
	}
	
}