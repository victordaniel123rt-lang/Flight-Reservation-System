package com.vdgarcia.reservation_service.cliente.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {
    private Long id;
    private String name;
    private String lastname;
    private String email;
    private String phone;
    private String passport;
}
