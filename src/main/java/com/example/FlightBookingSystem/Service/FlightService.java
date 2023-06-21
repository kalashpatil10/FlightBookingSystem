package com.example.FlightBookingSystem.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FlightBookingSystem.Model.Flight;
import com.example.FlightBookingSystem.dao.FlightRepository;

@Service
public class FlightService {
	
	private FlightRepository flightRepo;
	
	
	@Autowired
	public FlightService(FlightRepository flightRepo) {
		super();
		this.flightRepo = flightRepo;
	}


	/**
	 * This method is used to find all the flights from the database
	 * @return arrayList of flights 
	 */
	public List<Flight> findAll() {
		List<Flight> flights = flightRepo.findAll();
		return flights;
	}


	/**
	 * This method return just the details of flight with given id
	 * @param id to find the flight details from the database
	 * @return the object of flight retrieved from database
	 */
	public Flight findById(Long id) {
		try {
			return this.flightRepo.findById(id)
					.orElseThrow(()-> new Exception("User not Found"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * This method is used to save flight details into the database
	 * @param flight object which will be saved into the database
	 */
	public void saveFlight(Flight flight) {
		flightRepo.save(flight);
		
	}


	/**
	 * This method is used to find the flights in route from source to destination
	 * @param source place from where you want to go
	 * @param destination place where you want to go
	 * @return list of flights in that route
	 */
	public List<Flight> flightsOnRoute(String source, String destination) {
		return flightRepo.returnFlightsOnRoute(source, destination);
	}
	
	/**
	 * Method to get the list of booked seats in a flight
	 * @param flightId
	 * @return
	 */
	public List<Integer> getBookedSeats(Long flightId) {
		List<Integer> seats = flightRepo.bookedSeatsInFlight(flightId);
		return seats;
	}
	
	/**
	 * Method to get the list of available seats in a flight
	 * @param flightId
	 * @return
	 */
	public List<Integer> getAvailableSeats(Long flightId) {
		List<Integer> bookedSeats = getBookedSeats(flightId);
		List<Integer> availableSeats = new ArrayList<>();
		for( int i = 1; i <= 20; i++) {
			if(bookedSeats.contains(i))
				continue;
			availableSeats.add(i);
		}
		for(int seats: availableSeats) {
			System.out.println(seats);
		}
		return availableSeats;
	}

}
