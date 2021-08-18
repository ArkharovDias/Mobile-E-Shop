package com.springshell.eshop.service;

public interface CrudService<T,K> {
    T findById(K id);
    void create(T entity);
    void update(T entity, K id);
    void delete(K id);
}
