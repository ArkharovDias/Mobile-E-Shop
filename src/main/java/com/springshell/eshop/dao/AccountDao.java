package com.springshell.eshop.dao;

import com.springshell.eshop.domain.entity.Account;

import java.util.List;

public interface AccountDao extends CrudDao<Account, Long>{
    List<Account> findAll();
    Account findByLogin(String login);
}
