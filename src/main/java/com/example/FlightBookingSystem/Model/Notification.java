package com.example.FlightBookingSystem.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Notification {

	@Id
	@GeneratedValue
	private Long id;
	
	private String message;
	
	private String createdAt;
	
	private boolean isRead;
	
	@ManyToOne
	private Passenger passenger;

	public Notification() {
		super();
	}
	
	

	public Notification(String message, String createdAt, boolean isRead) {
		super();
		this.message = message;
		this.createdAt = createdAt;
		this.isRead = isRead;
	}



	public Long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	@Override
	public String toString() {
		return "Notification [message=" + message + ", createdAt=" + createdAt + ", isRead=" + isRead
				+ "]";
	}
	
	
}
