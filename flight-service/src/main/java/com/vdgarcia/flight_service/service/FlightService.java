package com.vdgarcia.flight_service.service;

import com.vdgarcia.flight_service.dto.FlightDTO;
import com.vdgarcia.flight_service.dto.FlightDisponibleDTO;

import java.util.List;

public interface FlightService {
    List<FlightDTO> obtenerTodos();
    FlightDTO obtenerPorId(Long id);
    FlightDTO crear(FlightDTO dto);
    FlightDTO actualizar(Long id,FlightDTO dto);
    FlightDTO eliminar(Long id);
    List<FlightDisponibleDTO> disponibles();
}
