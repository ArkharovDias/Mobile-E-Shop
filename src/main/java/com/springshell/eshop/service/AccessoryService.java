package com.springshell.eshop.service;

import com.springshell.eshop.domain.entity.Accessory;

import java.util.List;

public interface AccessoryService extends CrudService<Accessory, Long>{
    List<Accessory> findAll();
    Accessory findByName(String name);
}
