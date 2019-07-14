package com.abseliamov.javacoreio.repository;

import java.util.Set;

public interface GenericRepository<T, ID> {

    void add(T t);

    T getById(ID id);

    T getByName(String name);

    Set<T> getAll();

    void delete(ID id);

}
