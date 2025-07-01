package org.example.repository;

import org.example.domain.Match;

import java.util.List;

public interface IRepository<T, ID> {
    Match add(T entity);
    List<T> getAll();
    T getById(ID id);
    Match update(ID id, T entity);
    void delete(ID id);
}
