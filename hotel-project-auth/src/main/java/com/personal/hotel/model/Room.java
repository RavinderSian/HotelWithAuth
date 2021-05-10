package com.personal.hotel.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity(name = "rooms")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "room_number")
	private Integer roomNumber;
	
	@Column(name = "capacity")
	private Integer capacity;
	
	@OneToMany
	private Set<Guest> guests = new HashSet<>();
	
}
