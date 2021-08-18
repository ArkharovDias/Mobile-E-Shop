package com.springshell.eshop.commands;

import com.springshell.eshop.domain.dto.MobileDto;
import com.springshell.eshop.domain.entity.Account;
import com.springshell.eshop.domain.entity.Mobile;
import com.springshell.eshop.domain.entity.Order;
import com.springshell.eshop.domain.enums.Color;
import com.springshell.eshop.service.MobileService;
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
public class MobileShellCommands {

    @Autowired
    @Lazy
    private LineReader lineReader;

    private final MobileService mobileService;

    private final OrderService orderService;

    public MobileShellCommands(MobileService mobileService, OrderService orderService) {
        this.mobileService = mobileService;
        this.orderService = orderService;
    }


    @ShellMethod(key = "find-by-id-mobile", value = "find Mobile by id")
    public MobileDto findById(@ShellOption({"-i, -id"}) Long id) {
        return MobileDto.from(mobileService.findById(id));
    }

    @ShellMethod(key = "find-by-name-mobile", value = "find Mobile by name")
    public MobileDto findByName(@ShellOption({"-n, -name"}) String name) {
        return MobileDto.from(mobileService.findByName(name));
    }

    @ShellMethod(key = "save-mobile", value = "save Mobile")
    public void save() {
        String description = lineReader.readLine("description" + ": ");
        String price = lineReader.readLine("price" + ": ");
        String brand = lineReader.readLine("brand" + ": ");
        String model = lineReader.readLine("model" + ": ");
        String name = lineReader.readLine("name" + ": ");
        String orderId = lineReader.readLine("order id" + ": ");

        Order order = orderService.findById(Long.valueOf(orderId));

        Mobile mobile = Mobile.builder()
                .description(description)
                .price(Double.valueOf(price))
                .name(name)
                .brand(brand)
                .model(model)
                .order(order)
                .build();

        mobileService.create(mobile);
    }

    @ShellMethod(key = "update-mobile", value = "update Mobile")
    public void updateMobile(@ShellOption({"-i, -id"}) Long id){
        String description = lineReader.readLine("description" + ": ");
        String price = lineReader.readLine("price" + ": ");
        String name = lineReader.readLine("name" + ": ");
        String brand = lineReader.readLine("brand" + ": ");
        String model = lineReader.readLine("model" + ": ");

        Mobile mobile = Mobile.builder()
                .description(description)
                .name(name)
                .price(Double.valueOf(price))
                .brand(brand)
                .model(model)
                .build();

        mobileService.update(mobile, id);
    }

    @ShellMethod(key = "delete-mobile", value = "delete Mobile by id")
    public void delete(@ShellOption({"-i", "-id"}) Long id) {
        mobileService.delete(id);
    }

    @ShellMethod(key = "list-mobile", value = "show all Mobiles")
    public List<MobileDto> findAll(){
        return mobileService.findAll().stream()
                .map(MobileDto::from)
                .collect(Collectors.toList());
    }
}
