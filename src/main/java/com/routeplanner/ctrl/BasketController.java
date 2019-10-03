package com.routeplanner.ctrl;
import java.net.URI;
import java.net.URISyntaxException;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.routeplanner.shopping.Basket;
import com.routeplanner.shopping.User;
import com.routeplanner.shopping.service.BasketService;

@RestController
@RequestMapping("basket")
public class BasketController {

	private final static Logger logger = LoggerFactory.getLogger(BasketController.class);
	
	@Autowired
	private BasketService basketService;
	

	@GetMapping("/current/{userId}")
    ResponseEntity<?> getCurrentBasket(@PathVariable Integer userId) throws URISyntaxException {
		try {
			Basket basket = basketService.getCurrentBasket(userId);
			logger.info("basket created = " + basket.getId());
			return ResponseEntity.ok().body(basket);
		} catch(Throwable t) {
			logger.info("no basket available for user with id: " + userId);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
	
	
	@PostMapping("/add")
	ResponseEntity<Basket> saveBasket(@Valid @RequestBody Basket basket) throws URISyntaxException {
	    logger.info("Request to create basket: {}", basket);
	    basketService.saveNewBasket(basket);
	    return ResponseEntity.created(new URI("/route/basket/" + basket.getId()))
	                .body(basket);
	}
	
	
}
