package com.personal.hotel.auth;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.personal.hotel.model.Guest;

import lombok.Data;

@Data
@Entity(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_name", unique = true)
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "authority")
	private String authority;

	@OneToOne(cascade = CascadeType.ALL)
	private Guest guest;
	
}
