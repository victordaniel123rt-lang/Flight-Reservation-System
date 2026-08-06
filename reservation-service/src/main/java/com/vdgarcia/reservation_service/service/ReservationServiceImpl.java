package com.vdgarcia.reservation_service.service;

import com.vdgarcia.events.ReservationCreada;
import com.vdgarcia.reservation_service.cliente.CustomerClient;
import com.vdgarcia.reservation_service.cliente.FlightClient;
import com.vdgarcia.reservation_service.cliente.dto.CustomerDTO;
import com.vdgarcia.reservation_service.cliente.dto.Estatus;
import com.vdgarcia.reservation_service.cliente.dto.FlightDTO;
import com.vdgarcia.reservation_service.dto.ReservationDTO;
import com.vdgarcia.reservation_service.mapper.Mapper;
import com.vdgarcia.reservation_service.model.Reservation;
import com.vdgarcia.reservation_service.publisher.inter.CreatedEventPublisher;
import com.vdgarcia.reservation_service.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository repository;
    private final CustomerClient customerClient;
    private final FlightClient flightClient;
    private final CreatedEventPublisher publisher;

    @Override
    public ReservationDTO crear(ReservationDTO dto) {
        CustomerDTO existeCliente = customerClient.existeCliente(dto.getCustomerId());
        FlightDTO existeVuelo = flightClient.existeVuelo(dto.getFlighId());
        if(existeCliente==null){
            throw  new IllegalArgumentException("El cliente no existe, verifique la información");
        }
        if(existeVuelo==null){
            throw new IllegalArgumentException("El vuelo solicitado no existe");
        }
        FlightDTO flightDTO = flightClient.obtenerVuelo(dto.getFlighId());
        if(!flightDTO.getEstatus().equals(Estatus.DISPONIBLE)){
            throw new IllegalArgumentException("El vuelo ya no cuenta con asientos disponibles");
        }
        Reservation reservation = Reservation.builder()
                .customerId(dto.getCustomerId())
                .flighId(dto.getFlighId())
                .reservationDate(LocalDate.now())
                .estatus(com.vdgarcia.reservation_service.model.Estatus.CONFIRMED)
                .build();
        Reservation creada = repository.save(reservation);

        ReservationCreada event = ReservationCreada.builder()
                .reservationId(creada.getId())
                .flightId(creada.getFlighId())
                .customerId(creada.getCustomerId())
                .date(LocalDate.now())
                .build();
        publisher.publishReservationCreated(event);

        return Mapper.toReservationDTO(creada);
    }
}
