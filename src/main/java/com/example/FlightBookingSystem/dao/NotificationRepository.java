package com.example.FlightBookingSystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.FlightBookingSystem.Model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

	@Query("SELECT n from Notification n where n.passenger.id = :passengerId")
	List<Notification> getNotificationForUser(Long passengerId);
	
}
