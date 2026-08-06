package com.vdgarcia.reservation_service.publisher.kafka;

import com.vdgarcia.events.ReservationCreada;
import com.vdgarcia.reservation_service.publisher.inter.CreatedEventPublisher;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class kafkaReservationPublisher implements CreatedEventPublisher {

    private final KafkaTemplate<String,Object> kafkaTemplate;

    @Value("${application.config.kafka.topics.reservation-created}")
    private String reservationCreadaTopic;

    @Override
    public void publishReservationCreated(ReservationCreada event) {
        log.info("Publicando evento ReservationCreada para la reservación ID: {}", event.getReservationId());
        kafkaTemplate.send(reservationCreadaTopic,String.valueOf(event.getReservationId()),event);
    }
}
