package com.example.FlightBookingSystem.ControllerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;

import com.example.FlightBookingSystem.Controller.FlightController;
import com.example.FlightBookingSystem.Controller.PassengerController;
import com.example.FlightBookingSystem.Controller.TicketController;
import com.example.FlightBookingSystem.Model.Passenger;
import com.example.FlightBookingSystem.Model.Ticket;
import com.example.FlightBookingSystem.Service.FlightService;
import com.example.FlightBookingSystem.Service.PassengerService;
import com.example.FlightBookingSystem.Service.TicketService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
class TicketControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private Model mockModel;
	
	@MockBean
	private HttpSession mockHttpSession;
	
	private TicketController ticketController;
	
	@MockBean
	private PassengerService mockPassengerService;
	
	@MockBean
	private TicketService mockTicketService;
	
	@MockBean
	private FlightService mockFlightService;

	@BeforeEach
	void setUp() throws Exception {
		ticketController = new TicketController(mockTicketService, mockPassengerService);
	}

	@Test
	void deleteTicketWithId_test() throws Exception {
		long id = 2L;
		
		ticketController.deleteTicketWithId(id);
		
		verify(mockTicketService).removeById(id);
		
		mvc.perform(MockMvcRequestBuilders.get("/deleteTicket?id={id}", id))
		.andExpect(MockMvcResultMatchers.view().name("redirect:/"));
	}
	
	@Test
	void getAllTicketDetails_test() throws Exception {
		long id = 1L;
		Passenger passenger = new Passenger("Avisek", "avsk", "12345", "adhikariavisek@gmail.com", 1234567L, 1234567L);
		
		when(mockHttpSession.getAttribute("passenger")).thenReturn(passenger);
		
		when(mockPassengerService.findById(passenger.getId())).thenReturn(passenger);
		
		List<Ticket> tickets = passenger.getTickets();
		
		ticketController.getAllTicketDetails(mockModel, mockHttpSession);
		
		verify(mockHttpSession).getAttribute("passenger");
		
		verify(mockPassengerService).findById(passenger.getId());
		
		verify(mockModel).addAttribute("tickets", tickets);	
	
		String test = "viewAllTickets";
		
		assertEquals(test, ticketController.getAllTicketDetails(mockModel, mockHttpSession));
		
	}
	
	@Test
	void getTicketView_test() throws Exception {
		long id = 1L;
		Ticket ticket = new Ticket();
		when(mockTicketService.getTicketById(id)).thenReturn(ticket);
		
		ticketController.getTicketView(mockModel, id);
		
		verify(mockTicketService).getTicketById(id);
		
		verify(mockModel).addAttribute("ticket", ticket);
		
		String test = "viewTicket";
		
		assertEquals(test, ticketController.getTicketView(mockModel, id));
		
	}
	
	@Test
	void ticketCancelConfirmation() throws Exception {
		long id = 1L;
		
		Ticket ticket = new Ticket();
		
		when(mockTicketService.getTicketById(id)).thenReturn(ticket);
		
		ticketController.ticketCancelConfirmation(id, mockModel);
		
		verify(mockTicketService).getTicketById(id);
		
		verify(mockModel).addAttribute("ticket", ticket);
		
		String test = "confirmTicket";
		
		assertEquals(test, ticketController.ticketCancelConfirmation(id, mockModel));
	}
	
	@Test
	void changeFlightForTicket() throws Exception {
		long id = 1L;
		
		Ticket ticket = new Ticket();
		
		when(mockTicketService.getTicketById(id)).thenReturn(ticket);
		
		ticketController.changeFlightForTicket(id, mockHttpSession);
		
		verify(mockTicketService).getTicketById(id);
		
		verify(mockHttpSession).setAttribute("ticket", ticket);
		
		String test = "redirect:/home";
		
		assertEquals(test, ticketController.changeFlightForTicket(id, mockHttpSession));
		
	}

}
