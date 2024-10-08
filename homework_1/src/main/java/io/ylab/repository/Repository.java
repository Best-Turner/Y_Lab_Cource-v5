package io.ylab.repository;

import java.util.Iterator;

public interface Repository<T, K> {

    T save(T t);

    T findById(K id);

    Iterator<T> findAll();

    boolean deleteById(K id);
}
