package com.vdgarcia.customer_service.mapper;

import com.vdgarcia.customer_service.dto.CustomerDTO;
import com.vdgarcia.customer_service.model.Customer;

public class Mapper {

    public static CustomerDTO toCustomerDTO(Customer entity){
        if (entity==null) return null;

        return CustomerDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .lastname(entity.getLastname())
                .email(entity.getEmail())
                .passport(entity.getPassport())
                .phone(entity.getPhone())
                .build();
    }

    public static Customer toCustomer(CustomerDTO dto){
        if (dto==null) return null;
        return Customer.builder()
                .id(dto.getId())
                .name(dto.getName())
                .lastname(dto.getLastname())
                .passport(dto.getPassport())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .build();
    }


    public static void updateCustomer(CustomerDTO dto, Customer entity){
        if (dto==null || entity == null) return;
        entity.setName(dto.getName());
        entity.setLastname(dto.getLastname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPassport(dto.getPassport());
    }


}
