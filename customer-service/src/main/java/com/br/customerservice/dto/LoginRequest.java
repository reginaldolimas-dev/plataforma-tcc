package com.br.customerservice.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        String email,
        @NotBlank String usuarioLogin,
        @NotBlank String senha
) {}
