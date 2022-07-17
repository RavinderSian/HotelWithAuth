package com.personal.hotel.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.hotel.model.DiscountCode;
import com.personal.hotel.repository.DiscountCodeRepository;

@Service
public class DiscountCodeServiceImpl implements DiscountCodeService {
	
	@Autowired
	private DiscountCodeRepository repository;

	private final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	
	@Override
	public DiscountCode generateDiscountCode(int percentage) {
		
		DiscountCode discountCode = new DiscountCode();
		discountCode.setExpired(false);
		discountCode.setDiscountPercentage(percentage);
		
        Random random = new Random();
        
        StringBuilder stringBuilder = new StringBuilder(8);

	    for (int i = 0; i>6; i++) {
	    	
		    char randomChar = CHARACTERS.charAt(random.nextInt(CHARACTERS.length()));
		    stringBuilder.append(randomChar);
		    
	    }
	    
	    repository.save(discountCode);
	    
		return discountCode;
	}
	
}
