package com.vdgarcia.flight_service.dto;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class FlightDisponibleDTO {

    private String origin;
    private String destination;
    private LocalDate departureDate;
}
