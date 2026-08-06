package com.vdgarcia.reservation_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "reservacion")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private Long flighId;
    private LocalDate reservationDate;
    private Estatus estatus;
}
