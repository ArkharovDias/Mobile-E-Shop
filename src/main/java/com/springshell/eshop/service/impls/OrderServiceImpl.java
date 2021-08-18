package com.springshell.eshop.service.impls;

import com.springshell.eshop.dao.OrderDao;
import com.springshell.eshop.domain.entity.Order;
import com.springshell.eshop.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Order findById(Long id) {
        return orderDao.findById(id);
    }

    @Override
    public void create(Order entity) {
        orderDao.create(entity);
    }

    @Override
    public void update(Order entity, Long id) {
        orderDao.update(entity, id);
    }

    @Override
    public void delete(Long id) {
        orderDao.delete(id);
    }

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }
}
