package org.javacodegeeks.webservices.rest.ticket.endpoint;

import java.util.List;

import org.javacodegeeks.webservices.rest.ticket.domain.Comment;
import org.javacodegeeks.webservices.rest.ticket.domain.Ticket;
import org.javacodegeeks.webservices.rest.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketEndpoint {

	@Autowired
	private TicketService ticketService;

	// --------------------------------------------
	// CRUD OPERATIONS FOR PARENT RECORDS (TICKETS)

	@PostMapping("/tickets")
	public Ticket createTicket(@RequestBody Ticket ticket) {
		Ticket savedTicket = ticketService.createTicket(ticket);
		return savedTicket;
	}

	@GetMapping("/tickets")
	public List<Ticket> getAllTickets() {
		return ticketService.findAll();
	}

	@GetMapping("/tickets/{id}")
	public Ticket getTicket(@PathVariable long id) {
		return ticketService.findTicket(id);
	}

	@PutMapping("/tickets/{id}")
	public Ticket changeTicket(@PathVariable long id, @RequestBody Ticket ticket) {
		return ticketService.updateTicket(id, ticket);
	}

	@DeleteMapping("/tickets/{id}")
	public String deleteTicket(@PathVariable long id) {
		ticketService.deleteById(id);
		return String.format("Ticket id #%d successfully deleted", id);
	}

	// --------------------------------------------
	// CRUD OPERATIONS FOR CHILD RECORDS (COMMENTS)

	@PostMapping("/tickets/{id}/comments")
	public Ticket createComment(@PathVariable long id, @RequestBody Comment comment) {
		return ticketService.createComment(id, comment);
	}

	@GetMapping("/tickets/{id}/comments")
	public List<Comment> getAllComments(@PathVariable long id) {
		return ticketService.findAllComments(id);
	}

	@GetMapping("/tickets/comments/{id}")
	public Comment getComment(@PathVariable long id) {
		return ticketService.findComment(id);
	}

	@PutMapping("/tickets/comments/{id}")
	public Comment changeComment(@PathVariable long id, @RequestBody Comment comment) {
		return ticketService.updateComment(id, comment);
	}

	@DeleteMapping("/tickets/comments/{id}")
	public String deleteComment(@PathVariable long id) {
		ticketService.deleteCommentById(id);
		return String.format("Comment id %d successfully deleted", id);
	}
}