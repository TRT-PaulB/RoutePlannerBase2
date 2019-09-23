package com.routeplanner.shopping.service;
import java.util.Optional;
import com.routeplanner.shopping.repository.*;
import com.routeplanner.shopping.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	
	@Override	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// hard coded way of doing things, without any checks on the username, just the pass from the database
		// obviously the database is create and a user table with 1 row containing the pass (see readMeNotes)
		// return new MyUserDetails(username); 
		
		// get data via JPA API, looks up user from the database given the username
		Optional<User> user = userRepository.findByUserName(username);
		
		user.orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
		
		// see below for nicer way of doing this with method reference
		//return new MyUserDetails(user.get());
		return user.map(MyUserDetails::new).get();
	}	
	
}
