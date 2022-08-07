package com.personal.hotel.services;

import java.util.Optional;

import com.personal.hotel.model.DiscountCode;

public interface DiscountCodeService {
	
	DiscountCode generateDiscountCode(int percentage);

	Optional<DiscountCode> verifyDiscountCode(String discountCode);
}
