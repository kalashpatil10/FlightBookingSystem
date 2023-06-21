package com.example.FlightBookingSystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FlightBookingSystem.Model.Ticket;
import com.example.FlightBookingSystem.dao.TicketRepository;

@Service
public class TicketService {

	private TicketRepository ticketRepo;

	@Autowired
	public TicketService(TicketRepository ticketRepo) {
		super();
		this.ticketRepo = ticketRepo;
	}

	/**
	 * method to save a ticket in database
	 * @param ticket object to be saved
	 */
	public void saveTicket(Ticket ticket) {
		ticketRepo.save(ticket);
		
	}

	/**
	 * Method to remove ticket from the database
	 * @param id for the ticket to be removed
	 */
	public void removeById(long id) {
		ticketRepo.deleteById(id);
	}

	/**
	 * Method to find ticket based on the id of the ticket
	 * @param id for the ticket 
	 * @return searched ticket by id object
	 */
	public Ticket getTicketById(long id)  {
		try {
			return this.ticketRepo.findById(id)
					.orElseThrow(()-> new Exception("User not Found"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	
}
