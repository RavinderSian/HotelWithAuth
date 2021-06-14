package com.personal.hotel.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.personal.hotel.auth.User;

import lombok.Data;

@Data
@Entity(name = "bookings")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "cost")
	private Double cost;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Room room;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private User user;
	
	@CreationTimestamp
	LocalDate bookingDate;
	
	@UpdateTimestamp
	LocalDate lastUpdated;
	
}
