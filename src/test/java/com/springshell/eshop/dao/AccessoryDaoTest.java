package com.springshell.eshop.dao;

import com.springshell.eshop.domain.entity.Accessory;
import com.springshell.eshop.domain.enums.Color;
import com.springshell.eshop.domain.enums.Size;
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
public class AccessoryDaoTest {
    
    @Autowired
    private AccessoryDao accessoryDao;

    @Test
    public void testCreateAccessoryShouldCreateAccessory() throws ParseException {
        Accessory accessory = Accessory.builder()
                .name("testAccessory1")
                .description("testAccessory")
                .price(7635d)
                .color(Color.YELLOW)
                .size(Size.BIG)
                .build();

        accessoryDao.create(accessory);

        Assert.assertEquals(accessoryDao.findByName("testAccessory1").getDescription(), "testAccessory");


    }

    @Test
    public void testUpdateAccessoryShouldUpdateAccessory(){
        Accessory accessory = accessoryDao.findByName("accessoryName2");
        accessory.setPrice(7983d);
        accessoryDao.update(accessory, accessory.getId());

        Assert.assertEquals(accessoryDao.findByName("accessoryName2").getPrice(), Double.valueOf(7983d));
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteAccessoryShouldDeleteAccessory() throws ParseException {
        Accessory accessory = Accessory.builder()
                .name("testAccessoryName2")
                .description("testAccessory2")
                .price(852d)
                .build();
        accessoryDao.create(accessory);
        Accessory accessoryForDelete = accessoryDao.findByName("testAccessoryName2");
        accessoryDao.delete(accessoryForDelete.getId());
        accessoryDao.findByName(accessoryForDelete.getName());
    }

    @Test
    public void testFindAllAccessoryShouldFindAllAccessories(){
        List<Accessory> accessoryList = accessoryDao.findAll();
        assertThat(accessoryList).hasSizeGreaterThan(0);
    }
}
