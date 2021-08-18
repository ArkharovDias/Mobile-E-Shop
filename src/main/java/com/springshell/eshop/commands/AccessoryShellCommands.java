package com.springshell.eshop.commands;

import com.springshell.eshop.domain.dto.AccessoryDto;
import com.springshell.eshop.domain.entity.Accessory;
import com.springshell.eshop.domain.entity.Order;
import com.springshell.eshop.domain.enums.Color;
import com.springshell.eshop.domain.enums.Size;
import com.springshell.eshop.service.AccessoryService;
import com.springshell.eshop.service.OrderService;
import org.jline.reader.LineReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
public class AccessoryShellCommands {

    @Autowired
    @Lazy
    private LineReader lineReader;

    private final AccessoryService accessoryService;

    private final OrderService orderService;

    public AccessoryShellCommands(AccessoryService accessoryService, OrderService orderService) {
        this.accessoryService = accessoryService;
        this.orderService = orderService;
    }


    @ShellMethod(key = "find-by-id-accessory", value = "find accessory by id")
    public AccessoryDto findById(@ShellOption({"-i, -id"}) Long id) {
        return AccessoryDto.from(accessoryService.findById(id));
    }

    @ShellMethod(key = "find-by-name-accessory", value = "find accessory by name")
    public AccessoryDto findByName(@ShellOption({"-n, -name"}) String name) {
        return AccessoryDto.from(accessoryService.findByName(name));
    }

    @ShellMethod(key = "save-accessory", value = "save accessory")
    public void save() {
        String description = lineReader.readLine("description" + ": ");
        String price = lineReader.readLine("price" + ": ");
        String name = lineReader.readLine("name" + ": ");
        String color = lineReader.readLine("color" + ": ");
        String size = lineReader.readLine("size" + ": ");
        String orderId = lineReader.readLine("order id" + ": ");

        Order order = orderService.findById(Long.valueOf(orderId));

        Accessory accessory = Accessory.builder()
                .description(description)
                .price(Double.valueOf(price))
                .name(name)
                .color(Color.valueOf(color))
                .size(Size.valueOf(size))
                .order(order)
                .build();

        accessoryService.create(accessory);
    }

    @ShellMethod(key = "update-accessory", value = "update accessory")
    public void updateAccessory(@ShellOption({"-i, -id"}) Long id){
        String description = lineReader.readLine("description" + ": ");
        String price = lineReader.readLine("price" + ": ");
        String name = lineReader.readLine("name" + ": ");
        String color = lineReader.readLine("color" + ": ");
        String size = lineReader.readLine("size" + ": ");

        Accessory accessory = Accessory.builder()
                .description(description)
                .price(Double.valueOf(price))
                .name(name)
                .color(Color.valueOf(color))
                .size(Size.valueOf(size))
                .build();

        accessoryService.update(accessory, id);
    }

    @ShellMethod(key = "delete-accessory", value = "delete accessory by id")
    public void delete(@ShellOption({"-i", "-id"}) Long id) {
        accessoryService.delete(id);
    }

    @ShellMethod(key = "list-accessory", value = "show all accessories")
    public List<AccessoryDto> findAll(){
        return accessoryService.findAll().stream()
                .map(AccessoryDto::from)
                .collect(Collectors.toList());
    }
}
