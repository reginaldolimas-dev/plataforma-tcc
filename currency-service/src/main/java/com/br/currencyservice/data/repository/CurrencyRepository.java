package com.br.currencyservice.data.repository;

import com.br.currencyservice.model.entity.CurrencyEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class CurrencyRepository {

    private static final Logger log = LoggerFactory.getLogger(CurrencyRepository.class);

    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private final ReentrantLock lock = new ReentrantLock();

    @Value("${currency.storage.path:./data/currencies.json}")
    private String storagePath;

    private Map<String, CurrencyEntity> loadAll() {
        File file = new File(storagePath);
        if (!file.exists()) {
            return new HashMap<>();
        }
        try {
            CurrencyEntity[] entities = mapper.readValue(file, CurrencyEntity[].class);
            Map<String, CurrencyEntity> map = new HashMap<>();
            for (CurrencyEntity e : entities) {
                map.put(e.getCode(), e);
            }
            return map;
        } catch (IOException e) {
            log.error("Failed to read currency storage file: {}", e.getMessage());
            return new HashMap<>();
        }
    }

    private void persist(Map<String, CurrencyEntity> data) {
        try {
            File file = new File(storagePath);
            file.getParentFile().mkdirs();
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, data.values());
        } catch (IOException e) {
            log.error("Failed to write currency storage file: {}", e.getMessage());
            throw new IllegalStateException("Could not persist currency data", e);
        }
    }

    public Optional<CurrencyEntity> findById(String code) {
        lock.lock();
        try {
            return Optional.ofNullable(loadAll().get(code));
        } finally {
            lock.unlock();
        }
    }

    public List<CurrencyEntity> findByCodeIn(Collection<String> codes) {
        lock.lock();
        try {
            Map<String, CurrencyEntity> all = loadAll();
            List<CurrencyEntity> result = new ArrayList<>();
            for (String code : codes) {
                CurrencyEntity entity = all.get(code);
                if (entity != null) {
                    result.add(entity);
                }
            }
            return result;
        } finally {
            lock.unlock();
        }
    }

    public long countUpdatedToday(Collection<String> codes, LocalDateTime startOfDay) {
        lock.lock();
        try {
            Map<String, CurrencyEntity> all = loadAll();
            return codes.stream()
                    .map(all::get)
                    .filter(Objects::nonNull)
                    .filter(e -> e.getUpdatedAt() != null && !e.getUpdatedAt().isBefore(startOfDay))
                    .count();
        } finally {
            lock.unlock();
        }
    }

    public CurrencyEntity save(CurrencyEntity entity) {
        lock.lock();
        try {
            Map<String, CurrencyEntity> all = loadAll();
            all.put(entity.getCode(), entity);
            persist(all);
            return entity;
        } finally {
            lock.unlock();
        }
    }
}