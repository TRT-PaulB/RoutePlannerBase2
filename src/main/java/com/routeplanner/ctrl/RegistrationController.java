package com.routeplanner.ctrl;
import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.routeplanner.shopping.ContactDetails;
import com.routeplanner.shopping.service.RegistrationService;

@RestController
@RequestMapping("route")
public class RegistrationController {
	
	@Autowired
	RegistrationService registrationService;
	
	private final static Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
	@PostMapping("/contact")
	ResponseEntity<ContactDetails> saveContactDetails(@Valid @RequestBody ContactDetails contactDetails) throws URISyntaxException {
	    logger.info("Request to create contact details: {}", contactDetails);
        registrationService.save(contactDetails);
	    return ResponseEntity.created(new URI("/route/contact/" + contactDetails.getId()))
	                .body(contactDetails);
	}
	
	
	
	
	
	
	
	
}
