package com.br.currencyservice.data.dao;

import com.br.currencyservice.model.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface CurrencyDao extends JpaRepository<CurrencyEntity, String> {

    @Query("""
        select count(c)
        from CurrencyEntity c
        where c.code in :codes
          and c.updatedAt >= :startOfDay
    """)
    long countUpdatedToday(Set<String> codes, LocalDateTime startOfDay);

    List<CurrencyEntity> findByCodeIn(Collection<String> codes);
}
