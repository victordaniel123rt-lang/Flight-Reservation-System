package com.vdgarcia.customer_service.service;

import com.vdgarcia.customer_service.dto.CustomerDTO;
import com.vdgarcia.customer_service.mapper.Mapper;
import com.vdgarcia.customer_service.model.Customer;
import com.vdgarcia.customer_service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository repository;

    @Override
    public List<CustomerDTO> obtenerTodos() {
        return repository.findAll().stream().map(Mapper::toCustomerDTO).toList();
    }

    @Override
    public CustomerDTO obtenerPorId(Long id) {
        Customer customer = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Cliente no encontrado")
        );
        return Mapper.toCustomerDTO(customer);
    }

    @Override
    public CustomerDTO crear(CustomerDTO dto) {
        Customer customer = Mapper.toCustomer(dto);
        Customer guardado = repository.save(customer);
        return Mapper.toCustomerDTO(guardado);
    }

    @Override
    public CustomerDTO actualizar(Long id, CustomerDTO dto) {
        Customer customer = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Cliente no encontrado")
        );
        Mapper.updateCustomer(dto,customer);
        Customer actualizado = repository.save(customer);
        return Mapper.toCustomerDTO(actualizado);
    }

    @Override
    public CustomerDTO eliminar(Long id) {
        Customer eliminar = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Cliente no encontrado")
        );
        repository.delete(eliminar);
        return Mapper.toCustomerDTO(eliminar);
    }
}
