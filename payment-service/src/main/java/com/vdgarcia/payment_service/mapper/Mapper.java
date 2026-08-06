package com.vdgarcia.payment_service.mapper;

import com.vdgarcia.payment_service.dto.PaymentDTO;
import com.vdgarcia.payment_service.model.Payment;

public class Mapper {

    public static PaymentDTO toPaymentDTO(Payment entity){
        if(entity==null) return null;
        return PaymentDTO.builder()
                .id(entity.getId())
                .reservationId(entity.getReservationId())
                .status(entity.getStatus())
                .amount(entity.getAmount())
                .build();
    }

    public static Payment toPayment(PaymentDTO dto){
        if (dto==null) return null;
        return Payment.builder()
                .id(dto.getId())
                .reservationId(dto.getReservationId())
                .status(dto.getStatus())
                .amount(dto.getAmount())
                .build();
    }
}
