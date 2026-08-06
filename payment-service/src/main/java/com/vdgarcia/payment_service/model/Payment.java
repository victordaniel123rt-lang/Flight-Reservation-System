package com.vdgarcia.payment_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pagos")
@Getter @Setter
@AllArgsConstructor @RequiredArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long reservationId;
    private Long amount;
    private Estatus status;
}
