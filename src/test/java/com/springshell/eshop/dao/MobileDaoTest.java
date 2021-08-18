package com.springshell.eshop.dao;

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
public class MobileDaoTest {

    @Autowired
    private MobileDao mobileDao;

    @Test
    public void testCreateMobileShouldCreateMobile() throws ParseException {
        Mobile mobile = Mobile.builder()
                .name("testMobile1")
                .description("testMobile7")
                .price(761d)
                .model("Galaxy S6")
                .brand("Samsung")
                .build();

        mobileDao.create(mobile);

        Assert.assertEquals(mobileDao.findByName("testMobile1").getDescription(), "testMobile7");


    }

    @Test
    public void testUpdateMobileShouldUpdateMobile() {
        Mobile mobile = mobileDao.findByName("mobileName2");
        mobile.setPrice(456d);
        mobileDao.update(mobile, mobile.getId());

        Assert.assertEquals(mobileDao.findByName("mobileName2").getPrice(), Double.valueOf(456d));
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteMobileShouldDeleteMobile() throws ParseException {
        Mobile mobile = Mobile.builder()
                .name("testMobile2")
                .description("testMobile2")
                .price(852d)
                .build();
        mobileDao.create(mobile);
        Mobile mobileForDelete = mobileDao.findByName("testMobile2");
        mobileDao.delete(mobileForDelete.getId());
        mobileDao.findById(mobileForDelete.getId());
    }

    @Test
    public void testFindAllMobileShouldFindAllMobiles() {
        List<Mobile> mobileList = mobileDao.findAll();
        assertThat(mobileList).hasSizeGreaterThan(0);
    }
}
