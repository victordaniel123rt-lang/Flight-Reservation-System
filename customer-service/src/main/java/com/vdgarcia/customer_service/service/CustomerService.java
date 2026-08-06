package com.vdgarcia.customer_service.service;

import com.vdgarcia.customer_service.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> obtenerTodos();
    CustomerDTO obtenerPorId(Long id);
    CustomerDTO crear(CustomerDTO dto);
    CustomerDTO actualizar(Long id, CustomerDTO dto);
    CustomerDTO eliminar(Long id);

}
