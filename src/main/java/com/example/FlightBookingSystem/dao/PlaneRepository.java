package com.example.FlightBookingSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FlightBookingSystem.Model.Plane;

@Repository
public interface PlaneRepository extends JpaRepository<Plane, Long> {

}
