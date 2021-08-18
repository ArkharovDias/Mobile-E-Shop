package com.springshell.eshop.domain.dto;

import com.springshell.eshop.domain.entity.Order;
import com.springshell.eshop.domain.enums.Status;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderItemDto {
    private Long id;
    private Date orderedDate;
    private Date shippedDate;
    private Status status;
    private String totalPrice;

    public static OrderItemDto from(Order order){
        return OrderItemDto.builder()
                .id(order.getId())
                .orderedDate(order.getOrderedDate())
                .shippedDate(order.getShippedDate())
                .status(order.getStatus())
                .totalPrice(order.getTotalPrice())
                .build();
    }
    public static List<OrderItemDto> from(List<Order> orders){
        return orders.stream()
                .map(OrderItemDto::from)
                .collect(Collectors.toList());
    }

}
