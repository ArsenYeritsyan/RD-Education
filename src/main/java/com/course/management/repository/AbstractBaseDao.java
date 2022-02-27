package com.course.management.repository;

import java.util.List;
import java.util.UUID;

public interface AbstractBaseDao<T>{
    void save(T t);

    T find(UUID id);

    void delete(UUID id);

    void update(T t);

    List<T> findAll();
}
