package com.vdgarcia.reservation_service.cliente;

import com.vdgarcia.reservation_service.cliente.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "${application.config.customer-url}")
public interface CustomerClient {

    @GetMapping("/{id}")
    CustomerDTO existeCliente(@PathVariable  Long id);
}
