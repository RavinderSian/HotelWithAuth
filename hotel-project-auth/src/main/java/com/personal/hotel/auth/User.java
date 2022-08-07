package com.personal.hotel.auth;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.CreditCardNumber;

import com.personal.hotel.model.Booking;

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
	
	@Getter
	@Setter
	@NotEmpty(message = "please enter a first name")
	@Column(name = "first_name")
	private String firstName;
	
	@Getter
	@Setter
	@NotEmpty(message = "please enter a last name")
	@Column(name = "last_name")
	private String lastName;
	
	@Getter
	@Setter
	@CreditCardNumber(message = "please enter a valid card number")
	@Column(name = "card_number")
	private String cardNumber;
	
	@Getter
	@Setter
	@NotEmpty(message = "please enter an email")
	@Email(message = "please enter a valid email")
	@Column(name = "email")
	private String email;
	
	@Getter
	@Setter
	@OneToOne(cascade = CascadeType.PERSIST)
	private Booking booking;

}
