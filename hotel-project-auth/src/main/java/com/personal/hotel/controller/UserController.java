package com.personal.hotel.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.personal.hotel.auth.User;
import com.personal.hotel.auth.UserRepository;

@Controller
@RequestMapping("user/")
public class UserController {
	
	private final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("newuser")
	public String registerUser(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("newuser")
	public String registerUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model, 
			HttpServletResponse httpServletResponse){
		
		if (bindingResult.hasErrors()) {
			httpServletResponse.setStatus(400);
			return "register";
		}
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setAuthority("USER");
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);

		return "redirect:/";
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("changepassword")
	public String changePassword(Model model) {
		model.addAttribute("password", "");
		return "changepassword.html";
	}
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("changepassword")
	public String changePasswordPost(Model model, @RequestParam String password,
			HttpServletRequest request, HttpServletResponse response) {
		
		if (password.length()<2) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "changepasswordempty.html";
		}
		
		User currentUser = userRepository.findByUsername(request.getUserPrincipal().getName());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		currentUser.setPassword(encoder.encode(password));
		userRepository.save(currentUser);
		
		return "redirect:/";
	}

}
