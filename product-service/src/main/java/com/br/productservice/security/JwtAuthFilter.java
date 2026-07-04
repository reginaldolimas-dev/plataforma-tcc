package com.br.productservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final ObjectMapper mapper = new ObjectMapper();

    public JwtAuthFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            unauthorized(response, "Token não informado");
            return;
        }

        String token = authHeader.substring(7);

        try {
            var claims = jwtUtil.validateAndGetClaims(token);
            request.setAttribute("clientId", claims.getSubject());
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            unauthorized(response, "Token inválido ou expirado");
        }
    }

    private void unauthorized(HttpServletResponse response, String errorMsg) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        Map<String, Object> body = new HashMap<>();
        body.put("message", "Falha na autenticação");
        body.put("timestamp", Instant.now().toString());
        body.put("elapsed", 0);
        body.put("error", errorMsg);

        response.getWriter().write(mapper.writeValueAsString(body));
    }
}