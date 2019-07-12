package com.abseliamov.module14.controller;

import java.util.Set;

public interface GenericInterface<T> {
    void add(T t);

    void update(T t);

    void delete(long id);

    T getItemById(long id);

    Set<T> listItem();
}
