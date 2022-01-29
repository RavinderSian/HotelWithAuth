package com.personal.hotel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import com.personal.hotel.auth.DatabaseUserDetailsService;
import com.personal.hotel.auth.HotelAuthenticationFailureHandler;
import com.personal.hotel.auth.UserRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //allows us to use @preAuthorize
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception { //this method allows you to set the domain specific language which can configure the servlet filters
		http
		.csrf().disable()
		.formLogin() //no custom page so default is /login
		.loginProcessingUrl("/performlogin") //url to submit the username and pw to
		.defaultSuccessUrl("/") //landing page after login
		.failureUrl("/failedlogin") //for when login fails
		.and()
		.logout()
		.logoutUrl("/logout") //url for default logout page
		.logoutSuccessUrl("/") //url for successful logout 
		.deleteCookies("JSESSIONID") 
		.and()
		.httpBasic();
		
		http.headers().frameOptions().disable(); //stops h2 database having an error
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new DatabaseUserDetailsService(userRepository);
	}
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    HotelAuthenticationFailureHandler authenticationFailureHandler(){
        return new HotelAuthenticationFailureHandler();
    }
	
    @Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }
	
}
