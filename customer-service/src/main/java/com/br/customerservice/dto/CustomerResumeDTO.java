package com.br.customerservice.dto;

import java.time.LocalDate;

public interface CustomerResumeDTO {

    Long getId();

    String getName();

    String getSurname();

    String getEmail();

    LocalDate getBirthDate();

    boolean getActive();
}
