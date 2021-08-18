package com.springshell.eshop.service.impls;

import com.springshell.eshop.dao.CustomerDao;
import com.springshell.eshop.domain.entity.Customer;
import com.springshell.eshop.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public Customer findById(Long id) {
        return customerDao.findById(id);
    }

    @Override
    public void create(Customer entity) {
        customerDao.create(entity);
    }

    @Override
    public void update(Customer entity, Long id) {
        customerDao.update(entity, id);
    }

    @Override
    public void delete(Long id) {
        customerDao.delete(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public Customer findByName(String name) {
        return customerDao.findByName(name);
    }
}
