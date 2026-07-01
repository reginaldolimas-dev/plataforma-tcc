package com.br.customerservice.data.dao;

import com.br.customerservice.model.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioDao extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByEmail(String email);
    Optional<UsuarioEntity> findByUsuarioLogin(String usuario);
    boolean existsByEmail(String email);
}
