package com.personal.hotel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.personal.hotel.communication.SendEmail;
import com.personal.hotel.model.DiscountCode;
import com.personal.hotel.services.DiscountCodeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/slotmachine")
@PreAuthorize("hasRole('USER')")
public class SlotMachineController {
	
	@Autowired
	private DiscountCodeService discountCodeService;
	

	@Autowired
	private SendEmail emailSender;
	
	@GetMapping
	public String slotMachine() {
		
		return "slotmachine.html";
	}
	
	@PostMapping("/winner")
	public String slotMachineWinner(HttpServletRequest request) {
		
		DiscountCode discountCode = discountCodeService.generateDiscountCode(40);
		
		String loggedInUser =  request.getUserPrincipal().getName();
		
		log.info("User has won: " + loggedInUser);
		
		return ":/";
	}
	
}
