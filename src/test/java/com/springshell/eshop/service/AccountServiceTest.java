package com.springshell.eshop.service;

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
public class AccountServiceTest {
    
    @Autowired
    private AccountService accountService;

    @Test
    public void testCreateAccountShouldCreateAccount(){

        Account account = Account.builder()
                .name("kiril")
                .surname("kirilov")
                .login("kiril")
                .password("9996349")
                .email("kiril288")
                .phoneNumber("83390749435")
                .build();

        accountService.create(account);

        Assert.assertNotNull(accountService.findByLogin("kiril"));
    }

    @Test
    public void testUpdateAccountShouldUpdateAccount(){
        Account account = accountService.findByLogin("airat");
        account.setPassword("759");
        accountService.update(account, account.getId());

        Assert.assertEquals(accountService.findByLogin("airat").getPassword(), "759");
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteAccountShouldDeleteAccount(){
        Account account = accountService.findByLogin("sveta");
        accountService.delete(account.getId());

        accountService.findByLogin("sveta");
    }

    @Test
    public void testFindAccountByLoginShouldFindAccount(){
        Assert.assertNotNull(accountService.findByLogin("airat"));
    }

    @Test
    public void testFindAllAccountShouldFindAllAccounts(){
        List<Account> accountList = accountService.findAll();
        assertThat(accountList).hasSizeGreaterThan(0);
    }
    
}
