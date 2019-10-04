package com.routeplanner.ctrl;
import java.net.URI;
import java.net.URISyntaxException;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.routeplanner.shopping.ContactDetails;
import com.routeplanner.shopping.Order;
import com.routeplanner.shopping.PaymentMethod;
import com.routeplanner.shopping.service.OrderService;
import com.routeplanner.shopping.service.PaymentMethodService;

@RestController
@RequestMapping("order")
public class OrderController {
	
	private final static Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private PaymentMethodService paymentMethodService;
	

	@PostMapping("/member/purchase")
	ResponseEntity<Order> placeOrder(@RequestBody Order order) throws URISyntaxException {
		try {
			Order purchase = orderService.placeOrder(order);
			logger.info("Request to purchase order: {}", purchase);
		    return ResponseEntity.created(new URI("/member/purchase/" + purchase.getId()))
		                .body(purchase);
		} catch(Throwable t) {
			Integer basketId = order.getBasket() != null ? order.getBasket().getId() : null; 
			logger.info("Error placing order for basket: " + basketId + ". Error:\n " + t.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

		
	@GetMapping("/member/{userId}")
	public List<Order> getOrders(@PathVariable Integer userId) {
		return orderService.getOrdersForUser(userId);
	}

	@GetMapping("/member/pm/{userId}")
	ResponseEntity<List<PaymentMethod>> getPaymentMethods(@PathVariable Integer userId) {
		 logger.info("Searching payment methods for user: " + userId);
		 Optional<List<PaymentMethod>> optPayMeths = paymentMethodService.getAllPaymentMethodsByUser(userId);
		 return optPayMeths.map(response -> ResponseEntity.ok().body(response)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
		
	@PostMapping("/member/pm/add")
	ResponseEntity<PaymentMethod> postPaymentMethod(@RequestBody PaymentMethod paymentMethod) throws URISyntaxException {
		logger.info("posting payment method: " + paymentMethod.toString());
		PaymentMethod result = paymentMethodService.save(paymentMethod);
		return ResponseEntity.created(new URI("/order/member/pm/add" + result.getId()))
	            .body(result);
	}  		

}

