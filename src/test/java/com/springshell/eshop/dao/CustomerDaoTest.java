package com.springshell.eshop.dao;

import com.springshell.eshop.domain.entity.Account;
import com.springshell.eshop.domain.entity.Customer;
import com.springshell.eshop.exceptions.EntityNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class CustomerDaoTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testCreateCustomerShouldCreateCustomer(){
        Customer customer = Customer.builder()
                .name("testCustomer")
                .build();

        customerDao.create(customer);

        Assert.assertNotNull(customerDao.findByName("testCustomer"));

    }

    @Test
    public void testUpdateCustomerShouldUpdateCustomer(){
        Customer customer = customerDao.findById(2L);
        customer.setName("nick");
        customerDao.update(customer, customer.getId());

        Assert.assertEquals(customerDao.findById(2L).getName(), "nick");
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteCustomerShouldDeleteCustomer(){
        Customer customer = Customer.builder()
                .name("testCustomerForDelete")
                .build();
        customerDao.create(customer);
        Customer customerForDelete = customerDao.findByName("testCustomerForDelete");
        customerDao.delete(customerForDelete.getId());
        customerDao.findById(customer.getId());
    }

    @Test
    public void testFindAllCustomerShouldFindAllCustomers(){
        List<Customer> customerList = customerDao.findAll();
        assertThat(customerList).hasSizeGreaterThan(0);
    }

}
