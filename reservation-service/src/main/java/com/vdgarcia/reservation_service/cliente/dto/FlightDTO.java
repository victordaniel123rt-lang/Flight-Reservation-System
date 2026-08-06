package com.vdgarcia.reservation_service.cliente.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class FlightDTO {
    private Long id;
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private BigDecimal price;
    private Estatus estatus;
}
