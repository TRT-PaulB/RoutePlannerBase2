package com.routeplanner.shopping.service;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.routeplanner.shopping.User;
import com.routeplanner.shopping.repository.UserRepository;

@Transactional(isolation = Isolation.DEFAULT, propagation=Propagation.REQUIRED) 
@Service
public class RegistrationService {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public Optional<User> findUser(String username) {
		Optional<User> user = userRepository.findByUserName(username);
		return user;
	}
	
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	public User saveUser(User user) {
        User userOut = userRepository.save(user);
        return userOut;
	}
	
}
