package com.example.FlightBookingSystem;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.FlightBookingSystem.Model.Flight;
import com.example.FlightBookingSystem.Model.Passenger;
import com.example.FlightBookingSystem.Model.Plane;
import com.example.FlightBookingSystem.Model.Ticket;
import com.example.FlightBookingSystem.Service.FlightService;
import com.example.FlightBookingSystem.dao.FlightRepository;
import com.example.FlightBookingSystem.dao.PassengerRepository;
import com.example.FlightBookingSystem.dao.PlaneRepository;
import com.example.FlightBookingSystem.dao.TicketRepository;

@Component
public class Runner implements ApplicationRunner {

	private FlightRepository flightRepo;
	private PassengerRepository passengerRepo;
	private PlaneRepository planeRepo;
	private TicketRepository ticketRepo;
	private FlightService flightService;
	
	
	
	@Autowired
	public Runner(FlightRepository flightRepo, PassengerRepository passengerRepo, PlaneRepository planeRepo,
			TicketRepository ticketRepo, FlightService flightService) {
		super();
		this.flightRepo = flightRepo;
		this.passengerRepo = passengerRepo;
		this.planeRepo = planeRepo;
		this.ticketRepo = ticketRepo;
		this.flightService = flightService;
	}



	/**
	 * DataLoader with predetermined flight and plane data
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Passenger passenger1 = new Passenger("Avisek Adhikari", "avsk", "12345", "adhikariavisek@gmail.com", 11184554L, 0416116354L);	
		passengerRepo.save(passenger1);

		Passenger passenger2 = new Passenger("Anupam Adhikari", "anup", "54321", "adhikarianupam@gmail.com", 11145345L, 0413534116354L);
		passengerRepo.save(passenger2);
		
		Plane plane1 = new Plane(20, "T501", null);
		Plane plane2 = new Plane(20, "T503", null);
		Plane plane3 = new Plane(20, "A-T1Z", null);
		Plane plane4 = new Plane(20, "T502", null);
		Plane plane5 = new Plane(20, "Alpha-1", null);
		Plane plane6 = new Plane(20, "Beta-B", null);
		
		planeRepo.save(plane1);
		planeRepo.save(plane2);
		planeRepo.save(plane3);
		planeRepo.save(plane4);
		planeRepo.save(plane5);
		planeRepo.save(plane6);
		
		
		List<Flight> flightsToPerth = new ArrayList<Flight>();
		List<Flight> flightsToSydney = new ArrayList<Flight>();
		
		
		flightsToPerth.add(new Flight("Sydney", "Melbourne", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToPerth.add(new Flight("Sydney", "Adelaide", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToPerth.add(new Flight("Sydney", "Perth", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToPerth.add(new Flight("Melbourne", "Sydney", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToPerth.add(new Flight("Melbourne", "Adelaide", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToPerth.add(new Flight("Melbourne", "Perth", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToPerth.add(new Flight("Adelaide", "Sydney", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToPerth.add(new Flight("Adelaide", "Melbourne", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToPerth.add(new Flight("Adelaide", "Perth", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToPerth.add(new Flight("Perth", "Sydney", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		
		flightsToPerth.add(new Flight("Perth", "Melbourne", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToPerth.add(new Flight("Perth", "Adelaide", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToPerth.add(new Flight("Sydney", "Melbourne", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToPerth.add(new Flight("Sydney", "Adelaide", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToPerth.add(new Flight("Sydney", "Perth", "2023/06/16 11:30", "2023/06/16 01:05", null, 25.5));
		flightsToPerth.add(new Flight("Melbourne", "Sydney", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToPerth.add(new Flight("Melbourne", "Adelaide", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToPerth.add(new Flight("Melbourne", "Perth", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToPerth.add(new Flight("Adelaide", "Sydney", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToPerth.add(new Flight("Adelaide", "Melbourne", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		
		flightsToPerth.add(new Flight("Adelaide", "Perth", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToPerth.add(new Flight("Perth", "Sydney", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToPerth.add(new Flight("Perth", "Melbourne", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToPerth.add(new Flight("Perth", "Adelaide", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToPerth.add(new Flight("Sydney", "Melbourne", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToPerth.add(new Flight("Sydney", "Adelaide", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToPerth.add(new Flight("Sydney", "Perth", "2023/06/16 04:30", "2023/06/16 06:05", null, 24.5));
		flightsToPerth.add(new Flight("Melbourne", "Sydney", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToPerth.add(new Flight("Melbourne", "Adelaide", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToPerth.add(new Flight("Melbourne", "Perth", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		
		flightsToSydney.add(new Flight("Sydney", "Melbourne", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToSydney.add(new Flight("Sydney", "Adelaide", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToSydney.add(new Flight("Sydney", "Perth", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToSydney.add(new Flight("Melbourne", "Sydney", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToSydney.add(new Flight("Melbourne", "Adelaide", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToSydney.add(new Flight("Melbourne", "Perth", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToSydney.add(new Flight("Adelaide", "Sydney", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToSydney.add(new Flight("Adelaide", "Melbourne", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToSydney.add(new Flight("Adelaide", "Perth", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToSydney.add(new Flight("Perth", "Sydney", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		
		flightsToSydney.add(new Flight("Perth", "Melbourne", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToSydney.add(new Flight("Perth", "Adelaide", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToSydney.add(new Flight("Sydney", "Melbourne", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToSydney.add(new Flight("Sydney", "Adelaide", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToSydney.add(new Flight("Sydney", "Perth", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToSydney.add(new Flight("Melbourne", "Sydney", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToSydney.add(new Flight("Melbourne", "Adelaide", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToSydney.add(new Flight("Melbourne", "Perth", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToSydney.add(new Flight("Adelaide", "Sydney", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToSydney.add(new Flight("Adelaide", "Melbourne", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		
		flightsToSydney.add(new Flight("Adelaide", "Perth", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToSydney.add(new Flight("Perth", "Sydney", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToSydney.add(new Flight("Perth", "Melbourne", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToSydney.add(new Flight("Perth", "Adelaide", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToSydney.add(new Flight("Sydney", "Melbourne", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToSydney.add(new Flight("Sydney", "Adelaide", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToSydney.add(new Flight("Sydney", "Perth", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToSydney.add(new Flight("Melbourne", "Sydney", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToSydney.add(new Flight("Melbourne", "Adelaide", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		flightsToSydney.add(new Flight("Melbourne", "Perth", "2023/06/16 10:30", "2023/06/16 12:05", null, 26.5));
		
		
		List<Flight> firstPlaneFlights = new ArrayList<Flight>();
		List<Flight> secondPlaneFlights = new ArrayList<Flight>();
		List<Flight> thirdPlaneFlights = new ArrayList<Flight>();
		List<Flight> fourthPlaneFlights = new ArrayList<Flight>();
		List<Flight> fifthPlaneFlights = new ArrayList<Flight>();
		List<Flight> sixthPlaneFlights = new ArrayList<Flight>();
		
		for(int i = 0; i < 5; i++) {
			firstPlaneFlights.add(flightsToPerth.get(i));
		}
		
		for(int i = 0; i < 5; i++) {
			firstPlaneFlights.add(flightsToSydney.get(i));
		}
		
		for(int i = 5; i < 10; i++) {
			secondPlaneFlights.add(flightsToPerth.get(i));
		}
		
		for(int i = 5; i < 10; i++) {
			secondPlaneFlights.add(flightsToSydney.get(i));
		}
		
		for(int i = 10; i < 15; i++) {
			thirdPlaneFlights.add(flightsToPerth.get(i));
		}
		
		for(int i = 10; i < 15; i++) {
			thirdPlaneFlights.add(flightsToSydney.get(i));
		}
		
		for(int i = 15; i < 20; i++) {
			fourthPlaneFlights.add(flightsToPerth.get(i));
		}
		
		for(int i = 15; i < 20; i++) {
			fourthPlaneFlights.add(flightsToSydney.get(i));
		}
		
		for(int i = 20; i < 25; i++) {
			fifthPlaneFlights.add(flightsToPerth.get(i));
		}
		
		for(int i = 20; i < 25; i++) {
			fifthPlaneFlights.add(flightsToSydney.get(i));
		}
		
		for(int i = 25; i < 30; i++) {
			sixthPlaneFlights.add(flightsToPerth.get(i));
		}
		
		for(int i = 25; i < 30; i++) {
			sixthPlaneFlights.add(flightsToSydney.get(i));
		}
		
		plane1.setFlights(firstPlaneFlights);
		plane2.setFlights(secondPlaneFlights);
		plane3.setFlights(thirdPlaneFlights);
		plane4.setFlights(fourthPlaneFlights);
		plane5.setFlights(fifthPlaneFlights);
		plane6.setFlights(sixthPlaneFlights);
		
		
		for(int i = 0; i < 5; i++) {
			flightsToPerth.get(i).setPlane(plane1);
		}
		
		for(int i = 0; i < 5; i++) {
			flightsToSydney.get(i).setPlane(plane1);
		}
		
		for(int i = 5; i < 10; i++) {
			flightsToPerth.get(i).setPlane(plane2);
		}
		
		for(int i = 5; i < 10; i++) {
			flightsToSydney.get(i).setPlane(plane2);
		}
		
		for(int i = 10; i < 15; i++) {
			flightsToPerth.get(i).setPlane(plane3);
		}
		
		for(int i = 10; i < 15; i++) {
			flightsToSydney.get(i).setPlane(plane3);
		}
		
		for(int i = 15; i < 20; i++) {
			flightsToPerth.get(i).setPlane(plane4);
		}
		
		for(int i = 15; i < 20; i++) {
			flightsToSydney.get(i).setPlane(plane4);
		}
		
		for(int i = 20; i < 25; i++) {
			flightsToPerth.get(i).setPlane(plane5);
		}
		
		for(int i = 20; i < 25; i++) {
			flightsToSydney.get(i).setPlane(plane5);
		}
		
		for(int i = 25; i < 30; i++) {
			flightsToPerth.get(i).setPlane(plane6);
		}
		
		for(int i = 25; i < 30; i++) {
			flightsToSydney.get(i).setPlane(plane6);
		}
		
		
		for(Flight flight : flightsToPerth) {
			flightRepo.save(flight);
		}
		
		for(Flight flight : flightsToSydney) {
			flightRepo.save(flight);
		}
	
		Ticket ticket1 = new Ticket(passenger1);
		Ticket ticket2 = new Ticket(passenger2);
		Ticket ticket3 = new Ticket(passenger2);
		Ticket ticket4 = new Ticket(passenger2);
		Ticket ticket5 = new Ticket(passenger2);
		Ticket ticket6 = new Ticket(passenger2);
		
		ticket1.setFlight(flightsToPerth.get(0));
		ticket2.setFlight(flightsToPerth.get(0));
		ticket3.setFlight(flightsToPerth.get(0));
		ticket4.setFlight(flightsToPerth.get(1));
		ticket5.setFlight(flightsToPerth.get(1));
		ticket6.setFlight(flightsToPerth.get(1));
		
		ticket1.setSeatNumber(5);
		ticket2.setSeatNumber(8);
		ticket3.setSeatNumber(1);
		ticket4.setSeatNumber(2);
		ticket5.setSeatNumber(3);
		ticket6.setSeatNumber(4);
		
		ticketRepo.save(ticket1);
		ticketRepo.save(ticket2);
		ticketRepo.save(ticket3);
		ticketRepo.save(ticket4);
		ticketRepo.save(ticket5);
		ticketRepo.save(ticket6);
		
	}

}
