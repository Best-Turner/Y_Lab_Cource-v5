package io.ylab.repository;

import java.util.List;

public interface Repository<T, K> {

    T save(T t);

    T findById(K id);

    List<T> findAll();

    boolean deleteById(K id);
}
