package com.learn.busBooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.busBooking.model.Bus;

public interface BusRepository extends JpaRepository<Bus, Long> {

	List<Bus> findBusBySourceAndDestinationAndDepartureDate(String source, String destination, String date);

	List<Bus> findBySourceAndDestination(String source, String destination);

}
