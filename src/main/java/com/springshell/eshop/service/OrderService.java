package com.springshell.eshop.service;

import com.springshell.eshop.domain.entity.Order;

import java.util.List;

public interface OrderService extends CrudService<Order, Long>{
    List<Order> findAll();
}
