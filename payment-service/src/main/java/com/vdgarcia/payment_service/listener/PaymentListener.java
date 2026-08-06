package com.vdgarcia.payment_service.listener;

import com.vdgarcia.events.ReservationCreada;
import com.vdgarcia.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentListener {

    private final PaymentService service;
@KafkaListener(
        topics ="${application.config.kafka.topics.reservation-created}",
        groupId ="${spring.kafka.consumer.group-id}"
)
public void handleReservationCreada(ReservationCreada creada){
log.info("Evento recibido en Payment Service para la reservación ID: {}", creada.getReservationId());
service.crear(creada);
}

}
