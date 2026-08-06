package com.vdgarcia.reservation_service.dto;

import com.vdgarcia.reservation_service.model.Estatus;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDTO {
    private Long id;
    private Long customerId;
    private Long flighId;
    private LocalDate reservationDate;
    private Estatus estatus;
}
