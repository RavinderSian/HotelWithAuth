package com.personal.hotel.services;

import com.personal.hotel.model.DiscountCode;

public interface DiscountCodeService {
	
	DiscountCode generateDiscountCode(int percentage);

}
