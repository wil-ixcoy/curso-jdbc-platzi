package org.example.repository;

import org.example.model.EmployeeEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    List<T> findAll() throws SQLException;

    T getById(Integer id) throws SQLException;

    void save(T t) throws SQLException;

    void update(Optional<T> t, Integer id) throws SQLException;

    void delete(Integer id) throws SQLException;
}
