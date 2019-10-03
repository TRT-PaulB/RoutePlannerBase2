package com.routeplanner.shopping.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.routeplanner.shopping.Basket;
import com.routeplanner.shopping.ContactDetails;
import com.routeplanner.shopping.Ticket;
import com.routeplanner.shopping.User;
import com.routeplanner.shopping.repository.BasketRepository;


@Transactional(isolation = Isolation.DEFAULT, propagation=Propagation.REQUIRED) 
@Service
public class BasketService {

	private static final Logger logger = LoggerFactory.getLogger(BasketService.class);
	
	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private TicketService ticketService;
	
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
	

	public void saveNewBasket(Basket basket) {
		if (basket.getTickets() != null && basket.getTickets().size() > 0) {
			List<Ticket> addedTickets = ticketService.saveAll(basket.getTickets());
			basket.setTickets(addedTickets.stream().collect(Collectors.toSet()));
		}

		basketRepository.save(basket);
		logger.debug("Basket saved with id: " + basket.getId());
	}
	
	
}

