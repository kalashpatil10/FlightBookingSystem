package com.example.FlightBookingSystem.ServiceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.FlightBookingSystem.Model.Flight;
import com.example.FlightBookingSystem.Service.FlightService;
import com.example.FlightBookingSystem.dao.FlightRepository;


@ExtendWith(MockitoExtension.class)
class FlightServiceTest {
	
	@Mock
	private FlightRepository mockFlightRepo;
	
	private FlightService flightService;

	@BeforeEach
	void setUp() throws Exception {
		flightService = new FlightService(mockFlightRepo);
	}

	@Test
	void findAll_test() {
		Flight flight = new Flight();
		Flight flight2 = new Flight();
		List<Flight> flights = new ArrayList<>();
		flights.add(flight);
		flights.add(flight2);
		when(mockFlightRepo.findAll()).thenReturn(flights);
		
		List<Flight> flights2 = flightService.findAll();
		
		verify(mockFlightRepo,times(1)).findAll();
		
		assertEquals(flights, flights2);
	}
	
	
	@Test
	void findById_test() {
		Flight flight = new Flight();
		
		when(mockFlightRepo.findById(1L)).thenReturn(Optional.of(flight));
		
		Flight testFlight = flightService.findById(1L);
		
		verify(mockFlightRepo, times(1)).findById((1L));
		
		assertEquals(flight, testFlight);
	}
	
	@Test
	void saveFliht_test() {
		Flight flight = new Flight();
		flightService.saveFlight(flight);
		
		verify(mockFlightRepo, times(1)).save(flight);
	}
	
	@Test
	void flightOnRoute_test() {
		Flight flight = new Flight();
		Flight flight2 = new Flight();
		List<Flight> flights = new ArrayList<>();
		flights.add(flight);
		flights.add(flight2);
		String source = "Sydney";
		String destination = "Melbourne";
		
		when(mockFlightRepo.returnFlightsOnRoute(source, destination)).thenReturn(flights);
		
		List<Flight> flights2 = flightService.flightsOnRoute(source, destination);
		
		verify(mockFlightRepo).returnFlightsOnRoute(source, destination);
		
		assertEquals(flights,flights2);
	}

}
