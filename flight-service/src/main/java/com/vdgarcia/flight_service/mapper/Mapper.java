package com.vdgarcia.flight_service.mapper;

import com.vdgarcia.flight_service.dto.FlightDTO;
import com.vdgarcia.flight_service.dto.FlightDisponibleDTO;
import com.vdgarcia.flight_service.model.Flight;

public class Mapper {

    public static FlightDTO toFlightDTO(Flight entity){
        if (entity==null) return null;
        return FlightDTO.builder()
                .id(entity.getId())
                .origin(entity.getOrigin())
                .departureDate(entity.getDepartureDate())
                .arrivalDate(entity.getArrivalDate())
                .destination(entity.getDestination())
                .estatus(entity.getEstatus())
                .price(entity.getPrice())
                .build();
    }


    public static Flight toFlight(FlightDTO dto){
        if(dto==null) return null;
        return Flight.builder()
                .id(dto.getId())
                .origin(dto.getOrigin())
                .destination(dto.getDestination())
                .price(dto.getPrice())
                .estatus(dto.getEstatus())
                .departureDate(dto.getDepartureDate())
                .arrivalDate(dto.getArrivalDate())
                .build();
    }

    public static FlightDisponibleDTO toFlightDisponibleDTO(Flight entity){
        if(entity==null) return null;

        return FlightDisponibleDTO.builder()
                .origin(entity.getOrigin())
                .destination(entity.getDestination())
                .departureDate(entity.getDepartureDate())
                .build();
    }

    public static  void updateFlight(FlightDTO dto, Flight entity){
        if(dto == null || entity==null) return;
        entity.setOrigin(dto.getOrigin());
        entity.setDestination(dto.getDestination());
        entity.setEstatus(dto.getEstatus());
        entity.setPrice(dto.getPrice());
        entity.setDepartureDate(dto.getDepartureDate());
        entity.setArrivalDate(dto.getArrivalDate());
    }


}
