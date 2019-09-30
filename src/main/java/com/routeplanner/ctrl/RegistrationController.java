package com.routeplanner.ctrl;
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
	public User saveUser(@RequestBody User user) {
		return registrationService.saveUser(user);
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
	
	 
	@PostMapping("/contact-details/persist")
	ContactDetails postContactDetails(@RequestBody ContactDetails contactDetails) {
		logger.info("posting contact details = " + contactDetails.toString());
		logger.info("contact details id = " + contactDetails.getId());
		ContactDetails cdAfter = registrationService.saveContactDetails(contactDetails);
		return cdAfter;
	}
	
	@PutMapping("/contact-details/{id}")
    ResponseEntity<ContactDetails> updateGroup(@Valid @RequestBody ContactDetails contactDetails) {
        logger.info("Request to update contact details: {}", contactDetails);
        ContactDetails result = registrationService.updateContactDetails(contactDetails);
        return ResponseEntity.ok().body(result);
    }
	
}
