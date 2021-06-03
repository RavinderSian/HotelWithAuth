package com.personal.hotel.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.personal.hotel.model.Guest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Entity(name = "user")
public class User {
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter
	@Setter
	@Column(name = "user_name", unique = true)
	private String username;
	
	@Getter
	@Setter
	@Column(name = "password")
	private String password;
	
	@Getter
	@Setter
	@Column(name = "authority")
	private String authority;

	@Getter
	@Setter
	@OneToOne
	private Guest guest;
	
}
