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

import com.routeplanner.shopping.PaymentMethod;
import com.routeplanner.shopping.repository.PaymentMethodRepository;


@Transactional(isolation = Isolation.DEFAULT, propagation=Propagation.REQUIRED)
@Service
public class PaymentMethodService {

	private static final Logger logger = LoggerFactory.getLogger(PaymentMethodService.class);
	
	@Autowired
	private PaymentMethodRepository paymentInfoRepository;
	
	
	public PaymentMethodService() {
		
	}
	
	public Optional<List<PaymentMethod>> getAllPaymentMethodsByUser(Integer userId) {
		Optional<List<PaymentMethod>> optPayMeths = paymentInfoRepository.findPaymentMethodForUser(userId);
		return optPayMeths;
	}
	
	public PaymentMethod save(PaymentMethod paymentInfo) {
		PaymentMethod dbPayMethod = paymentInfoRepository.save(paymentInfo);
		logger.debug("Payment info saved with id: " + dbPayMethod.getId());
		return dbPayMethod;
	}
	
}


