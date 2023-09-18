package ru.otus.homework.core.repository;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface DataTemplate<T> {
    Optional<T> findById(Connection connection, String id);
    List<T> findAll(Connection connection);
    String insert(Connection connection, T object);
    void update(Connection connection, T object);
}
