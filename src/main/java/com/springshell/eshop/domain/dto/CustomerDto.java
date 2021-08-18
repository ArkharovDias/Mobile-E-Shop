package com.springshell.eshop.domain.dto;

import com.springshell.eshop.domain.entity.Customer;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CustomerDto {
    private Long id;
    private String name;
    private List<OrderItemDto> ordersDto;

    public static CustomerDto from(Customer customer){
        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .ordersDto(OrderItemDto.from(customer.getOrders()))
                .build();
    }
}
