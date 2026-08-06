package com.vdgarcia.events;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentCreado {

    private Long reservationId;
    private Estatus paymentStatus;
}
