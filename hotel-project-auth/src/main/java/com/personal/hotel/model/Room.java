package com.personal.hotel.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER) //eager fetch type stops error in controller test failed to lazily initialize a collection of role
	private Set<Guest> guests = new HashSet<>();
	
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Booking booking;
	
	public void addGuest(Guest guest) {
		this.guests.add(guest);
		guest.setRoom(this);
	}
	
	public void removeGuest(Guest guest) {
		this.guests.remove(guest);
		guest.setRoom(null);
	}
	
}
