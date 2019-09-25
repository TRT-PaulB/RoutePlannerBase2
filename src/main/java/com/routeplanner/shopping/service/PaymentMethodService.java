package com.routeplanner.shopping.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.routeplanner.shopping.ContactDetails;
import com.routeplanner.shopping.PaymentMethod;
import com.routeplanner.shopping.repository.ContactDetailsRepository;
import com.routeplanner.shopping.repository.PaymentMethodRepository;


@Transactional(isolation = Isolation.DEFAULT, propagation=Propagation.REQUIRED)
@Service
public class PaymentMethodService {

	private static final Logger logger = LoggerFactory.getLogger(PaymentMethodService.class);
	
	@Autowired
	private PaymentMethodRepository paymentInfoRepository;
	
	@Autowired
	private ContactDetailsRepository contactDetailsRepository;
	
	public PaymentMethodService() {
		
	}
	
	public void save(PaymentMethod paymentInfo) {
		paymentInfoRepository.save(paymentInfo);
		logger.debug("Payment info saved with id: " + paymentInfo.getId());
	}
	
	public void saveContactDetails(ContactDetails contactDetails) {
		contactDetailsRepository.save(contactDetails);
		logger.debug("Contact details info saved with id: " + contactDetails.getId());
	}
	
}

