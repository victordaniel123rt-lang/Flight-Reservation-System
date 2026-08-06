package com.vdgarcia.events;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class ReservationCreada {

    private Long reservationId;
    private Long customerId;
    private Long flightId;
    private LocalDate date;
}
