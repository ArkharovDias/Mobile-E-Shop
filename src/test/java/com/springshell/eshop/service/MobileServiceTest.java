package com.springshell.eshop.service;

import com.springshell.eshop.domain.entity.Mobile;
import com.springshell.eshop.exceptions.EntityNotFoundException;
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
public class MobileServiceTest {

    @Autowired
    private MobileService mobileService;

    @Test
    public void testCreateMobileShouldCreateMobile() throws ParseException {
        Mobile mobile = Mobile.builder()
                .name("testServiceMobile1")
                .description("testServiceMobile1")
                .price(761d)
                .model("Galaxy S8")
                .brand("Samsung")
                .build();

        mobileService.create(mobile);

        Assert.assertEquals(mobileService.findByName("testServiceMobile1").getDescription(), "testServiceMobile1");


    }

    @Test
    public void testUpdateMobileShouldUpdateMobile() {
        Mobile mobile = mobileService.findByName("mobileName3");
        mobile.setPrice(456d);
        mobileService.update(mobile, mobile.getId());

        Assert.assertEquals(mobileService.findByName("mobileName3").getPrice(), Double.valueOf(456d));
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteMobileShouldDeleteMobile() throws ParseException {
        Mobile mobile = Mobile.builder()
                .name("testServiceMobile2")
                .description("testServiceMobile2")
                .price(852d)
                .build();
        mobileService.create(mobile);
        Mobile mobileForDelete = mobileService.findByName("testServiceMobile2");
        mobileService.delete(mobileForDelete.getId());
        mobileService.findById(mobileForDelete.getId());
    }

    @Test
    public void testFindAllMobileShouldFindAllMobiles() {
        List<Mobile> mobileList = mobileService.findAll();
        assertThat(mobileList).hasSizeGreaterThan(0);
    }
}
