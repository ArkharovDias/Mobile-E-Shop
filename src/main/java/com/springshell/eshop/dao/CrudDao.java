package com.springshell.eshop.dao;

public interface CrudDao<T,K> {
    T findById(K id);
    void create(T entity);
    void update(T entity, K id);
    void delete(K id);
}
