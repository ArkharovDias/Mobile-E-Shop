package com.springshell.eshop.commands;

import com.springshell.eshop.domain.dto.CustomerDto;
import com.springshell.eshop.domain.entity.Account;
import com.springshell.eshop.domain.entity.Customer;
import com.springshell.eshop.service.AccountService;
import com.springshell.eshop.service.CustomerService;
import org.jline.reader.LineReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
public class CustomerShellCommands {

    @Autowired
    @Lazy
    private LineReader lineReader;
    
    private final CustomerService customerService;
    private final AccountService accountService;

    public CustomerShellCommands(CustomerService customerService, AccountService accountService) {
        this.customerService = customerService;
        this.accountService = accountService;
    }

    @ShellMethod(key = "find-by-id-customer", value = "find customer by id")
    public CustomerDto findById(@ShellOption({"-i, -id"}) Long id) {
        return CustomerDto.from(customerService.findById(id));
    }

    @ShellMethod(key = "find-by-name-customer", value = "find customer by name")
    public CustomerDto findByName(@ShellOption({"-n, -name"}) String name) {
        return CustomerDto.from(customerService.findByName(name));
    }

    @ShellMethod(key = "save-customer", value = "save customer")
    public void save() {
        String name = lineReader.readLine("name" + ": ");
        String accountLogin = lineReader.readLine("login" + ": ");
        Account account = accountService.findByLogin(accountLogin);
        Customer customer = Customer.builder()
                .name(name)
                .account(account)
                .build();
        customerService.create(customer);
    }

    @ShellMethod(key = "update-customer", value = "update customer")
    public void updateCustomer(@ShellOption({"-i, -id"}) Long id){
        String name = lineReader.readLine("name" + ": ");

        Customer customer = Customer.builder()
                .name(name)
                .build();

        customerService.update(customer, id);
    }

    @ShellMethod(key = "delete-customer", value = "delete customer by id")
    public void delete(@ShellOption({"-i", "-id"}) Long id) {
        customerService.delete(id);
    }

    @ShellMethod(key = "list-customer", value = "show all customers")
    public List<CustomerDto> findAll(){
        return customerService.findAll().stream()
                .map(CustomerDto::from)
                .collect(Collectors.toList());
    }

}
