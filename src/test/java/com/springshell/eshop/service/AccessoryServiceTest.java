package com.springshell.eshop.service;

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
public class AccessoryServiceTest {
    
    @Autowired
    private AccessoryService accessoryService;

    @Test
    public void testCreateAccessoryShouldCreateAccessory() throws ParseException {
        Accessory accessory = Accessory.builder()
                .name("testServiceAccessory1")
                .description("testServiceAccessory1")
                .price(1649d)
                .color(Color.GREY)
                .size(Size.SMALL)
                .build();

        accessoryService.create(accessory);

        Assert.assertEquals(accessoryService.findByName("testServiceAccessory1").getDescription(), "testServiceAccessory1");


    }

    @Test
    public void testUpdateAccessoryShouldUpdateAccessory(){
        Accessory accessory = accessoryService.findByName("accessoryName3");
        accessory.setPrice(7983d);
        accessoryService.update(accessory, accessory.getId());

        Assert.assertEquals(accessoryService.findByName("accessoryName3").getPrice(), Double.valueOf(7983d));
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteAccessoryShouldDeleteAccessory() throws ParseException {
        Accessory accessory = Accessory.builder()
                .name("testServiceAccessoryName2")
                .description("testServiceAccessoryName2")
                .price(6952d)
                .build();
        accessoryService.create(accessory);
        Accessory accessoryForDelete = accessoryService.findByName("testServiceAccessoryName2");
        accessoryService.delete(accessoryForDelete.getId());
        accessoryService.findByName(accessoryForDelete.getName());
    }

    @Test
    public void testFindAllAccessoryShouldFindAllAccessories(){
        List<Accessory> accessoryList = accessoryService.findAll();
        assertThat(accessoryList).hasSizeGreaterThan(0);
    }
}
