package com.vdgarcia.customer_service.dto;

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
