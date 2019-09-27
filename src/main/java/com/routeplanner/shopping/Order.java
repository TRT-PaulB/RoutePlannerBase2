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
	private Basket basket;
	
	private boolean purchased;
	
	
	public Order() {
		super();
	}


	public Order(PaymentMethod paymentMethod, LocalDate transactionDate, Basket basket) {
		super();
		this.paymentMethod = paymentMethod;
		this.transactionDate = transactionDate;
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


	@Override
	public String toString() {
		return "Order [paymentMethod=" + paymentMethod + ", transactionDate=" + transactionDate + ", basket=" + basket
				+ ", purchased=" + purchased + "]";
	}
	
}
