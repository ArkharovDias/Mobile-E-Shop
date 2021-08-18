package com.springshell.eshop.service.impls;

import com.springshell.eshop.dao.AccountDao;
import com.springshell.eshop.domain.entity.Account;
import com.springshell.eshop.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account findById(Long id) {
        return accountDao.findById(id);
    }

    @Override
    public void create(Account entity) {
        accountDao.create(entity);
    }

    @Override
    public void update(Account entity, Long id) {
        accountDao.update(entity, id);
    }

    @Override
    public void delete(Long id) {
        accountDao.delete(id);
    }

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public Account findByLogin(String login) {
        return accountDao.findByLogin(login);
    }
}
