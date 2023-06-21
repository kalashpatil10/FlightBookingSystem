package com.example.FlightBookingSystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.FlightBookingSystem.Model.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

	/**
	 * Method with query to find given username and password
	 * @param userName
	 * @param password
	 * @return
	 */
	@Query("SELECT p from Passenger p where p.userName = :userName AND p.password = :password")
	Passenger checkLogin(String userName, String password);

}
