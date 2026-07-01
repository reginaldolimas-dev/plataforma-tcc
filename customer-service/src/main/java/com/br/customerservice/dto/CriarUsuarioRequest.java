package com.br.customerservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CriarUsuarioRequest(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank String usuarioLogin,
        @NotBlank @Size(min = 3, message = "A senha deve ter no mínimo 3 caracteres") String senha
) {}
