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

import com.routeplanner.shopping.Basket;
import com.routeplanner.shopping.Order;
import com.routeplanner.shopping.PaymentMethod;
import com.routeplanner.shopping.service.OrderService;

@RestController
@RequestMapping("member")
public class OrderController {
	
	private final static Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
		@PostMapping("/purchase")
	ResponseEntity<Order> placeOrder(@Valid @RequestBody Order order) throws URISyntaxException {
		Order purchase = orderService.placeOrder(order);
		logger.info("Request to purchase order: {}", purchase);
	    return ResponseEntity.created(new URI("/member/purchase/" + purchase.getId()))
	                .body(purchase);
	}

}
