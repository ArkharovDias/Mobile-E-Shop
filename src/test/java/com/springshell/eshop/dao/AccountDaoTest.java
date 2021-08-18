package com.springshell.eshop.dao;

import com.springshell.eshop.domain.entity.Account;
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
public class AccountDaoTest {

    @Autowired
    private AccountDao accountDao;

    @Test
    public void testCreateAccountShouldCreateAccount(){

        Account account = Account.builder()
                .name("masha")
                .surname("mashova")
                .login("masha")
                .password("999")
                .email("masha288")
                .phoneNumber("89869074943")
                .build();

        accountDao.create(account);

        Assert.assertNotNull(accountDao.findByLogin("masha"));
    }

    @Test
    public void testUpdateAccountShouldUpdateAccount(){
        Account account = accountDao.findByLogin("dias");
        account.setPassword("159");
        accountDao.update(account, account.getId());

        Assert.assertEquals(accountDao.findByLogin("dias").getPassword(), "159");
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteAccountShouldDeleteAccount(){
        Account account = accountDao.findByLogin("ivan");
        accountDao.delete(account.getId());

        accountDao.findByLogin("ivan");
    }

    @Test
    public void testFindAccountByLoginShouldFindAccount(){
        Assert.assertNotNull(accountDao.findByLogin("masha"));
    }

    @Test
    public void testFindAllAccountShouldFindAllAccounts(){
        List<Account> accountList = accountDao.findAll();
        assertThat(accountList).hasSizeGreaterThan(0);
    }


}
