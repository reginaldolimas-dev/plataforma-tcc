package com.br.currencyservice.data.repository;

import com.br.currencyservice.data.dao.CurrencyDao;
import com.br.currencyservice.dto.CurrencyResponseDTO;
import com.br.currencyservice.model.entity.CurrencyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class CurrencyRepository {
    private final CurrencyDao dao;

    public List<CurrencyResponseDTO> getAll() {
        return null;
    }

    public long countUpdatedToday(Set<String> supportedCodes, LocalDateTime startOfDay) {
        return dao.countUpdatedToday(supportedCodes, startOfDay);
    }

    public void save(CurrencyEntity entity) {
        dao.save(entity);
    }

    public Optional<CurrencyEntity> findById(String code) {
        return dao.findById(code);
    }

    public Collection<CurrencyEntity> findByCodeIn(Set<String> supportedCodes) {
        return dao.findByCodeIn(supportedCodes);
    }
}