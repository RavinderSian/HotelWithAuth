package com.personal.hotel.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity(name = "employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "please enter a first name")
	@Column(name = "first_name")
	private String firstName;
	
	@NotEmpty(message = "please enter a last name")
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "username", unique = true)
	private String username;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Booking> bookings = new ArrayList<>();
	
}
