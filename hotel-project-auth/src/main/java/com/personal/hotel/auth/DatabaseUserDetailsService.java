package com.personal.hotel.auth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class DatabaseUserDetailsService implements UserDetailsService {
	
	private final UserRepository repository;
	
	public DatabaseUserDetailsService(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("Failed login");
		}
		
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>(Arrays.asList(new SimpleGrantedAuthority(user.getAuthority())));
        if (user.getAuthority() == "ADMIN") {
            grantedAuthorities.add(new SimpleGrantedAuthority("EMPLOYEE"));
        }
		return new UserPrincipal(user, grantedAuthorities);
	}

}
