package com.springshell.eshop.commands;

import com.springshell.eshop.domain.dto.OrderDto;
import com.springshell.eshop.domain.entity.Order;
import com.springshell.eshop.domain.enums.Status;
import com.springshell.eshop.service.OrderService;
import com.springshell.eshop.util.DateUtil;
import org.jline.reader.LineReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@ShellComponent
public class OrderShellCommands {

    private final OrderService orderService;

    @Autowired
    @Lazy
    private LineReader lineReader;

    public OrderShellCommands(OrderService orderService) {
        this.orderService = orderService;
    }


    @ShellMethod(key = "find-by-id-order", value = "find order by id")
    public OrderDto findById(@ShellOption({"-i, -id"}) Long id) {
        return OrderDto.from(orderService.findById(id));
    }

    @ShellMethod(key = "save-order", value = "save order")
    public void save() throws ParseException {

        String shippedDate = lineReader.readLine("shipped date" + ": ");
        String orderedDate = lineReader.readLine("ordered date" + ": ");
        String status = lineReader.readLine("status" + ": ");
        String totalPrice = lineReader.readLine("total price" + ": ");

        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        Date shippedDateConverted = format.parse(shippedDate);
        Date orderedDateConverted = format.parse(orderedDate);

        Order order = Order.builder()
                .shippedDate(shippedDateConverted)
                .orderedDate(orderedDateConverted)
                .status(Status.valueOf(status))
                .totalPrice(totalPrice)
                .build();

        orderService.create(order);

    }

    @ShellMethod(key = "update-order", value = "update order")
    public void updateOrder(@ShellOption({"-i, -id"}) Long id) throws ParseException {
        String shippedDate = lineReader.readLine("shipped date" + ": ");
        String orderedDate = lineReader.readLine("ordered date" + ": ");
        String status = lineReader.readLine("status" + ": ");
        String totalPrice = lineReader.readLine("total price" + ": ");

        Order order = Order.builder()
                .shippedDate(DateUtil.convert(shippedDate))
                .orderedDate(DateUtil.convert(orderedDate))
                .status(Status.valueOf(status))
                .totalPrice(totalPrice)
                .build();

        orderService.update(order, id);
    }

    @ShellMethod(key = "delete-order", value = "delete order by id")
    public void delete(@ShellOption({"-i", "-id"}) Long id) {
        orderService.delete(id);
    }

    @ShellMethod(key = "list-order", value = "show all orders")
    public List<OrderDto> findAll(){
        return orderService.findAll().stream()
                .map(OrderDto::from)
                .collect(Collectors.toList());
    }

}
