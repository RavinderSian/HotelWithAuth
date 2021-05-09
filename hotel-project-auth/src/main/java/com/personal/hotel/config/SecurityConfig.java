package com.personal.hotel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.personal.hotel.auth.DatabaseUserDetailsService;
import com.personal.hotel.auth.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception { //this method allows you to set the domain specific language which can configure the servlet filters
		http
		.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/admin").hasAuthority("ROLE_ADMIN")
		.antMatchers("/non-admin").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
		.anyRequest().authenticated()
		.and()
		.httpBasic();
		
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new DatabaseUserDetailsService(userRepository);
	}
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
