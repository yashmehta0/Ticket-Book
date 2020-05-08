package org.javacodegeeks.webservices.rest.ticket.service;

import java.util.List;

import org.javacodegeeks.webservices.rest.ticket.domain.Comment;
import org.javacodegeeks.webservices.rest.ticket.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	// --------------------------------------------
	// CRUD OPERATIONS FOR PARENT RECORDS (TICKETS)

	public Ticket createTicket(Ticket ticket) {
		return ticketRepository.save(ticket);
	}

	public List<Ticket> findAll() {
		return ticketRepository.findAll();
	}

	public Ticket findTicket(long id) {
		return ticketRepository.findOne(id);
	}

	public Ticket updateTicket(long id, Ticket ticket) {
		Ticket updatedTicket = findTicket(id);
		if (!ticket.getDescription().equals(updatedTicket.getDescription())) {
			updatedTicket.setDescription(ticket.getDescription());
			return ticketRepository.save(updatedTicket);
		} else
			return null;
	}

	public void deleteById(long id) {
		ticketRepository.delete(id);
	}

	@Autowired
	private CommentRepository commentRepository;

	// --------------------------------------------
	// CRUD OPERATIONS FOR CHILD RECORDS (COMMENTS)

	public Ticket createComment(long ticketId, Comment comment) {
		Ticket ticket = findTicket(ticketId);
		comment.setTicket(ticket);
		ticket.getComments().add(comment);

		return ticketRepository.save(ticket);
	}

	public List<Comment> findAllComments(long ticketId) {
		return findTicket(ticketId).getComments();
	}

	public Comment findComment(long id) {
		return commentRepository.findOne(id);
	}

	public Comment updateComment(long commentId, Comment comment) {
		Comment savedComment = commentRepository.findOne(commentId);
		savedComment.setText(comment.getText());
		commentRepository.save(savedComment);
		return savedComment;
	}

	public void deleteCommentById(long id) {
		commentRepository.delete(id);
	}
}
