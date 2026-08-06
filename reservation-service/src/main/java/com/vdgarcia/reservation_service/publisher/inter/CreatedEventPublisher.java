package com.vdgarcia.reservation_service.publisher.inter;

import com.vdgarcia.events.ReservationCreada;

public interface CreatedEventPublisher {

    void publishReservationCreated(ReservationCreada event);
}
