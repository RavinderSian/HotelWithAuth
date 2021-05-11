package com.personal.hotel.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity(name = "bookings")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "cost")
	private Double cost;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	List<Room> rooms = new ArrayList<>();
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Employee employee;
}
