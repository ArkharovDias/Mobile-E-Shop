package com.springshell.eshop.service;

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
public class CustomerServiceTest {
    
    @Autowired
    private CustomerService customerService;

    @Test
    public void testCreateCustomerShouldCreateCustomer(){
        Customer customer = Customer.builder()
                .name("testServiceCustomer")
                .build();

        customerService.create(customer);

        Assert.assertNotNull(customerService.findByName("testServiceCustomer"));

    }

    @Test
    public void testUpdateCustomerShouldUpdateCustomer(){
        Customer customer = customerService.findById(2L);
        customer.setName("pasha");
        customerService.update(customer, customer.getId());

        Assert.assertEquals(customerService.findById(2L).getName(), "pasha");
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteCustomerShouldDeleteCustomer(){
        Customer customer = Customer.builder()
                .name("testServiceCustomerForDelete")
                .build();
        customerService.create(customer);
        Customer customerForDelete = customerService.findByName("testServiceCustomerForDelete");
        customerService.delete(customerForDelete.getId());
        customerService.findById(customerForDelete.getId());
    }

    @Test
    public void testFindAllCustomerShouldFindAllCustomers(){
        List<Customer> customerList = customerService.findAll();
        assertThat(customerList).hasSizeGreaterThan(0);
    }
}
