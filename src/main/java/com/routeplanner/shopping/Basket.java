package com.routeplanner.shopping;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="basket")
public class Basket extends DataModel {

	@OneToMany
	private Set<Ticket> tickets = new HashSet<Ticket>();
	
	@OneToOne
	//@JoinColumn(name = "contact_details_id")
	private User user;	
	
	private boolean open;
	
	
	public Basket() {

	}
	
	public Basket(User user) {
		this.user = user;
		this.open = true;
	}
	
	public Set<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}

	
	public void removeTicket(int id) {
		if (tickets != null) {
			Optional<Ticket> tickToRemove = tickets.stream().filter(o->o.getId() == id).findFirst();
			tickToRemove.ifPresent(t->tickets.remove(t)); 
		}
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isOpen() {
		return open;
	}
	
	/**
	 * Sets the status of the basket and its contents to open or closed.
	 * @param open
	 */
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public void closeAllTickets() {
		if (tickets != null) {
			tickets.forEach(i->i.setOpen(false));
		}
	}

	@Override
	public String toString() {
		return "Basket [tickets=" + tickets + ", user=" + user + ", open=" + open + "]";
	}
	
}	

