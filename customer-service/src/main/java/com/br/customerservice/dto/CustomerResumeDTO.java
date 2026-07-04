package com.br.customerservice.dto;

import java.time.LocalDate;
import java.util.UUID;

public interface CustomerResumeDTO {

    UUID getId();

    String getName();

    String getSurname();

    String getEmail();

    LocalDate getBirthDate();

    boolean getActive();
}
