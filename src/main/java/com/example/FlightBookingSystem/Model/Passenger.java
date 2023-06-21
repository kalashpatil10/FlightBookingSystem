package com.example.FlightBookingSystem.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Proxy;

@Entity
public class Passenger {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String userName;
	private String password;
	private String email;
	private Long passportNumber;
	private Long phoneNumber;
	
	@OneToMany(mappedBy = "passenger")
	List<Ticket> tickets;
	


	public Passenger() {
		super();
	}


	public Passenger(String name, String userName, String password, String email, Long passportNumber, Long phoneNumber) {
		super();
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.passportNumber = passportNumber;
		this.phoneNumber = phoneNumber;
	}

	

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Long getPassportNumber() {
		return passportNumber;
	}


	public void setPassportNumber(Long passportNumber) {
		this.passportNumber = passportNumber;
	}


	public Long getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public List<Ticket> getTickets() {
		return tickets;
	}


	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}


	@Override
	public String toString() {
		return "Passenger [name=" + name + ", userName=" + userName + ", password=" + password + ", email=" + email
				+ ", passportNumber=" + passportNumber + ", phoneNumber=" + phoneNumber + ", tickets=" + tickets + "]";
	}
	
	
}
