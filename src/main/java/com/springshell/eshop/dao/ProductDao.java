package com.springshell.eshop.dao;

import com.springshell.eshop.domain.entity.Product;

import java.util.List;

public interface ProductDao extends CrudDao<Product, Long>{
    List<Product> findAll();
}
