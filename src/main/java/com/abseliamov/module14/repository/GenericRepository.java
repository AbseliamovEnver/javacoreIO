package com.abseliamov.module14.repository;

import java.util.Set;

public interface GenericRepository<T, ID> {

    void add(T t);

    T getById(ID id);

    T getByName(String name);

    Set<T> getAll();

    T delete(ID id);

}
