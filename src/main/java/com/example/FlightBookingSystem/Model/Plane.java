package com.example.FlightBookingSystem.Model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Plane {

	@Id
	@GeneratedValue
	private Long id;
	private int capacity;
	private String model;
	
	@OneToMany(mappedBy = "plane")
	private List<Flight> flights;

	public Plane() {
		super();
	}

	public Plane(int capacity, String model, List<Flight> flights) {
		super();
		this.capacity = capacity;
		this.model = model;
		this.flights = flights;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	@Override
	public String toString() {
		return "Plane [capacity=" + capacity + ", model=" + model + ", flights=" + flights + "]";
	}
	
	
}
