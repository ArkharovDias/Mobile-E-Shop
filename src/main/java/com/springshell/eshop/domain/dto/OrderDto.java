package com.springshell.eshop.domain.dto;

import com.springshell.eshop.domain.entity.Customer;
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
public class OrderDto {
    private Long id;
    private Date orderedDate;
    private Date shippedDate;
    private Status status;
    private String totalPrice;
    private List<ProductDto> productsDto;

    public static OrderDto from(Order order){
        return OrderDto.builder()
                .id(order.getId())
                .orderedDate(order.getOrderedDate())
                .shippedDate(order.getShippedDate())
                .status(order.getStatus())
                .totalPrice(order.getTotalPrice())
                .productsDto(ProductDto.from(order.getProducts()))
                .build();
    }

    public static List<OrderDto> from(List<Order> orders){
        return orders.stream()
                .map(OrderDto::from)
                .collect(Collectors.toList());
    }
}
