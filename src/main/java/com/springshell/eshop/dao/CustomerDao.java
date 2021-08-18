package com.springshell.eshop.dao;

import com.springshell.eshop.domain.entity.Customer;

import java.util.List;

public interface CustomerDao extends CrudDao<Customer, Long>{
    List<Customer> findAll();
    Customer findByName(String name);
}
