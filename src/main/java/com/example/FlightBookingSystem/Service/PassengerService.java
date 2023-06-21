package com.example.FlightBookingSystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FlightBookingSystem.Model.Passenger;
import com.example.FlightBookingSystem.dao.PassengerRepository;

@Service
public class PassengerService {
	
	private PassengerRepository passengerRepo;
	
	
	@Autowired
	public PassengerService(PassengerRepository passengerRepo) {
		super();
		this.passengerRepo = passengerRepo;
	}


	/**
	 * This method is used to save passenger details in the database
	 * @param passenger object for saving in the database
	 */
	public void savePassenger(Passenger passenger) {
		passengerRepo.save(passenger);
	}


	/**
	 * This method checks for the login details using username and password 
	 * @param userName to check the usersname in the database
	 * @param password to check the password in the database
	 * @return the passenger object with the username and password given
	 */
	public Passenger checkLogin(String userName, String password) {
		Passenger passenger = passengerRepo.checkLogin(userName, password);
		return passenger;
	}


	/**
	 * To find the passenger details from the database using ID
	 * @param id of the passenger you want to search
	 * @return passenger details from the database
	 */
	public Passenger findById(long id) {
		try {
			return this.passengerRepo.findById(id)
					.orElseThrow(()-> new Exception("User not Found"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	/**
	 * Method to delete a passenger from database using their id
	 * @param id of the passenger to be deleted
	 */
	public void removeById(long id) {
		passengerRepo.deleteById(id);
		
	}

}
