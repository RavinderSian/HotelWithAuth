package com.personal.hotel.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity(name = "discounts")
public class DiscountCode {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "discount_code", unique = true)
	private String code;

	@Column(name = "expired")
	private boolean expired;

	@Max(value = 100)
	@Column(name = "discount_percentage")
	private Double discountPercentage;
	
	@Column(name = "creation_date")
	@CreationTimestamp
	private LocalDateTime creationDate;
	
}
