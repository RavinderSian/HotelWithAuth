package com.personal.hotel.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.personal.hotel.model.DiscountCode;

public interface DiscountCodeRepository extends CrudRepository<DiscountCode, Long>{

	Optional<DiscountCode> findByCode(String code);
	
}
