package com.br.customerservice.service;

import com.br.customerservice.data.repository.UsuarioRepository;
import com.br.customerservice.dto.LoginRequest;
import com.br.customerservice.dto.LoginResponse;
import com.br.customerservice.dto.UsuarioResponse;
import com.br.customerservice.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.usuarioLogin(), request.senha())
            );
        } catch (Exception e) {
            throw new BadCredentialsException("Credenciais inválidas");
        }

        var usuario = usuarioRepository.findByUsuarioLogin(request.usuarioLogin())
                .orElseThrow(() -> new BadCredentialsException("Credenciais inválidas"));

        String token = jwtUtil.gerarToken(usuario.getUsuarioLogin());

        var usuarioResponse = new UsuarioResponse(
                usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getUsuarioLogin(), usuario.getRole().name()
        );

        return new LoginResponse(token, usuarioResponse);
    }
}
