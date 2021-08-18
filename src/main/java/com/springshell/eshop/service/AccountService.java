package com.springshell.eshop.service;

import com.springshell.eshop.domain.entity.Account;

import java.util.List;

public interface AccountService extends CrudService<Account, Long>{
    List<Account> findAll();
    Account findByLogin(String login);
}
