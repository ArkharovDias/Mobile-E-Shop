package com.springshell.eshop.service;

import com.springshell.eshop.domain.entity.Mobile;

import java.util.List;

public interface MobileService extends CrudService<Mobile, Long>{
    List<Mobile> findAll();
    Mobile findByName(String name);
}
