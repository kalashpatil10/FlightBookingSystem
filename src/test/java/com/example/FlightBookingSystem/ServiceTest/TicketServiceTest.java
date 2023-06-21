package com.example.FlightBookingSystem.ServiceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.FlightBookingSystem.Model.Ticket;
import com.example.FlightBookingSystem.Service.TicketService;
import com.example.FlightBookingSystem.dao.TicketRepository;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {
	
	@Mock
	private TicketRepository ticketRepo;
	
	private TicketService ticketService;

	@BeforeEach
	void setUp() throws Exception {
		ticketService = new TicketService(ticketRepo);
	}

	@Test
	void saveTicket_test() {
		Ticket ticket = new Ticket();
		
		ticketService.saveTicket(ticket);
		
		verify(ticketRepo).save(ticket);
	}
	
	@Test
	void removeById_test() {
		long id = 1L;
		
		ticketService.removeById(id);
		
		verify(ticketRepo).deleteById(id);
	}
	
	@Test
	void getTicketById_test() {
		Ticket ticket = new Ticket();
		
		when(ticketRepo.findById(1L)).thenReturn(Optional.of(ticket));
		
		Ticket testTicket = ticketService.getTicketById(1L);
		
		verify(ticketRepo, times(1)).findById((1L));
		
		assertEquals(ticket, testTicket);
	}

}
