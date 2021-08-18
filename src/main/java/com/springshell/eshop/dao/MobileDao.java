package com.springshell.eshop.dao;

import com.springshell.eshop.domain.entity.Mobile;
import com.springshell.eshop.service.CrudService;

import java.util.List;

public interface MobileDao extends CrudService<Mobile, Long> {
    List<Mobile> findAll();
    Mobile findByName(String name);
}
