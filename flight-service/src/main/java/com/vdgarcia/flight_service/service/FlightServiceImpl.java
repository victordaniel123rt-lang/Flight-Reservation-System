package com.vdgarcia.flight_service.service;

import com.vdgarcia.flight_service.dto.FlightDTO;
import com.vdgarcia.flight_service.dto.FlightDisponibleDTO;
import com.vdgarcia.flight_service.mapper.Mapper;
import com.vdgarcia.flight_service.model.Estatus;
import com.vdgarcia.flight_service.model.Flight;
import com.vdgarcia.flight_service.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService{

    private final FlightRepository repository;


    @Override
    public List<FlightDTO> obtenerTodos() {
        return repository.findAll().stream().map(Mapper::toFlightDTO).toList();
    }

    @Override
    public FlightDTO obtenerPorId(Long id) {
        Flight flight = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Vuelo no encontrado")
        );
        return Mapper.toFlightDTO(flight);
    }

    @Override
    public FlightDTO crear(FlightDTO dto) {
        Flight flight = Mapper.toFlight(dto);
        Flight creado = repository.save(flight);
        return Mapper.toFlightDTO(creado);
    }

    @Override
    public FlightDTO actualizar(Long id, FlightDTO dto) {
        Flight flight = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Vuelo no encontrado")
        );
        Mapper.updateFlight(dto,flight);
        Flight actualizado = repository.save(flight);
        return Mapper.toFlightDTO(actualizado);
    }

    @Override
    public FlightDTO eliminar(Long id) {
        Flight flight = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Vuelo no encontrado")
        );
        repository.delete(flight);
        return Mapper.toFlightDTO(flight);
    }

    @Override
    public List<FlightDisponibleDTO> disponibles() {
        return repository.findAll().stream()
                .filter(f->f.getEstatus().equals(Estatus.DISPONIBLE))
                .map(Mapper::toFlightDisponibleDTO)
                .toList();
    }
}
