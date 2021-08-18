package com.springshell.eshop.service.impls;

import com.springshell.eshop.dao.AccessoryDao;
import com.springshell.eshop.domain.entity.Accessory;
import com.springshell.eshop.service.AccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessoryServiceImpl implements AccessoryService {

    @Autowired
    private final AccessoryDao accessoryDao;

    public AccessoryServiceImpl(AccessoryDao accessoryDao) {
        this.accessoryDao = accessoryDao;
    }

    @Override
    public List<Accessory> findAll() {
        return accessoryDao.findAll();
    }

    @Override
    public Accessory findByName(String name) {
        return accessoryDao.findByName(name);
    }

    @Override
    public Accessory findById(Long id) {
        return accessoryDao.findById(id);
    }

    @Override
    public void create(Accessory entity) {
        accessoryDao.create(entity);
    }

    @Override
    public void update(Accessory entity, Long id) {
        accessoryDao.update(entity, id);
    }

    @Override
    public void delete(Long id) {
        accessoryDao.delete(id);
    }
}
