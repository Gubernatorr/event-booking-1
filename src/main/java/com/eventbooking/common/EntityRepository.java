package com.eventbooking.common;

import java.util.List;
import java.util.Optional;

/**
 * Абстракція сховища сутностей. Сервіси залежать від неї, а не від реалізації:
 * сьогодні in-memory, на тижні 3 - PostgreSQL, і код сервісів не зміниться.
 */
public interface EntityRepository<T extends Identifiable<I>, I> {

    T save(T entity);

    Optional<T> findById(I id);

    List<T> findAll();

    /** @return true, якщо щось було видалено */
    boolean deleteById(I id);
}