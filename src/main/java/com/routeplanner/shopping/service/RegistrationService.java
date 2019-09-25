package com.routeplanner.shopping.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.routeplanner.shopping.ContactDetails;
import com.routeplanner.shopping.repository.ContactDetailsRepository;

@Transactional(isolation = Isolation.DEFAULT, propagation=Propagation.REQUIRED) 
@Service
public class RegistrationService {

private static final Logger logger = LoggerFactory.getLogger(RegistrationService.class);
	
	@Autowired
	private ContactDetailsRepository contactDetailsRepository;
	
	
	public void save(ContactDetails contactDetails) {
		contactDetailsRepository.save(contactDetails);
	}
	
	
}
