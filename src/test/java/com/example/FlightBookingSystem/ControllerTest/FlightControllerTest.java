//package com.example.FlightBookingSystem.ControllerTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpSession;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.ui.Model;
//
//import com.example.FlightBookingSystem.Controller.FlightController;
//import com.example.FlightBookingSystem.Controller.TicketController;
//import com.example.FlightBookingSystem.Model.Flight;
//import com.example.FlightBookingSystem.Model.Passenger;
//import com.example.FlightBookingSystem.Model.Ticket;
//import com.example.FlightBookingSystem.Service.FlightService;
//import com.example.FlightBookingSystem.Service.NotificationService;
//import com.example.FlightBookingSystem.Service.PassengerService;
//import com.example.FlightBookingSystem.Service.TicketService;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//
//
//@ExtendWith(MockitoExtension.class)
//@WebMvcTest
//class FlightControllerTest {
//	
//	@Autowired
//	private MockMvc mvc;
//	
//	@MockBean
//	private Model mockModel;
//	
//	@MockBean
//	private HttpSession mockHttpSession;
//	
//	private FlightController flightController;
//	
//	@MockBean
//	private PassengerService mockPassengerService;
//	
//	@MockBean
//	private TicketService mockTicketService;
//	
//	@MockBean
//	private FlightService mockFlightService;
//	
//	@Mock
//	private NotificationService mockNotificationService;
//
//	@BeforeEach
//	void setUp() throws Exception {
//		flightController = new FlightController(mockFlightService, mockPassengerService, mockTicketService, mockNotificationService);
//	}
//
//	@Test
//	void getAllFlightDetails_test() throws Exception {
//		List<Flight> flights = new ArrayList<Flight>();
//		
//		when(mockFlightService.findAll()).thenReturn(flights);
//		
//		flightController.getAllFlightDetails(mockModel);
//		
//		verify(mockFlightService).findAll();
//		
//		verify(mockModel).addAttribute("flights", flights);
//		
//		mvc.perform(MockMvcRequestBuilders.get("/allFlightDetails"))
//		.andExpect(MockMvcResultMatchers.view().name("allFlightDetails.html"));
//	}
//	
//	@Test
//	void getBookingPage_test() throws Exception{
//		mvc.perform(MockMvcRequestBuilders.get("/bookingPage"))
//		.andExpect(MockMvcResultMatchers.view().name("bookingPage.html"));
//	}
//	
//	@Test
//	void ticketVerification_test_fail() throws Exception{
//		Long id = 1L;
//		
//		flightController.ticketVerification(id, mockHttpSession, mockModel);
//		
//		verify(mockHttpSession).getAttribute("passenger");
//		
//		String test = "redirect:/login";
//		
//		assertEquals(test, flightController.ticketVerification(id, mockHttpSession, mockModel));
//	}
//	
//	@Test
//	void ticketVerification_test_pass() throws Exception {
//		Long id = 1L;
//		Passenger passenger = new Passenger("Avisek", "avsk", "12345", "adhikariavisek@gmail.com", 12345L, 12345L);
//		Flight flight = new Flight();
//		
//		List<Ticket> tickets = new ArrayList<Ticket>();
//		flight.setTickets(tickets);
//		passenger.setTickets(tickets);
//		
//		when(mockFlightService.findById(id)).thenReturn(flight);
//		
//		when(mockHttpSession.getAttribute("passenger")).thenReturn(passenger);
//		
//		when(mockPassengerService.findById(passenger.getId())).thenReturn(passenger);
//		
//		flightController.ticketVerification(id, mockHttpSession, mockModel);
//		
//		verify(mockFlightService).findById(id);
//		
//		verify(mockPassengerService).savePassenger(passenger);
//		
//		verify(mockHttpSession,times(2)).getAttribute("passenger");
//		
//		verify(mockPassengerService).findById(passenger.getId());
//		
//		verify(mockHttpSession).setAttribute("passenger", passenger);
//		
//		String test = "confirmTicket";
//		
//		assertEquals(test, flightController.ticketVerification(id, mockHttpSession, mockModel));
//	}
//	
//	@Test
//	void getSourceAndDestination_source_and_destination_equal_test() throws Exception {
//		String source = "Sydney";
//		String destination = "Sydney";
//		String equal = "isEqual";
//		
//		flightController.getSourceAndDestination(mockModel, source, destination, mockHttpSession);
//		
//		verify(mockModel).addAttribute("isEqual", equal);
//		
//		String test = "home";
//		
//		assertEquals(test, flightController.getSourceAndDestination(mockModel, source, destination, mockHttpSession));
//	}
//	
//	@Test
//	void getSourceAndDestination_booking_for_same_ticket_test() throws Exception {
//		String source = "Sydney";
//		String destination = "Melbourne";
//		List<Flight> flights = new ArrayList<Flight>();
//		Ticket ticket = new Ticket();
//		
//		when(mockFlightService.flightsOnRoute(source, destination)).thenReturn(flights);
//		
//		when(mockHttpSession.getAttribute("ticket")).thenReturn(ticket);
//		
//		flightController.getSourceAndDestination(mockModel, source, destination, mockHttpSession);
//		
//		verify(mockFlightService).flightsOnRoute(source, destination);
//		verify(mockHttpSession).getAttribute("ticket");
//		
//		String test = "bookingForSameTicketPage";
//		
//		assertEquals(test, flightController.getSourceAndDestination(mockModel, source, destination, mockHttpSession));
//		
//	}
//	
//	@Test
//	void getSourceAndDestination_booking_test() throws Exception{
//		String source = "Sydney";
//		String destination = "Melbourne";
//		List<Flight> flights = new ArrayList<Flight>();
//		
//		when(mockFlightService.flightsOnRoute(source, destination)).thenReturn(flights);
//		
//		when(mockHttpSession.getAttribute("ticket")).thenReturn(null);
//		
//		flightController.getSourceAndDestination(mockModel, source, destination, mockHttpSession);
//		
//		verify(mockFlightService).flightsOnRoute(source, destination);
//		
//		verify(mockHttpSession).getAttribute("ticket");
//		
//		String test = "allFlightDetails";
//		
//		assertEquals(test, flightController.getSourceAndDestination(mockModel, source, destination, mockHttpSession));
//	}
//	
//	
//	@Test
//	void replaceFlightOfTicket() throws Exception {
//		long id = 1L;
//		Flight flight = new Flight();
//		Ticket ticket = new Ticket();
//		
//		when(mockFlightService.findById(id)).thenReturn(flight);
//		
//		when(mockHttpSession.getAttribute("ticket")).thenReturn(ticket);
//		
//		flightController.replaceFlightOfTicket(id, mockHttpSession);
//		
//		verify(mockFlightService).findById(id);
//		
//		verify(mockHttpSession).getAttribute("ticket");
//		
//		verify(mockHttpSession).removeAttribute("ticket");
//		
//		verify(mockTicketService).saveTicket(ticket);
//		
//		String test = "redirect:/passengerProfile";
//		
//		assertEquals(test, flightController.replaceFlightOfTicket(id, mockHttpSession));
//	}
//
//}
