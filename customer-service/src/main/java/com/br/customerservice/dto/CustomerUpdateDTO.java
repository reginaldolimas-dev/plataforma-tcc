package com.br.customerservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CustomerUpdateDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private LocalDate birthDate;
    private Boolean active;
}
