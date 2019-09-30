package com.routeplanner.ctrl;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.routeplanner.shopping.ContactDetails;
import com.routeplanner.shopping.User;
import com.routeplanner.shopping.service.RegistrationService;

@RestController
@RequestMapping("reg")
public class RegistrationController {
	
	@Autowired
	private RegistrationService registrationService;
	
	private final static Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
	@PostMapping("/user/add")
	public ResponseEntity<User> saveUser(@RequestBody User user) throws URISyntaxException {
		logger.info("Request to create user: {}", user);
		User result = registrationService.saveUser(user);
		return ResponseEntity.created(new URI("/reg/user/add" + result.getId()))
                .body(result);
	}
	
	@GetMapping("/users")
	public List<User> getUsers() {
		return registrationService.getUsers();
	}
	
	@GetMapping("/user/{username}")
	public ResponseEntity<User> getUser(@PathVariable String username) {
		Optional<User> user = registrationService.findUser(username);
		return user.map(response -> ResponseEntity.ok().body(response)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/contact/{userId}")
	public ResponseEntity<ContactDetails> getUser(@PathVariable Integer userId) {
		Optional<ContactDetails> contactDetails = registrationService.findContactDetailsByUserId(userId);
		return contactDetails.map(response -> ResponseEntity.ok().body(response)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/user/id/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Integer id) {
		Optional<User> user = registrationService.findUserById(id);
		return user.map(response -> ResponseEntity.ok().body(response)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PutMapping("/contact-details/{id}")
    ResponseEntity<ContactDetails> updateContactDetails(@Valid @RequestBody ContactDetails contactDetails) {
        logger.info("Request to update contact details: {}", contactDetails);
        ContactDetails result = registrationService.updateContactDetails(contactDetails);
        return ResponseEntity.ok().body(result);
    }
	
	@PostMapping("/contact-details/persist")
	ResponseEntity<ContactDetails> saveContactDetails(@RequestBody ContactDetails contactDetails) throws URISyntaxException {
		logger.info("posting contact details = " + contactDetails.toString());
		ContactDetails result = registrationService.saveContactDetails(contactDetails);
		return ResponseEntity.created(new URI("/reg/user/add" + result.getId()))
                .body(result);
	}
		
}
