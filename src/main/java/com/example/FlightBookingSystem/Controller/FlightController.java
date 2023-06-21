package com.example.FlightBookingSystem.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.FlightBookingSystem.Model.Flight;
import com.example.FlightBookingSystem.Model.Notification;
import com.example.FlightBookingSystem.Model.Passenger;
import com.example.FlightBookingSystem.Model.Ticket;
import com.example.FlightBookingSystem.Service.FlightService;
import com.example.FlightBookingSystem.Service.NotificationService;
import com.example.FlightBookingSystem.Service.PassengerService;
import com.example.FlightBookingSystem.Service.TicketService;

@Controller
public class FlightController {
	
	private static final Logger logger = LoggerFactory.getLogger(FlightController.class);

	private FlightService flightService;
	private PassengerService passengerService;
	private TicketService ticketService;
	private NotificationService notificationService;

	@Autowired
	public FlightController(FlightService flightService, PassengerService passengerService, TicketService ticketService, NotificationService notificationService) {
		super();
		this.flightService = flightService;
		this.passengerService = passengerService;
		this.ticketService = ticketService;
		this.notificationService = notificationService;
	}
	
	/**
	 * This method is used to get all the flights from the database
	 * @param model to pass list of flights
	 * @return
	 */
	@GetMapping("allFlightDetails")
	public String getAllFlightDetails(Model model) {
		List<Flight> flights = this.flightService.findAll();
		logger.info("Found All the flights");
		model.addAttribute("flights", flights);
		return "allFlightDetails.html";
		
	}
	
	/**
	 * This method is used to get the bookingpage
	 * @param
	 * @return
	 */
	@GetMapping("bookingPage")
	public String getBookingPage(Model model) {
		return "bookingPage.html";
	}
	
	@GetMapping("chooseSeat")
	public String chooseSeatForFlight(@RequestParam Long id, Model model, HttpSession httpSession) {
		if (httpSession.getAttribute("passenger") == null) {
			return "redirect:/login";
		}
		List<Integer> availableSeats = flightService.getAvailableSeats(id);
		Flight flight = flightService.findById(id);
		model.addAttribute("availableSeats", availableSeats);
		model.addAttribute("flight", flight);
		return "chooseSeat";
	}
	
	/**
	 * This method takes the id from the flight and then creates a ticket 
	 * @param id of the flight
	 * @param httpSession for passenger details
	 * @param model to pass the data of ticket
	 * @return
	 * @throws Exception
	 */
	@GetMapping("bookingFlight")
	public String ticketVerification(@RequestParam("id") Long id, @RequestParam(value = "chooseSeat", required = false) String seatNumber,HttpSession httpSession, Model model) throws Exception {
		System.out.println(seatNumber);
		if (httpSession.getAttribute("passenger") == null) {
			logger.info("User tried to book without logging in");
			return "redirect:/login";
		}
		Flight flight = flightService.findById(id);
		Passenger passengerFromSession = (Passenger) httpSession.getAttribute("passenger");
		Passenger passengerFromDatabase = passengerService.findById(passengerFromSession.getId());
		
		Ticket ticket = new Ticket(passengerFromDatabase);
		ticket.setFlight(flight);
		
		List<Ticket> ticketsInFlight = flight.getTickets();
		ticketsInFlight.add(ticket);
		flight.setTickets(ticketsInFlight);
		flightService.saveFlight(flight);
		
		List<Ticket> ticketsForPassenger = passengerFromDatabase.getTickets();
		ticketsForPassenger.add(ticket);
		passengerFromDatabase.setTickets(ticketsForPassenger);
		passengerService.savePassenger(passengerFromDatabase);
		
		ticketService.saveTicket(ticket);
		
		httpSession.setAttribute("passenger", passengerFromDatabase);
		
		model.addAttribute("ticket", ticket);
		
		logger.info("Booking done for " + passengerFromDatabase.getName() + " with flight id " + id);
		
		String time = notificationService.currentTime();
		
		Notification notification = new Notification("Booking done for " + passengerFromDatabase.getName() + " with flight id " + id , time , false);
		notification.setPassenger(passengerFromDatabase);
		notificationService.saveNotification(notification);
		
		return "confirmTicket";
		
	}
	
	/**
	 * This method provide list of the flights based on new booking or changing flight in existing ticket
	 * @param model to pass the details of flights to view
	 * @param source
	 * @param destination
	 * @param httpSession
	 * @return
	 */
	@GetMapping("flightDetails")
	public String getSourceAndDestination(Model model, @RequestParam("source-place") String source, @RequestParam("destination-place") String destination, HttpSession httpSession) {
		if ( source.equals(destination)) {
			String equal = "isEqual";
			model.addAttribute("isEqual", equal);
			return "home";
		}
		else {
			List<Flight> flights = flightService.flightsOnRoute(source, destination);
			model.addAttribute("flights",flights);
			Ticket ticket = (Ticket)httpSession.getAttribute("ticket");
			if(ticket != null) {
				logger.info("Flight changed for ticket with id " + ticket.getId());
				return "bookingForSameTicketPage";
			}
			
			return "allFlightDetails";
		}
	}
	
	/**
	 * This method is used to replace ticket
	 * @param id of the flight
	 * @param httpSession
	 * @return
	 */
	@GetMapping("replaceTicket")
	public String replaceFlightOfTicket(@RequestParam long id, HttpSession httpSession) {
		Flight flight = flightService.findById(id);
		Ticket ticket = (Ticket)httpSession.getAttribute("ticket");
		ticket.setFlight(flight);
		httpSession.removeAttribute("ticket");
		ticketService.saveTicket(ticket);
		logger.info("Ticket replaced for ticket id " + ticket.getId());
		return "redirect:/passengerProfile";
	}
}
