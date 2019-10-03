package com.routeplanner.shopping.service;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.routeplanner.shopping.Ticket;
import com.routeplanner.shopping.repository.TicketRepository;


@Transactional(isolation = Isolation.DEFAULT, propagation=Propagation.REQUIRED)
@Service
public class TicketService {

	private static final Logger logger = LoggerFactory.getLogger(TicketService.class);
	
	@Autowired
	private TicketRepository<Ticket> ticketRepository;
	
	public TicketService() {
		
	}

	public Ticket save(Ticket ticket) {
		return ticketRepository.save(ticket);
	}
	
	public List<Ticket> saveAll(Set<Ticket> tickets) {
		return ticketRepository.saveAll(tickets);
	}
	
	public List<Ticket> getAllTickets() {
		return ticketRepository.findAll();
	}
	
	public void delete(Integer ticketId) {
		ticketRepository.deleteById(ticketId);
		logger.debug("Ticket deleted with id: " + ticketId);
	}
	
}






