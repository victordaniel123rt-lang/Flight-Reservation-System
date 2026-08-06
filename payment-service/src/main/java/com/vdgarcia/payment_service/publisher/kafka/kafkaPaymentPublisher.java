package com.vdgarcia.payment_service.publisher.kafka;

import com.vdgarcia.events.PaymentCreado;
import com.vdgarcia.payment_service.publisher.inter.CreateEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class kafkaPaymentPublisher implements CreateEventPublisher {


    private final KafkaTemplate<String,Object> kafkaTemplate;
    @Value("${application.config.kafka.topics.payment-completed}")
    private String paymentCompletedTopic;

    @Override
    public void publishPaymentCreate(PaymentCreado evento) {
        log.info("Publicando evento en payment-completed para la reservation ID: {}", evento.getReservationId());
        kafkaTemplate.send(paymentCompletedTopic, String.valueOf(evento.getReservationId()),evento);
    }
}
