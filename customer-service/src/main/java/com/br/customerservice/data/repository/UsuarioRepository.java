package com.br.customerservice.data.repository;

import com.br.customerservice.data.dao.UsuarioDao;
import com.br.customerservice.model.entity.UsuarioEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class UsuarioRepository {

    private final UsuarioDao dao;

    public Optional<UsuarioEntity> findByUsuarioLogin(String username) {
        return dao.findByUsuarioLogin(username);
    }

    public boolean existsById(Long id) {
        return dao.existsById(id);
    }

    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    public boolean existsByEmail(@NotBlank @Email String email) {
        return dao.existsByEmail(email);
    }

    public Optional<UsuarioEntity> findById(Long id) {
        return dao.findById(id);
    }

    public List<UsuarioEntity> findAll() {
        return dao.findAll();
    }

    public UsuarioEntity save(UsuarioEntity usuario) {
        return dao.save(usuario);
    }
}
