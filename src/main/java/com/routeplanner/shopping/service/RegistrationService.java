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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.routeplanner.shopping.ContactDetails;
import com.routeplanner.shopping.User;
import com.routeplanner.shopping.repository.ContactDetailsRespository;
import com.routeplanner.shopping.repository.UserRepository;

@Transactional(isolation = Isolation.DEFAULT, propagation=Propagation.REQUIRED) 
@Service
public class RegistrationService {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ContactDetailsRespository contractDetailsRespository;
	
	
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
	
//	public ContactDetails saveContactDetailsOLD(ContactDetails contactDetails) {
//		logger.info("getting contact details = " + contactDetails.toString());
//		if (contactDetails.getId() == null) {
//			return contractDetailsRespository.save(contactDetails);
//		} else {
//			contractDetailsRespository.setUserInfoById(contactDetails.getFullname(), contactDetails.getUser().getId(), contactDetails.getId(),
//					contactDetails.getTitle(), contactDetails.getAddressLine1(), contactDetails.getAddressLine2(), contactDetails.getAddressLine3(),
//					contactDetails.getCity(), contactDetails.getRegion(), contactDetails.getCountry(), contactDetails.getEmail(), contactDetails.getMobileTel(),
//					contactDetails.getHomeTel(), contactDetails.getOfficeTel());
//			return contactDetails;
//		}
//	}
	
	public ContactDetails saveContactDetails(ContactDetails contactDetails) {
		logger.info("getting contact details = " + contactDetails.toString());
		return contractDetailsRespository.save(contactDetails);
	}
	
	
	public ContactDetails updateContactDetails(ContactDetails contactDetails) {

		contractDetailsRespository.setUserInfoById(contactDetails.getFullname(), contactDetails.getUser().getId(), contactDetails.getId(),
				contactDetails.getTitle(), contactDetails.getAddressLine1(), contactDetails.getAddressLine2(), contactDetails.getAddressLine3(),
				contactDetails.getCity(), contactDetails.getRegion(), contactDetails.getCountry(), contactDetails.getEmail(), contactDetails.getMobileTel(),
				contactDetails.getHomeTel(), contactDetails.getOfficeTel());
		return contactDetails;
	}
	
	
}
