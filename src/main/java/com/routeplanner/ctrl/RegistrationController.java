package com.routeplanner.ctrl;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.routeplanner.shopping.ContactDetails;
import com.routeplanner.shopping.User;
import com.routeplanner.shopping.repository.ContactDetailsRespository;
import com.routeplanner.shopping.service.RegistrationService;

@RestController
@RequestMapping("route")
public class RegistrationController {
	
	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	RegistrationService regService;
	
	@Autowired
	ContactDetailsRespository contractDetailsRespository;
	
	
	private final static Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
	@PostMapping("/user")
	public User saveUser(@RequestBody User user) {
		return registrationService.saveUser(user);
	}
	
	@GetMapping("/users")
	public List<User> getUsers() {
		return registrationService.getUsers();
	}
	
	@GetMapping("/user/{username}")
	public ResponseEntity<User> getUser(@PathVariable String username) {
		Optional<User> user = regService.findUser(username);
		return user.map(response -> ResponseEntity.ok().body(response)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/contact-details/add")
	ContactDetails postContactDetails(@RequestBody ContactDetails contactDetails) {
		logger.info("getting contact details = " + contactDetails.toString());
		ContactDetails cdAfter = regService.saveContactDetails(contactDetails);
		return cdAfter;
	}
	
	
}
