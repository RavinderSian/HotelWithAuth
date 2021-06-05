package com.personal.hotel.controller;

import javax.validation.Valid;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.personal.hotel.auth.User;
import com.personal.hotel.auth.UserRepository;
import com.personal.hotel.model.Guest;
import com.personal.hotel.services.GuestServices;

@Controller
@RequestMapping("guests/")
public class GuestController {
	
	private final GuestServices guestServices;
	private final UserRepository userRepository;
	
	public GuestController(GuestServices guestServices, UserRepository userRepository) {
		this.guestServices = guestServices;
		this.userRepository = userRepository;
	}

	@GetMapping("newguest")
	public String register(Model model) {
		model.addAttribute("guest", new Guest());
		return "register";
	}

	@PostMapping("newguest")
	public String addGuest(@Valid @ModelAttribute Guest guest, BindingResult bindingResult, Model model){
		
		if (bindingResult.hasErrors()) {
			return "register";
		}
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		User user = new User();
		user.setAuthority("USER");
		user.setUsername(guest.getUser().getUsername());
		user.setPassword(encoder.encode(guest.getUser().getPassword()));
		guest.setUser(user);
		guestServices.save(guest);
		
		user.setGuest(guest);
		userRepository.save(user);

		return "redirect:/";
	}
}
