package com.routeplanner.shopping;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order extends DataModel {
	
	@OneToOne
	private PaymentMethod paymentMethod;
	
	@Column(name="transaction")
	private LocalDate transactionDate;
	
	@OneToOne
	private ContactDetails contactDetails;
	
	@OneToOne
	private Basket basket;
	
	private boolean purchased;
	
	
	public Order() {
		super();
	}


	public Order(PaymentMethod paymentMethod, LocalDate transactionDate, ContactDetails contactDetails, Basket basket) {
		super();
		this.paymentMethod = paymentMethod;
		this.transactionDate = transactionDate;
		this.contactDetails = contactDetails;
		this.basket = basket;
	}

	
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}


	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}


	public LocalDate getTransactionDate() {
		return transactionDate;
	}


	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}


	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}
	
	public ContactDetails getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

	@Override
	public String toString() {
		return "Order [paymentMethod=" + paymentMethod + ", transactionDate=" + transactionDate + ", contactDetails="
				+ contactDetails + ", basket=" + basket + "]";
	}
	
}
