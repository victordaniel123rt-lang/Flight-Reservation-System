package com.vdgarcia.flight_service.repository;

import com.vdgarcia.flight_service.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
