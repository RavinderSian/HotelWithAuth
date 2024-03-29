package com.personal.hotel.services;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.hotel.model.DiscountCode;
import com.personal.hotel.repository.DiscountCodeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DiscountCodeServiceImpl implements DiscountCodeService {
	
	@Autowired
	private DiscountCodeRepository repository;
	
	public DiscountCodeServiceImpl(DiscountCodeRepository repository) {
		this.repository = repository;
	}

	private final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	
	@Override
	public DiscountCode generateDiscountCode(int percentage) {
		
		DiscountCode discountCode = new DiscountCode();
		discountCode.setExpired(false);
		discountCode.setDiscountPercentage(Double.valueOf(percentage));
		
        Random random = new Random();
        
        StringBuilder stringBuilder = new StringBuilder(8);

	    while (stringBuilder.length()<8) {
		    char randomChar = CHARACTERS.charAt(random.nextInt(CHARACTERS.length()));
		    stringBuilder.append(randomChar);
	    }
	    
	    discountCode.setCode(stringBuilder.toString());
	    
	    log.info("discount code created");
	    repository.save(discountCode);
	    
		return discountCode;
	}

	@Override
	public Optional<DiscountCode> verifyDiscountCode(String discountCode) {
		
		Optional<DiscountCode> result = repository.findByCode(discountCode);
		
		if (result.isPresent()) {
			return result.get().isExpired() ? Optional.empty() : result;
		}
		
		return result;
		
	}
	
	@Override
	public Optional<DiscountCode> findByDiscountCode(String discountCode) {
		
		Optional<DiscountCode> result = repository.findByCode(discountCode);
		
		return result.isPresent() ? result : Optional.empty();
		
	}
	
	@Override
	public void saveAsExpired(Long id) {
		
		Optional<DiscountCode> discountCodeOptional = repository.findById(id);
		
		discountCodeOptional.get().setExpired(true);
		
		repository.save(discountCodeOptional.get());
	}
	
}
