package com.routeplanner.config;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import com.routeplanner.shopping.service.MyUserDetailsService;


//@EnableWebSecurity
public class SecurityConfiguration { // extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	MyUserDetailsService userDetailsService;
	
	
	// AUTHENTICATION
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService);
//	}		

	
	// AUTHORIZATION
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//		
////		.antMatchers("/admin*").hasRole("ADMIN")  // catch all admin urls (includes admin etwas)
////		.antMatchers("/user").hasAnyRole("ADMIN", "USER")
//		
//		.antMatchers("/home", "/errors", "/route/*", "/stations", "/member/*", "/").permitAll()
//		.and().formLogin();
//	}
//	
//	// TODO  change 'user' to 'member' level, then move member above general security
//	
//	
//	// PASSWORD ENCODER
//	@Bean  // test only
//	public PasswordEncoder getPasswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
	
	
	
}

