package com.personal.hotel.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import com.personal.hotel.model.Guest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Entity(name = "user")
public class User {
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "please enter a username")
	@Getter
	@Setter
	@Column(name = "user_name", unique = true)
	private String username;
	
	@NotEmpty(message = "please enter a password")
	@ToString.Exclude
	@Getter
	@Setter
	@Column(name = "password")
	private String password;
	
	@Getter
	@Setter
	@Column(name = "authority")
	private String authority;

	@ToString.Exclude
	@Getter
	@Setter
	@OneToOne
	private Guest guest;
	
}
