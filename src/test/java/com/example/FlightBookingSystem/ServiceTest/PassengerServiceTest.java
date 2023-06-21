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

import com.example.FlightBookingSystem.Model.Passenger;
import com.example.FlightBookingSystem.Service.PassengerService;
import com.example.FlightBookingSystem.dao.PassengerRepository;


@ExtendWith(MockitoExtension.class)
class PassengerServiceTest {
	
	@Mock
	private PassengerRepository mockPassengerRepo;
	
	private PassengerService passengerService;

	@BeforeEach
	void setUp() throws Exception {
		passengerService = new PassengerService(mockPassengerRepo);
	}

	@Test
	void savePassenger_test() {
		Passenger passenger = new Passenger();
		
		passengerService.savePassenger(passenger);
		
		verify(mockPassengerRepo,times(1)).save(passenger);
	}
	
	@Test
	void checkLogin_test() {
		Passenger passenger = new Passenger();
		String testUsername = "avsk";
		String testPassword = "12345";
		
		when(mockPassengerRepo.checkLogin(testUsername, testPassword)).thenReturn(passenger);
		
		Passenger testPassenger = passengerService.checkLogin(testUsername, testPassword);
		
		verify(mockPassengerRepo).checkLogin(testUsername, testPassword);
		
		assertEquals(passenger, testPassenger);
		
	}
	
	@Test
	void findById_test() {
		Passenger passenger = new Passenger();
		
		when(mockPassengerRepo.findById(1L)).thenReturn(Optional.of(passenger));
		
		Passenger testPassenger = passengerService.findById(1L);
		
		verify(mockPassengerRepo, times(1)).findById((1L));
		
		assertEquals(passenger, testPassenger);
	}
	
	@Test
	void removeById() {
		long id = 1L;
		
		passengerService.removeById(id);
		
		verify(mockPassengerRepo).deleteById(id);
	}

}
