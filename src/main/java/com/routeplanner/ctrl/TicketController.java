package com.routeplanner.ctrl;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.routeplanner.shopping.Ticket;
import com.routeplanner.shopping.service.TicketService;

@RestController
@RequestMapping("ticket")
public class TicketController {

	private final static Logger logger = LoggerFactory.getLogger(TicketController.class);
	
	@Autowired
	private TicketService ticketService;

	
	@GetMapping("/all")
	List<Ticket> getAllTickets() {
		return ticketService.getAllTickets();
	}
		
	@DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable Integer id) {
        logger.info("Request to delete ticket id: {}", id);
        ticketService.delete(id);
        return ResponseEntity.ok().build();
    }
	
	@PostMapping("/admin/add")
	ResponseEntity<Ticket> addNewTicket(@RequestBody Ticket ticket) throws URISyntaxException {
		Ticket result = ticketService.save(ticket);
		logger.debug("Ticket saved with id: " + result.getId());
		return ResponseEntity.created(new URI("/ticket/admin/add" + result.getId()))
                .body(result);
	}
	
}

