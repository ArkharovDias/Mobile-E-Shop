package com.springshell.eshop.service;

import com.springshell.eshop.domain.entity.Product;

import java.util.List;

public interface ProductService extends CrudService<Product, Long>{
    List<Product> findAll();
}
