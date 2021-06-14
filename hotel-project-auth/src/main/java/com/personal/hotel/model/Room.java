package com.personal.hotel.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.ToString;

@Data
@Entity(name = "rooms")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "room_number", unique = true)
	private Integer roomNumber;
	
	@Column(name = "capacity")
	private Integer capacity;
	
	@Column(name = "occupied")
	private boolean occupied;
	
	@Column(name = "price")
	private Double price;
	
	@ToString.Exclude
	@OneToOne(cascade = CascadeType.PERSIST)
	private Booking booking;
	
}
