package com.vdgarcia.reservation_service.cliente;

import com.vdgarcia.reservation_service.cliente.dto.FlightDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "flight-service", url = "${application.config.flight-url}")
public interface FlightClient {

    @GetMapping("/{id}")
    FlightDTO existeVuelo(@PathVariable Long id);

    @GetMapping("/{id}")
    FlightDTO obtenerVuelo(@PathVariable Long id);

}
