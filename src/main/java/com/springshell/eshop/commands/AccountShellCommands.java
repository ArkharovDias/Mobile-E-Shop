package com.springshell.eshop.commands;

import com.springshell.eshop.domain.dto.AccountDto;
import com.springshell.eshop.domain.entity.Account;
import com.springshell.eshop.domain.entity.Customer;
import com.springshell.eshop.service.AccountService;
import org.jline.reader.LineReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
public class AccountShellCommands {

    private final AccountService accountService;

    @Autowired
    @Lazy
    private LineReader lineReader;

    public AccountShellCommands(AccountService accountService) {
        this.accountService = accountService;
    }

    @ShellMethod(key = "find-by-id-account", value = "find Account by id")
    public AccountDto findById(@ShellOption({"-i, -id"}) Long id) {
        return AccountDto.from(accountService.findById(id));
    }

    @ShellMethod(key = "save-account", value = "save account")
    public void save() {
        String name = lineReader.readLine("name" + ": ");
        String surname = lineReader.readLine("surname" + ": ");
        String login = lineReader.readLine("login" + ": ");
        String password = lineReader.readLine("password" + ": ");
        String email = lineReader.readLine("email" + ": ");
        String phoneNumber = lineReader.readLine("phone number" + ": ");

        Account account = Account.builder()
                .login(login)
                .name(name)
                .surname(surname)
                .email(email)
                .password(password)
                .customer(Customer.builder().build())
                .phoneNumber(phoneNumber)
                .build();

        accountService.create(account);

    }

    @ShellMethod(key = "update-account", value = "update account")
    public void updateAccount(@ShellOption({"-i, -id"}) Long id){
        String name = lineReader.readLine("name" + ": ");
        String login = lineReader.readLine("login" + ": ");
        String surname = lineReader.readLine("surname" + ": ");
        String password = lineReader.readLine("password" + ": ");
        String email = lineReader.readLine("email" + ": ");
        String phoneNumber = lineReader.readLine("phone number" + ": ");

        Account account = Account.builder()
                .login(login)
                .name(name)
                .surname(surname)
                .email(email)
                .password(password)
                .customer(Customer.builder().build())
                .phoneNumber(phoneNumber)
                .build();

        accountService.update(account, id);
    }

    @ShellMethod(key = "delete-account", value = "delete account by id")
    public void delete(@ShellOption({"-i", "-id"}) Long id) {
        accountService.delete(id);
    }

    @ShellMethod(key = "list-account", value = "show all accounts")
    public List<AccountDto> findAll(){
        return accountService.findAll().stream()
                .map(AccountDto::from)
                .collect(Collectors.toList());
    }

    @ShellMethod(key = "find-by-login-account", value = "find account by login")
    public AccountDto findByLogin(@ShellOption({"-l", "-login"}) String login){
        return AccountDto.from(accountService.findByLogin(login));
    }

}
