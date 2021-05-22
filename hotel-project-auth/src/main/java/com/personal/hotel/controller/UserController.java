package com.personal.hotel.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.personal.hotel.auth.User;
import com.personal.hotel.auth.UserRepository;



@Controller
@RequestMapping("user/")
public class UserController {

	private final UserRepository userRepository;
	
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping("add")
	public String addUser(Model model) {
		model.addAttribute("user", new User());
		return "guest_user_registration";
	}
	
	@PostMapping("add")
	public String addUserPost(@ModelAttribute User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		user.setAuthority("USER");
		userRepository.save(user);
		return "redirect:/";
	}
	
}
