package com.personal.hotel.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.CreditCardNumber;

import com.personal.hotel.auth.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Entity(name = "guests")
public class Guest {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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
	@Column(name = "bank_card")
	private String cardNumber;
	
	@Getter
	@Setter
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Room room;
	
	@Getter
	@Setter
	@ToString.Exclude
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	
}
