package com.springshell.eshop.dao;

import com.springshell.eshop.domain.entity.Order;

import java.util.List;

public interface OrderDao extends CrudDao<Order, Long>{
    List<Order> findAll();
}
