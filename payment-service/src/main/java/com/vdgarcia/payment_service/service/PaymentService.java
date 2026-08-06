package com.vdgarcia.payment_service.service;

import com.vdgarcia.events.ReservationCreada;
import com.vdgarcia.payment_service.dto.PaymentDTO;

public interface PaymentService {

    PaymentDTO crear(ReservationCreada reservationCreada);
}
