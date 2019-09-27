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
	
	
	
	public Basket getCurrentBasket(String username) throws Throwable {
		
		Optional<Basket> basket = basketRepository.findOpenBasketForUser(username);
		
		if (!basket.isPresent()) {
			// create a new basket
			Optional<User> user = registrationService.findUser(username);
			
			if (user.isPresent()) {
				ContactDetails userContact = new ContactDetails(user.get());
				userContact = registrationService.saveContactDetails(userContact);
				Basket newBasket = new Basket(userContact);
				return basketRepository.save(newBasket);
			} else {
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

