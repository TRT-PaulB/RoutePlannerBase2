package com.routeplanner.shopping;
import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.routeplanner.shopping.converters.PassengerTypeConverter;
import com.routeplanner.shopping.converters.TicketTypeConverter;

@Entity
@Table(name="ticket")
public class Ticket extends AbstractItem {
	
	@Convert(converter = PassengerTypeConverter.class)
	private PassengerType passengerType;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate travelDate;
	
	@Convert(converter = TicketTypeConverter.class)
	private TicketType ticketType;
		
	private String start;
	
	private String destination;
	
	public Ticket() {
		
	}
	
	public Ticket(boolean open, int numUnits, PassengerType passengerType, LocalDate travelDate, 
			String start, String destination) {
		super(numUnits);
		this.passengerType = passengerType;
		this.travelDate = travelDate;
		this.start = start;
		this.destination = destination;
	}

	public PassengerType getPassengerType() {
		return passengerType;
	}

	public void setPassengerType(PassengerType passengerType) {
		this.passengerType = passengerType;
	}
		

	public LocalDate getTravelDate() {
		return travelDate;
	}


	public void setTravelDate(LocalDate travelDate) {
		this.travelDate = travelDate;
	}


	public TicketType getTicketType() {
		return ticketType;
	}


	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}

	
	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	@Override
	public String toString() {
		return "Ticket [passengerType=" + passengerType + ", travelDate=" + travelDate + ", ticketType=" + ticketType
				+ ", start=" + start + ", destination=" + destination + "]";
	}
	
}	
