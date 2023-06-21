package com.example.FlightBookingSystem.Model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Flight {

	@Id
	@GeneratedValue
	private Long id;
	private String source;
	private String destination;
	private String departureTime;
	private String arrivalInDestinationTime;
	private double fare;
	
	@ManyToOne
	private Plane plane;
	
	@OneToMany(mappedBy = "flight")
	private List<Ticket> tickets;

	public Flight() {
		super();
	}

	public Flight(String source, String destination, String departureTime, String arrivalInDestinationTime,
			List<Ticket> tickets, double fare) {
		super();
		this.source = source;
		this.destination = destination;
		this.departureTime = departureTime;
		this.arrivalInDestinationTime = arrivalInDestinationTime;
		this.tickets = tickets;
		this.fare = fare;
	}
	
	
		
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalInDestinationTime() {
		return arrivalInDestinationTime;
	}

	public void setArrivalInDestinationTime(String arrivalInDestinationTime) {
		this.arrivalInDestinationTime = arrivalInDestinationTime;
	}

	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	@Override
	public String toString() {
		return "Flight [source=" + source + ", destination=" + destination + ", departureTime=" + departureTime
				+ ", arrivalInDestinationTime=" + arrivalInDestinationTime + ", plane=" + plane +"]";
	}
	
	
	
	
	
	
}
