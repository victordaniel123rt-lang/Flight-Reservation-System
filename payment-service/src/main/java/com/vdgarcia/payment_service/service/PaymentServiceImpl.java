package com.vdgarcia.payment_service.service;

import com.vdgarcia.events.PaymentCreado;
import com.vdgarcia.events.ReservationCreada;
import com.vdgarcia.payment_service.dto.PaymentDTO;
import com.vdgarcia.payment_service.mapper.Mapper;
import com.vdgarcia.payment_service.model.Estatus;
import com.vdgarcia.payment_service.model.Payment;
import com.vdgarcia.payment_service.publisher.inter.CreateEventPublisher;
import com.vdgarcia.payment_service.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository repository;
    private final CreateEventPublisher publisher;

    @Override
    public PaymentDTO crear(ReservationCreada reservationCreada) {

        Payment payment = Payment.builder()
                .reservationId(reservationCreada.getReservationId())
                .amount(8500L)
                .status(Estatus.PAID)
                .build();
        Payment guardada = repository.save(payment);

        PaymentCreado evento = PaymentCreado.builder()
                .reservationId(guardada.getReservationId())
                .paymentStatus(com.vdgarcia.events.Estatus.PAID)
                .build();
        publisher.publishPaymentCreate(evento);

        return Mapper.toPaymentDTO(guardada);
    }
}
