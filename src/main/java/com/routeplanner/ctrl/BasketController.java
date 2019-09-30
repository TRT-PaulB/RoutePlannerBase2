package com.routeplanner.ctrl;
import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.routeplanner.shopping.Basket;
import com.routeplanner.shopping.service.BasketService;

@RestController
@RequestMapping("basket")
public class BasketController {

//	@Autowired
//	private BasketService basketService;
	
	private final static Logger logger = LoggerFactory.getLogger(BasketController.class);
	
	@GetMapping("/basket/{username}")
    ResponseEntity<?> getCurrentBasket(@PathVariable String username) {
//        Optional<Group> group = groupRepository.findById(id);
//        return group.map(response -> ResponseEntity.ok().body(response))
//                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		
		
		// IN SERVICE:
		// 1) get an open basket for the user
		
		// 2) if no such basket exists, create and persist one
		
		// 3) return the basket
		
		
		return null;
    }

	
	
	@PostMapping("/add")
	ResponseEntity<Basket> saveBasket(@Valid @RequestBody Basket basket) throws URISyntaxException {
	    logger.info("Request to create basket: {}", basket);
	    // basketService.save(basket);
	    return ResponseEntity.created(new URI("/route/basket/" + basket.getId()))
	                .body(basket);
	}
	
	
}
