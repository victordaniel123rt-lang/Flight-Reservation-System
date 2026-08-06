package com.vdgarcia.payment_service.dto;

import com.vdgarcia.payment_service.model.Estatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class PaymentDTO {
    private Long id;
    private Long reservationId;
    private Long amount;
    private Estatus status;
}
