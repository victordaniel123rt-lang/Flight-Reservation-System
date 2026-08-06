package com.vdgarcia.payment_service.publisher.inter;

import com.vdgarcia.events.PaymentCreado;

public interface CreateEventPublisher {
    void publishPaymentCreate(PaymentCreado evento);
}
