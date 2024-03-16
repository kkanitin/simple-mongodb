package com.example.simplemongo.repository;

import org.springframework.data.mongodb.core.query.UpdateDefinition;

import java.util.List;

public interface SimpleMongoRepository<T, K> {

    List<T> saveAll(List<T> entities);

    T save(T entity);

    T update(T entity);

    long partialUpdate(T entities, UpdateDefinition updateDefinition);

    long delete(K pk);
}
