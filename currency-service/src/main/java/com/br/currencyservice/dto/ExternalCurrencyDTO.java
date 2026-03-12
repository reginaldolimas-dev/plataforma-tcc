package com.br.currencyservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExternalCurrencyDTO {
    private String code;

    @JsonProperty("bid")
    private String bid;

    @JsonProperty("create_date")
    private String createDate;
}
