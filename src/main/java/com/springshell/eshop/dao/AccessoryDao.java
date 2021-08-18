package com.springshell.eshop.dao;

import com.springshell.eshop.domain.entity.Accessory;

import java.util.List;

public interface AccessoryDao extends CrudDao<Accessory, Long> {
    List<Accessory> findAll();
    Accessory findByName(String name);
}
