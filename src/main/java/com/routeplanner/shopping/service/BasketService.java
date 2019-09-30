package com.routeplanner.shopping.service;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.routeplanner.shopping.Basket;
import com.routeplanner.shopping.ContactDetails;
import com.routeplanner.shopping.User;
import com.routeplanner.shopping.repository.BasketRepository;


@Transactional(isolation = Isolation.DEFAULT, propagation=Propagation.REQUIRED) 
@Service
public class BasketService {

	private static final Logger logger = LoggerFactory.getLogger(BasketService.class);
	
	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private BasketRepository<Basket> basketRepository;
	
	public BasketService() {
		
	}
	
	
	public Basket getCurrentBasket(Integer userId) throws Throwable {
		
		Optional<Basket> basket = basketRepository.findOpenBasketByUserId(userId);
		
		if (!basket.isPresent()) {
			logger.info("no open basket is present - so create a contact details record and basket for this user");
			Optional<User> user = registrationService.findUserById(userId);
			
			Optional<ContactDetails> contactDetails = registrationService.findContactDetailsByUserId(userId);
			
			if (contactDetails.isPresent()) {
				Basket newBasket = new Basket(contactDetails.get());
				newBasket = basketRepository.save(newBasket);
				logger.info("created a new basket:  = " + newBasket.toString());
				return newBasket;
			} else {
				logger.info("problem creating new basket - no contact details available for this user");
				throw new Throwable("user is invalid so basket could not be identified");
			}
		}
		
		return basket.get();
	}
	
	
	
	
	
	
	// TICKETS....
//	public void saveTickets(Set<Ticket> tickets) {
//		if (tickets != null && tickets.size() > 0) {
//			logger.debug("Persisting a batch of " + tickets.size() + " tickets");
//			basketRepository.saveAll((ArrayList)tickets.stream().collect(Collectors.toList()));
//		}
//	}
	
	
//public void save(Basket basket) {
//		
//		if (basket.getTickets() != null) {
//			saveTickets(basket.getTickets());
//		}
//		
//		basketRepository.save(basket);
//		logger.debug("Basket saved with id: " + basket.getId());
//	}
	
	
}

