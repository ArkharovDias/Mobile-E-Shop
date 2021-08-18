package com.springshell.eshop.service.impls;

import com.springshell.eshop.dao.MobileDao;
import com.springshell.eshop.domain.entity.Mobile;
import com.springshell.eshop.service.MobileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MobileServiceImpl implements MobileService {

    private final MobileDao mobileDao;

    public MobileServiceImpl(MobileDao mobileDao) {
        this.mobileDao = mobileDao;
    }

    @Override
    public Mobile findById(Long id) {
        return mobileDao.findById(id);
    }

    @Override
    public void create(Mobile entity) {
        mobileDao.create(entity);
    }

    @Override
    public void update(Mobile entity, Long id) {
        mobileDao.update(entity, id);
    }

    @Override
    public void delete(Long id) {
        mobileDao.delete(id);
    }

    @Override
    public List<Mobile> findAll() {
        return mobileDao.findAll();
    }

    @Override
    public Mobile findByName(String name) {
        return mobileDao.findByName(name);
    }
}
