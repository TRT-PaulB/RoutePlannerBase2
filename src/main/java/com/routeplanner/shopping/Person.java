package com.routeplanner.shopping;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="person")
public class Person extends DataModel {
		
	@OneToOne
	private ContactDetails contactDetails;

	@OneToMany
	private Set<PaymentMethod> paymentInfos;
		
	public Person() {
	
		
	}

	public Person(ContactDetails contactDetails, Set<PaymentMethod> paymentInfoList) {
		this.contactDetails = contactDetails;
		this.paymentInfos = paymentInfoList;
	}

	public ContactDetails getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

	public Set<PaymentMethod> getPaymentInfos() {
		return paymentInfos;
	}

	public void setPaymentInfos(Set<PaymentMethod> paymentInfos) {
		this.paymentInfos = paymentInfos;
	}

	@Override
	public String toString() {
		return "Person [contactDetails=" + contactDetails + ", paymentInfos=" + paymentInfos + ", getContactDetails()="
				+ getContactDetails() + ", getPaymentInfos()=" + getPaymentInfos() + ", getId()=" + getId()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	
}
