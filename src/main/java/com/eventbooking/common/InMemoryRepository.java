package com.eventbooking.common;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Спільна in-memory реалізація CRUD для будь-якої сутності.
 * Конкретні репозиторії лише фіксують типи і додають свої запити.
 */
public abstract class InMemoryRepository<T extends Identifiable<I>, I>
        implements EntityRepository<T, I> {

    private final Map<I, T> storage = new ConcurrentHashMap<>();

    @Override
    public T save(T entity) {
        storage.put(entity.id(), entity);
        return entity;
    }

    @Override
    public Optional<T> findById(I id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<T> findAll() {
        return List.copyOf(storage.values());
    }

    @Override
    public boolean deleteById(I id) {
        return storage.remove(id) != null;
    }
}