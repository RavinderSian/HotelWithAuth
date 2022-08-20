package com.personal.hotel.controller;

import java.util.Optional;

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
import com.personal.hotel.model.DiscountCode;
import com.personal.hotel.model.Room;
import com.personal.hotel.publishers.RoomBookedEventPublisher;
import com.personal.hotel.services.BookingServices;
import com.personal.hotel.services.DiscountCodeService;
import com.personal.hotel.services.RoomServices;

@PreAuthorize("hasRole('USER')")
@RequestMapping("booking/")
@Controller
public class BookingController {

	private final BookingServices services;
	private final RoomServices roomServices;
	private final UserRepository userRepository;
	private final RoomBookedEventPublisher publisher;
	private final DiscountCodeService discountCodeService;

	public BookingController(BookingServices services, RoomServices roomServices, 
			UserRepository userRepository, RoomBookedEventPublisher publisher, DiscountCodeService discountCodeService) {
		this.services = services;
		this.roomServices = roomServices;
		this.userRepository = userRepository;
		this.publisher = publisher;
		this.discountCodeService = discountCodeService;
	}

	@GetMapping("/{roomId}/book/{discountCodeString}") //get request as values are visible in url
	public String bookRoom(Model model, HttpServletRequest request, @PathVariable Long roomId, @PathVariable String discountCodeString) {
		
		User user = userRepository.findByUsername(request.getUserPrincipal().getName());
		Room room = roomServices.findById(roomId).get();
		
		Optional<DiscountCode> discountCodeOptional = discountCodeService.findByDiscountCode(discountCodeString);
		
		room.setOccupied(true);
		Booking booking = new Booking();
		booking.setRoom(room);
		booking.setUser(user);
		
		if (discountCodeOptional.isPresent()) {
			booking.setCost(room.getPrice() * ((100 - discountCodeOptional.get().getDiscountPercentage())/100));
			discountCodeService.saveAsExpired(discountCodeOptional.get().getId());
		} else {
			booking.setCost(room.getPrice());
		}
		
		services.save(booking);
		
		user.setBooking(booking);
		userRepository.save(user);
		
		roomServices.save(room);
		
		System.out.println(booking.getCost() + "----------------------");
		System.out.println(booking.getCost() + "----------------------");
		
		publisher.publishRoomBookedEvent(1L);
		
		return "redirect:/booking/yourbooking";
	}
	
	@GetMapping("yourbooking")
	public String yourBooking(Model model, HttpServletRequest request) {

		User user = userRepository.findByUsername(request.getUserPrincipal().getName());
		model.addAttribute("username", request.getUserPrincipal().getName());
		model.addAttribute("booking", user.getBooking());
		model.addAttribute("room", user.getBooking().getRoom());
		return "yourbooking";
	}
	
}