package com.example.FlightBookingSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.FlightBookingSystem.Model.Flight;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

	/**
	 * Method with query to find flights with given source and destination
	 * @param source
	 * @param destination
	 * @return
	 */
	@Query("SELECT f from Flight f where f.source = :source and f.destination = :destination")
	List<Flight> returnFlightsOnRoute(String source, String destination);
	
	/**
	 * Method to find the seats that were occupied for a flight to know the available flights
	 * @param flightId
	 * @return
	 */
	@Query("SELECT t.seatNumber from Ticket t INNER JOIN t.flight where t.flight.id = :flightId")
	List<Integer> bookedSeatsInFlight(Long flightId);
}
