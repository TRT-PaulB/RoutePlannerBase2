package com.routeplanner.ctrl;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.routeplanner.shopping.Order;
import com.routeplanner.shopping.PaymentMethod;
import com.routeplanner.shopping.service.OrderService;
import com.routeplanner.shopping.service.PaymentMethodService;

@RestController
@RequestMapping("member")
public class OrderController {
	
	private final static Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private PaymentMethodService paymentMethodService;
	
	
	@PostMapping("/purchase")
	ResponseEntity<Order> placeOrder(@Valid @RequestBody Order order) throws URISyntaxException {
		try {
			Order purchase = orderService.placeOrder(order);
			logger.info("Request to purchase order: {}", purchase);
		    return ResponseEntity.created(new URI("/member/purchase/" + purchase.getId()))
		                .body(purchase);
		} catch(Throwable t) {
			logger.info("Error placing order: " + t.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/users")
	List<PaymentMethod> getPaymentMethods(Integer userId) {
		return paymentMethodService.getAllPaymentMethodsByUser(userId);
	}
		
	@PostMapping("/contact-details/add")
	PaymentMethod postPaymentMethod(@RequestBody PaymentMethod paymentMethod) {
		logger.info("posting payment method = " + paymentMethod.toString());
		paymentMethodService.save(paymentMethod);
		return paymentMethod;
	}  		
		

}
