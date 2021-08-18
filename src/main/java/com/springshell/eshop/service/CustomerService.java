package com.springshell.eshop.service;

import com.springshell.eshop.domain.entity.Customer;

import java.util.List;

public interface CustomerService extends CrudService<Customer, Long>{
    List<Customer> findAll();
    Customer findByName(String name);
}
