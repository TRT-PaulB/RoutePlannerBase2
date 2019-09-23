package com.routeplanner.shopping;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="registration_details")
public class RegistrationDetails extends ContactDetails {

	@NotNull
	@OneToOne
	private OldUser user;
	
	public RegistrationDetails() {
		super();
		this.user = new OldUser();
	}
	
	public OldUser getUser() {
		return user;
	}

	public void setUser(OldUser user) {
		this.user = user;
	}
	
}
