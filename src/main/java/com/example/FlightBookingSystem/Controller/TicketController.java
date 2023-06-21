package com.example.FlightBookingSystem.Controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.FlightBookingSystem.Model.Passenger;
import com.example.FlightBookingSystem.Model.Ticket;
import com.example.FlightBookingSystem.Service.PassengerService;
import com.example.FlightBookingSystem.Service.TicketService;

import java.util.List;

@Controller
public class TicketController {
	
	private static final Logger logger = LoggerFactory.getLogger(FlightController.class);

	private TicketService ticketService;
	private PassengerService passengerService;

	@Autowired
	public TicketController(TicketService ticketService, PassengerService passengerService) {
		super();
		this.ticketService = ticketService;
		this.passengerService = passengerService;
	}
	
	/**
	 * Method to remove a ticket based on id
	 * @param id of the ticket to be removed
	 * @return
	 */
	@GetMapping("deleteTicket")
	public String deleteTicketWithId(@RequestParam long id) {
		logger.info("Ticket with id " + id + " deleted");
		ticketService.removeById(id);
		return "redirect:/";
	}
	
	/**
	 * This method is used to view all tickets assigned to a user
	 * @param model for displaying tickets list
	 * @param httpSession to get the details of the loggedin passenger
	 * @return
	 */
	@GetMapping("viewAllTickets")
	public String getAllTicketDetails(Model model, HttpSession httpSession) {
		Passenger passengerFromSession = (Passenger)httpSession.getAttribute("passenger");
		Passenger passengerFromDatabase = passengerService.findById(passengerFromSession.getId());
		List<Ticket> tickets = passengerFromDatabase.getTickets();
		model.addAttribute("tickets", tickets);
		return "viewAllTickets";
	}
	
	/**
	 * Method to view a single ticket
	 * @param model to pass the details of the ticket
	 * @param id for the ticket
	 * @return
	 */
	@GetMapping("viewTicket")
	public String getTicketView(Model model, @RequestParam long id) {
		Ticket ticket = ticketService.getTicketById(id);
		model.addAttribute("ticket", ticket);
		return "viewTicket";
	}
	
	/**
	 * This method is used to take to the confirmation page for deleting a ticket
	 * @param id for the ticket
	 * @param model to pass the data of the ticket
	 * @return
	 */
	@GetMapping("cancelTicket")
	public String ticketCancelConfirmation(@RequestParam long id, Model model) {
		logger.info("Ticket with id " + id + " canceled");
		Ticket ticket = ticketService.getTicketById(id);
		model.addAttribute("ticket",ticket);
		return "confirmTicket";
	}
	
	/**
	 * This method is to change the flight from the same ticket
	 * @param id of the ticket
	 * @param httpSession to save the details of the ticket in the session for change of booking
	 * @return
	 */
	@GetMapping("changeFlight")
	public String changeFlightForTicket(@RequestParam long id, HttpSession httpSession) {
		Ticket ticket = ticketService.getTicketById(id);
		httpSession.setAttribute("ticket", ticket);
		return "redirect:/home";
	}
}
