package com.routeplanner.shopping.service;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.routeplanner.shopping.Basket;
import com.routeplanner.shopping.Order;
import com.routeplanner.shopping.repository.OrderRepository;
import com.routeplanner.shopping.repository.PaymentMethodRepository;

@Transactional(isolation = Isolation.DEFAULT, propagation=Propagation.REQUIRED)
@Service
public class OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PaymentMethodRepository paymentMethodRepository;
	
	public OrderService() {
		
	}
	
	public Order placeOrder(Order order) throws Throwable {
		if (order == null || order.getPaymentMethod() == null) {
			throw new Throwable("Order failed - incomplete information");
		}

		if (order.getPaymentMethod().getId() == null) {
			paymentMethodRepository.save(order.getPaymentMethod());
		}
		
		Basket basket = order.getBasket();
		basket.setOpen(false);
		
		order.setTransactionDate(LocalDate.now());
		
		Order purchase = orderRepository.save(order);
		logger.debug("Order saved with id: " + purchase.getId());
		return purchase;
	}

	
	public List<Order> getOrdersForUser(String username) {
		return orderRepository.findPurchasesForUser(username);
	}

}
