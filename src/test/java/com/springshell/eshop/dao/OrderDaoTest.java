package com.springshell.eshop.dao;

import com.springshell.eshop.domain.entity.Order;
import com.springshell.eshop.domain.enums.Status;
import com.springshell.eshop.exceptions.EntityNotFoundException;
import com.springshell.eshop.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class OrderDaoTest {

    @Autowired
    private OrderDao orderDao;

    @Test
    public void testCreateOrderShouldCreateOrder() throws ParseException {
        Order order = Order.builder()
                .orderedDate(DateUtil.convert("July 15, 2021"))
                .shippedDate(DateUtil.convert("August 30, 2021"))
                .status(Status.NEW)
                .totalPrice("123")
                .build();

        orderDao.create(order);

        Assert.assertEquals(orderDao.findById(3L).getTotalPrice(), "123");


    }

    @Test
    public void testUpdateOrderShouldUpdateOrder(){
        Order order = orderDao.findById(2L);
        order.setTotalPrice("500");
        orderDao.update(order, order.getId());

        Assert.assertEquals(orderDao.findById(2L).getTotalPrice(), "500");
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteOrderShouldDeleteOrder() throws ParseException {
        Order order = Order.builder()
                .orderedDate(DateUtil.convert("September 15, 2021"))
                .shippedDate(DateUtil.convert("December 30, 2021"))
                .status(Status.NEW)
                .totalPrice("123")
                .build();
        orderDao.create(order);
        orderDao.delete(4L);
        orderDao.findById(4L);
    }

    @Test
    public void testFindAllOrderShouldFindAllOrders(){
        List<Order> orderList = orderDao.findAll();
        assertThat(orderList).hasSizeGreaterThan(0);
    }
}
