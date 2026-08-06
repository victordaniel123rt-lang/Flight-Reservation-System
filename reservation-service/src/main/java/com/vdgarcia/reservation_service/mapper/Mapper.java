package com.vdgarcia.reservation_service.mapper;

import com.vdgarcia.reservation_service.dto.ReservationDTO;
import com.vdgarcia.reservation_service.model.Reservation;

public class Mapper {

    public static ReservationDTO toReservationDTO(Reservation entity){
        if(entity==null) return null;

        return ReservationDTO.builder()
                .id(entity.getId())
                .customerId(entity.getCustomerId())
                .flighId(entity.getFlighId())
                .estatus(entity.getEstatus())
                .reservationDate(entity.getReservationDate())
                .build();
    }


    public static Reservation toReservation(ReservationDTO dto){
        if (dto==null) return null;
        return Reservation.builder()
                .id(dto.getId())
                .customerId(dto.getCustomerId())
                .flighId(dto.getFlighId())
                .estatus(dto.getEstatus())
                .reservationDate(dto.getReservationDate())
                .build();

    }


}
