package com.routeplanner.shopping.service;
import java.util.List;
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
		logger.debug("Ticket saved with id: " + ticket.getId());
		return ticketRepository.save(ticket);
	}

	public List<Ticket> getAllTickets() {
		return ticketRepository.findAll();
	}
	
	public void delete(Integer ticketId) {
		ticketRepository.deleteById(ticketId);
		logger.debug("Ticket deleted with id: " + ticketId);
	}
	
}






