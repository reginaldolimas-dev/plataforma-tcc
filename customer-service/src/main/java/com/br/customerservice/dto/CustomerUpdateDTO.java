package com.br.customerservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class CustomerUpdateDTO {
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private LocalDate birthDate;
    private Boolean active;
}
