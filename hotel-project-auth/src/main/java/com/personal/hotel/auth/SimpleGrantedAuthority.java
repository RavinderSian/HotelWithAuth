package com.personal.hotel.auth;

import org.springframework.security.core.GrantedAuthority;

public class SimpleGrantedAuthority implements GrantedAuthority {

	private final String authority;
	
	public SimpleGrantedAuthority(String role) {
		this.authority = role;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

}
